package com.lly.config.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * 描述: 连接 redis 集群模板
 *
 * @author Joker-lly
 * @since 2020-12-24
 */
//@Component
public class JedisTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisTemplate.class);

    @Autowired
    private JedisCluster jedisCluster;
    private static final String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

    /**
     * 设置缓存
     *
     * @param prefix 缓存前缀（用于区分缓存，防止缓存键值重复）
     * @param key    缓存key
     * @param value  缓存value
     */
    public void set(String prefix, String key, String value) {

        if (StringUtils.isBlank(prefix)) throw new IllegalArgumentException("prefix must not null!");
        if (StringUtils.isBlank(key)) throw new IllegalArgumentException("key must not null!");

        jedisCluster.set(prefix + KEY_SPLIT + key, value);
        LOGGER.debug("RedisUtil:set cache key={},value={}", prefix + KEY_SPLIT + key, value);
    }

    /**
     * 设置缓存，并且自己指定过期时间
     *
     * @param prefix
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    public void setWithExpireTime(String prefix, String key, String value, int expireTime) {

        if (StringUtils.isBlank(prefix)) throw new IllegalArgumentException("prefix must not null!");
        if (StringUtils.isBlank(key)) throw new IllegalArgumentException("key must not null!");

        jedisCluster.setex(prefix + KEY_SPLIT + key, expireTime, value);
        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", prefix + KEY_SPLIT + key, value,
                expireTime);
    }

    /**
     * 设置缓存，并且由配置文件指定过期时间
     *
     * @param prefix
     * @param key
     * @param value
     */
    public void setWithExpireTime(String prefix, String key, String value) {

        if (StringUtils.isBlank(prefix)) throw new IllegalArgumentException("prefix must not null!");
        if (StringUtils.isBlank(key)) throw new IllegalArgumentException("key must not null!");

        jedisCluster.setex(prefix + KEY_SPLIT + key, 2000, value);
        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", prefix + KEY_SPLIT + key, value, 2000);
    }

    /**
     * 获取指定key的缓存
     *
     * @param prefix
     * @param key
     */
    public String get(String prefix, String key) {

        if (StringUtils.isBlank(prefix)) throw new IllegalArgumentException("prefix must not null!");
        if (StringUtils.isBlank(key)) throw new IllegalArgumentException("key must not null!");

        String value = jedisCluster.get(prefix + KEY_SPLIT + key);
        LOGGER.debug("RedisUtil:get cache key={},value={}", prefix + KEY_SPLIT + key, value);
        return value;
    }


    /**
     * 删除指定key的缓存
     *
     * @param prefix
     * @param key
     */

    public void deleteWithPrefix(String prefix, String key) {

        if (StringUtils.isBlank(prefix)) throw new IllegalArgumentException("prefix must not null!");
        if (StringUtils.isBlank(key)) throw new IllegalArgumentException("key must not null!");

        jedisCluster.del(prefix + KEY_SPLIT + key);
        LOGGER.debug("RedisUtil:delete cache key={}", prefix + KEY_SPLIT + key);
    }


    public void delete(String key) {
        if (StringUtils.isBlank(key)) throw new IllegalArgumentException("key must not null!");
        jedisCluster.del(key);
        LOGGER.debug("RedisUtil:delete cache key={}", key);
    }
}