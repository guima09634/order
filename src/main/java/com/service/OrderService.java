package com.service;

import com.apec.framework.common.util.JsonUtils;
import com.constant.OrderCacheKey;
import com.entity.Item;
import com.entity.Order;
import com.entity.OrderDetail;
import com.rpc.RpcItemService;
import com.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *订单业务类
 *@date 2019/8/10
 *@author danielHua
 */
@Service
public class OrderService
{
    @Autowired
    private RpcItemService rpcItemService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Map<String, Order> ORDER_DATA = new HashMap<String, Order>();

    static {
        Order order = new Order();
        order.setCreateDate(new Date());
        order.setOrderId("201810300001");
        order.setUserId(1L);
        order.setUpdateDate(new Date());
        List<OrderDetail> orderDetails = new ArrayList<>(2);
        orderDetails.add(new OrderDetail("201810300001", new Item(1L, null, null, null, 0L)));
        orderDetails.add(new OrderDetail("201810300001", new Item(2L, null, null, null, 0L)));
        order.setOrderDetails(orderDetails);

        ORDER_DATA.put("201810300001", order);

    }

    /**
     * 根据orderId查找订单
     * @param orderId 订单Id
     * @return
     */
    public Order queryByOrderId(String orderId) {
         String jsonOrder = redisUtils.get(OrderCacheKey.ORDER_KEY + "_" + orderId);
         if(!StringUtils.isBlank(jsonOrder)) {
            return JsonUtils.parseObject(jsonOrder, Order.class);
        } else
        {
            Order order = mongoTemplate.find(new Query().addCriteria(Criteria.where("orderId").is(orderId)), Order.class).get(0);
            Optional<Order> orderOptional = Optional.ofNullable(order);
            orderOptional.ifPresent(o -> {
                o.getOrderDetails().forEach(orderDetail -> {
                    orderDetail.setItem(rpcItemService.queryItemById(orderDetail.getItem().getId()));
                });
            });
            cacheService.saveOrder(orderOptional.get());
            redisUtils.set(OrderCacheKey.ORDER_KEY + "_" + orderId, JsonUtils.toJSONString(orderOptional.get()));
            return orderOptional.get();
        }
    }

    /**
     * 保存订单至mongodb中
     */
    public void saveOrder() {
        mongoTemplate.insert(ORDER_DATA.get("201810300001"), "order");
    }
}
