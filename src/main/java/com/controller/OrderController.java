package com.controller;

import com.entity.Order;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *订单控制器
 *@date 2019/8/10
 *@author danielHua
 */
@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/queryOrderById")
    public Order queryOrderById(@RequestParam String orderId) {
        orderService.saveOrder();
        return orderService.queryByOrderId(orderId);
    }
}
