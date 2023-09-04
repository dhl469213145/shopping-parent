package com.shopping.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.order.pojo.Order;
import com.shopping.order.pojo.entity.OrderEntity;
import utils.PageUtils;

import java.util.Map;

public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void createOrder();

    void batchCreateTestOrder(Long totalSize, Long eachSize);

    void testDistributeSaveOrder();


    /**
     * 更新对应的订单的状态
     * @param out_trade_no
     * @param transaction_id
     */
    public void updateStatus(String out_trade_no,String transaction_id);

    /**
     * 跨服务保存
     */
    public void saveOrderFeign();


    public void saveProductFeign();


    /**
     * 分布式事务
     */
    public void distributeTx();
}
