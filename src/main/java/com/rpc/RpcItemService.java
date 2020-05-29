package com.rpc;

import com.entity.Item;
import com.feign.ItemFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *rpc查找item服务的数据
 *@date 2019/8/10
 *@author danielHua
 */
@Service
@Slf4j
public class RpcItemService
{
    @Autowired
    private ItemFeignClient itemFeignClient;

    private Long itemId;

    /**
     * 向item服务发送请求，查找item
     * @param id
     * @return
     */
    public Item queryItemById(Long id) {
        log.info("----------调用商品服务，参数为：{}------------------", id);
        //  return restTemplate.getForObject("http://APP-ITEM-SERVICE/item/queryById?id=" + id, Item.class);
        return itemFeignClient.queryItemById(id);
    }

    public Item queryItemByIdFallbackMethod(Long id) {
        log.info("查询商品信息出错，商品编码为：{}，即将启动回滚方法。", id);
        return new Item(id, "查询商品信息出错", null, null, null);
    }
}
