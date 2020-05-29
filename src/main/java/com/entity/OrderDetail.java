package com.entity;

import lombok.Data;

/**
 *订单详情实体类
 *@date 2019/8/10
 *@author danielHua
 */
@Data
public class OrderDetail
{
    private String orderId;

    private Item item;

    public OrderDetail(String orderId, Item item) {
        this.orderId = orderId;
        this.item = item;
    }
}
