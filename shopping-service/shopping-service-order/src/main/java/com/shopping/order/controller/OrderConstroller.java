package com.shopping.order.controller;

import api.R;
import com.shopping.order.pojo.dto.BatchCreateTestOrderDto;
import com.shopping.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.PageUtils;

import java.util.Map;

@RestController()
@RequestMapping("order")
public class OrderConstroller {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public void createOrder() {
        orderService.createOrder();
    }

    @PostMapping("/batchCreateTestOrder")
    public void batchCreateTestOrder(@RequestBody BatchCreateTestOrderDto batchCreateTestOrderDto) {
        orderService.batchCreateTestOrder(batchCreateTestOrderDto.getTotalSize(), batchCreateTestOrderDto.getEachSize());
    }

    @PostMapping("/testDistributeSaveOrder")
    public void testDistributeSaveOrder() {
        orderService.testDistributeSaveOrder();
    }

    /***
     * Order分页条件搜索实现
     * @param params
     * @return
     */
    @PostMapping(value = "/search")
    public R findPage(@RequestBody Map<String, Object> params) {
        //调用OrderService实现分页条件查询Order
        PageUtils orders = orderService.queryPage(params);
        return R.data(orders);
    }
}
