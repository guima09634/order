package com.feign;

import com.entity.Item;
import com.fallback.ItemServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *ItemFeign客户端
 *@date 2019/8/12
 *@author danielHua
 */
@FeignClient(value = "APP-ITEM-SERVICE", fallbackFactory = ItemServiceFallback.class)
public interface ItemFeignClient
{
    /**
     * query item by id, if fallback, set id for item;
     * @param id
     * @return
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    Item queryItemById(@PathVariable Long id);
}
