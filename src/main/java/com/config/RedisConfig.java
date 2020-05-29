package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *redis配置类，配置jedis
 *@date 2019/8/15
 *@author danielHua
 */
@Configuration
@Slf4j
public class RedisConfig
{
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWait;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.timeout}")
    private int timeout;

    /**
     * 工厂模式创建jedisPool
     * @return
     */
    @Bean
    public JedisPool redisPoolFactory() {
        log.info("开始构建jedisPool");
        log.info("redis的地址为:{}:{}",host, port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        return new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }
}
