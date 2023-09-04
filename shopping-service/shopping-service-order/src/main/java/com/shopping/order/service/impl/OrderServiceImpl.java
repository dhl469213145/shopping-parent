package com.shopping.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.order.feign.SkuFeign;
import com.shopping.order.mapper.OrderDao;
import com.shopping.order.pojo.Order;
import com.shopping.order.pojo.dto.SkuSaveDTO;
import com.shopping.order.pojo.entity.OrderEntity;
import com.shopping.order.pojo.entity.SkuEntity;
import com.shopping.order.rabbitmq.OrderConfirmCallBack;
import com.shopping.order.rabbitmq.OrderRetrunCallBack;
import com.shopping.order.service.OrderService;
import com.shopping.order.util.BuildFirstName;
import com.shopping.order.util.BuildLastName;
import error.RRException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.PageUtils;
import utils.Query;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderConfirmCallBack orderConfirmCallBack;

    @Autowired
    private OrderRetrunCallBack orderRetrunCallBack;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDao orderDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );


        return new PageUtils(page);
    }

    @Override
    public void createOrder() {
        // TODO 预减库存

        String orderId = IdWorker.getIdStr();
        Order order = new Order();
        order.setId(orderId);
        order.setTotalNum(2);//设置总数量

        order.setTotalMoney(30);//设置总金额

        order.setPayMoney(30);//设置实付金额

        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());

        order.setOrderStatus("0");//0:未完成
        order.setPayStatus("0");//未支付
        order.setConsignStatus("0");//未发货
        order.setIsDelete("0");//未删除

        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        /**
         * 确保消息发送失败后可以重新返回到队列中
         * 注意：yml需要配置 publisher-returns: true
         */
