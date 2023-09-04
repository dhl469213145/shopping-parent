package com.shopping.order.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.rabbitmq.client.Channel;
import com.shopping.order.pojo.Order;
import com.shopping.order.pojo.entity.OrderEntity;
import com.shopping.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class OrderUpdateListener {


    @Autowired
    private OrderService orderService;

//    @RabbitHandler
//    @RabbitListener(queues = {"orderDirectQueue111"})
//    public void handlerData(String msg) {
//        //1.接收消息(有订单的ID  有transaction_id )
//        Map<String, String> map = JSON.parseObject(msg, Map.class);
//        //2.更新对营的订单的状态
//        if (map != null) {
//            if (map.get("return_code").equalsIgnoreCase("success")) {
//                orderService.updateStatus(map.get("out_trade_no"), map.get("transaction_id"));
//            } else {
//                //删除订单 支付失败.....
//            }
//        }
//    }

    @RabbitHandler
    @RabbitListener(queues = {"orderDirectQueue"})
    public void handlerData(Message msg, Channel channel) throws IOException  {
        //1.接收消息(有订单的ID  有transaction_id )
        log.info("接收到消息>>>{}",msg);
        OrderEntity order = JSON.parseObject(msg.getBody(), OrderEntity.class);
        order.setId(IdWorker.getIdStr());

        Long deliveryTag = (Long) msg.getMessageProperties().getDeliveryTag();
        try {
            System.out.println(Thread.currentThread().getName() + " 接收到来自orderDirectQueue：");
            log.info("监听orderDirectQueue消费消息=======:{}",new String(msg.getBody()));

            if (order != null) {
                orderService.save(order);
            }

            //手工签收
            channel.basicAck(deliveryTag,false);
//            int i = 3/0;
        } catch (Exception e) {
            channel.basicNack(deliveryTag, false, true);
            System.out.println("false///////" + e.getMessage());
        }


        //2.更新对营的订单的状态
//        if (order != null) {
//            orderService.save(order);
//            if (map.get("return_code").equalsIgnoreCase("success")) {
////                orderService.updateStatus(map.get("out_trade_no"), map.get("transaction_id"));
//            } else {
//                //删除订单 支付失败.....
//            }
//        }
    }

    @RabbitListener(queues = {"orderDirectQueue1"})
    public void consumerMsg(Message message, Channel channel) throws IOException {

        log.info("接收到消息>>>{}",message);
        int temp = 10/0;
        log.info("消息{}消费成功",message);
//        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();
//        try {
//            System.out.println(Thread.currentThread().getName() + " 接收到来自orderDirectQueue：");
//            log.info("监听orderDirectQueue消费消息=======:{}",new String(message.getBody()));
//            int i = 3/0;
//            //手工签收
//            channel.basicAck(deliveryTag,false);
////            int i = 3/0;
//        } catch (Exception e) {
//            channel.basicNack(deliveryTag, false, true);
//            System.out.println("false///////");
//        }

    }
}
