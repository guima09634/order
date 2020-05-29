package com.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 *操作redis的工具类
 *@date 2019/8/15
 *@author danielHua
 */
@Component
@Slf4j
public class RedisUtils
{

    @Autowired
    private JedisPool jedisPool;

    public String get(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            String value = jedis.get(key);
            log.info("获取的数据为：{}", value);
            return value;
        } catch (JedisException e) {
            log.error(e.getMessage());
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public void set(String key, String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        } catch (JedisException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void del(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        } catch (JedisException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public String setex(String key, int second, String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            String result = jedis.setex(key, second, value);
            log.info("获取的数据为：{}", result);
            return result;
        } catch (JedisException e) {
            log.error(e.getMessage());
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