//        rabbitTemplate.setMandatory(true);
        //开启确认模式
        rabbitTemplate.setConfirmCallback(orderConfirmCallBack);

        //开启消息可达监听
        rabbitTemplate.setReturnCallback(orderRetrunCallBack);

        /**
         * 使用org.springframework.amqp.core.Message 包装对象发送
         */
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(JSON.toJSONString(order).getBytes(StandardCharsets.UTF_8),messageProperties);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend("order.direct.exchange","order.direct.key", message, correlationData);
//        rabbitTemplate.convertAndSend("order.dlx.normal.exchange","order.dlx.normal.key", message, correlationData);
    }


    @Override
    public void batchCreateTestOrder(Long totalSize, Long eachSize) {
        long batchTimes = totalSize/eachSize +(totalSize%eachSize > 0 ? 1:0);

        for(int i = 0; i < batchTimes ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("批次开始");
                    List<OrderEntity> eachBatchOrders = new ArrayList<>();
                    for(int batchNum = 0 ; batchNum < eachSize; batchNum++) {
                        String orderId = IdWorker.getIdStr();
                        OrderEntity order = new OrderEntity();
                        order.setId(orderId);
                        order.setTotalNum(new Random().nextInt(500));//设置总数量
                        int price = new Random().nextInt(10000);
                        order.setTotalMoney(price);//设置总金额
                        order.setPayMoney(price);//设置实付金额

                        order.setCreateTime(new Date());
                        order.setUpdateTime(order.getCreateTime());

                        String firstName = new BuildFirstName().insideFirstName();
                        String lastName = new BuildLastName().insideLastName(new Random().nextInt(1));
                        String name = firstName + lastName;
                        order.setUsername(name);

                        String rechFirstName = new BuildFirstName().insideFirstName();
                        String rechLastName = new BuildLastName().insideLastName(new Random().nextInt(1));
                        String rechName = rechFirstName + rechLastName;
                        order.setReceiverContact(rechName);

                        order.setPayType("1");
                        order.setOrderStatus("0");//0:未完成
                        order.setPayStatus("0");//未支付
                        order.setConsignStatus("0");//未发货
                        order.setIsDelete("0");//未删除

                        eachBatchOrders.add(order);
                    }
                    orderService.saveBatch(eachBatchOrders);
                }
            }).start();
        }
    }

    @Override
    public void testDistributeSaveOrder() {
        String orderId = IdWorker.getIdStr();
        OrderEntity order = new OrderEntity();
        order.setId(orderId);
        order.setTotalNum(new Random().nextInt(500));//设置总数量
        int price = new Random().nextInt(10000);
        order.setTotalMoney(price);//设置总金额
        order.setPayMoney(price);//设置实付金额

        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());

        String firstName = new BuildFirstName().insideFirstName();
        String lastName = new BuildLastName().insideLastName(new Random().nextInt(1));
        String name = firstName + lastName;
        order.setUsername(name);

        String rechFirstName = new BuildFirstName().insideFirstName();
        String rechLastName = new BuildLastName().insideLastName(new Random().nextInt(1));
        String rechName = rechFirstName + rechLastName;
        order.setReceiverContact(rechName);

        order.setPayType("1");
        order.setOrderStatus("0");//0:未完成
        order.setPayStatus("0");//未支付
        order.setConsignStatus("0");//未发货
        order.setIsDelete("0");//未删除

        this.save(order);
    }

    @Override
    public void updateStatus(String out_trade_no, String transaction_id) {
        //1.根据id 获取订单的数据
        OrderEntity order = this.getById(out_trade_no);
        //2.更新
        order.setUpdateTime(new Date());

        //  支付的时间  从微信的参数中获取
        order.setPayTime(new Date());
        order.setOrderStatus("1");
        order.setPayStatus("1");
        order.setTransactionId(transaction_id);
        //3.更新到数据库
        this.updateById(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrderFeign() {

        SkuSaveDTO skuEntity = new SkuSaveDTO();
//        skuEntity.setCreateTime(new Date());
//        skuEntity.setUpdateTime(new Date());
        skuEntity.setName("test111");
        skuEntity.setPrice(25);
        skuEntity.setStatus("1");
        skuEntity.setNum(2000);
        skuEntity.setSn("sn001215421");
        skuEntity.setCommentNum(2000);
        skuFeign.saveOrder(skuEntity);


        String orderId = IdWorker.getIdStr();
        OrderEntity order = new OrderEntity();
        order.setId(orderId);
        order.setTotalNum(2);//设置总数量
        order.setTotalMoney(30);//设置总金额
        order.setPayMoney(30);//设置实付金额
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());
        order.setOrderStatus("0");//0:未完成
        order.setPayStatus("0");//未支付
        order.setConsignStatus("0");//未发货
        order.setIsDelete("0");//未删除
        this.save(order);


    }

    @Override
    public void saveProductFeign() {
        SkuSaveDTO skuSaveDTO = new SkuSaveDTO();
        skuSaveDTO.setSn("test" + new Random().nextInt(10));
        skuSaveDTO.setName("testName" + new Random().nextInt(10));
        skuSaveDTO.setCommentNum(new Random().nextInt(3));
        skuSaveDTO.setAlertNum(new Random().nextInt(3));
        skuSaveDTO.setBrandName("testBrandName" + new Random().nextInt(10));
        skuSaveDTO.setCategoryId(1);
        skuSaveDTO.setCategoryName("testCategoryName" + new Random().nextInt(10));
        skuSaveDTO.setPrice(new Random().nextInt(3));
        skuSaveDTO.setStatus("1");
        skuSaveDTO.setSaleNum(0);
        skuSaveDTO.setNum(20);
        skuSaveDTO.setImage("sdfsdf");
        skuSaveDTO.setId(IdWorker.getIdStr());
        skuFeign.saveOrder(skuSaveDTO);
    }

    @Override
    public void distributeTx() {
        String orderId = IdWorker.getIdStr();
        OrderEntity order = new OrderEntity();
        order.setId(orderId);
        order.setTotalNum(2);//设置总数量
        order.setTotalMoney(30);//设置总金额
        order.setPayMoney(30);//设置实付金额
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());
        order.setOrderStatus("0");//0:未完成
        order.setPayStatus("0");//未支付
        order.setConsignStatus("0");//未发货
        order.setIsDelete("0");//未删除
        this.save(order);

        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(JSON.toJSONString(order).getBytes(StandardCharsets.UTF_8),messageProperties);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.convertAndSend("order.tx.direct.exchange","order.tx.direct.key", message, correlationData);
    }

    public static void main(String[] args) {
        long i = 100;
        long j = 10;
        System.out.println(i/j);
        long z = 100 / 10 + (100 % 10 > 0 ? 1 : 0);
        System.out.println(z);
    }
}
