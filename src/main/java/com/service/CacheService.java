package com.service;

import com.apec.framework.common.util.JsonUtils;
import com.constant.OrderCacheKey;
import com.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 *缓存业务类，用于操作缓存，实现CURD
 *@date 2019/8/15
 *@author danielHua
 */
@Service
@Slf4j
public class CacheService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 从缓存获取order
     * @param orderId
     * @return
     */
    public String getOrder(String orderId) {
        final String key = OrderCacheKey.ORDER_KEY + "_" + orderId;
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 保存订单至缓存在中
     * @param order 订单实体
     */
    public void saveOrder(Order order) {
        final String key = OrderCacheKey.ORDER_KEY + "_" + order.getOrderId();
        stringRedisTemplate.opsForValue().set(key, JsonUtils.toJSONString(order));
    }
}
