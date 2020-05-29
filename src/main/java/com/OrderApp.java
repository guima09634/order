package com;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *订单启动类
 *@date 2019/8/10
 *@author danielHua
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients(basePackages = "com.feign")
public class OrderApp
{
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }

    /**
     * 注入一个restTemplate
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    /**
     * 使用随机访问策略
     * @return
     */
    @Bean
    public IRule iRule() {
        return new RoundRobinRule();
    }
}
