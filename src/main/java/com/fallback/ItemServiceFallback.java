package com.fallback;

import com.entity.Item;
import com.feign.ItemFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 *ItemService的回调类
 *@date 2019/8/12
 *@author danielHua
 */
@Component
@Slf4j
public class ItemServiceFallback implements FallbackFactory<ItemFeignClient>
{
    /* public static Item queryItemById(@PathVariable Long id) {
        log.info("-------服务调用失败，id为：{}，即将启动回调方法。", id);
        return new Item(id, "服务降级方法queryItemById", null, null, 0L);
    } */

    @Override
    public ItemFeignClient create(Throwable cause) {
        log.info("错误信息:{}", cause.getMessage());
        String msg = cause == null? "":cause.getMessage();
        if(StringUtils.isNotBlank(msg)) {
            log.info("错误信息为：{}", msg);
        }
        return (id -> new Item(id, "服务降级方法queryItemById", null, null, 0L));
    }
}
