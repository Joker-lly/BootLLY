package com.lly.config;

import io.netty.util.Constant;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述: 配置 redis 集群， 该配置文件和RedisConfig有冲突
 *
 *
 * @author Joker-lly
 * @since 2020-12-24
 */
@Configuration
public class JedisClusterConfig {
    @Bean
    public JedisCluster getJedisCluster() {
        Set<HostAndPort> nodesList = new HashSet<>();
        nodesList.add(new HostAndPort("192.168.31.33",8001));
        nodesList.add(new HostAndPort("192.168.31.33",8002));
        nodesList.add(new HostAndPort("192.168.31.33",8003));
        nodesList.add(new HostAndPort("192.168.31.33",8004));
        nodesList.add(new HostAndPort("192.168.31.33",8005));
        nodesList.add(new HostAndPort("192.168.31.33",8006));
        nodesList.add(new HostAndPort("192.168.31.33",8007));
        nodesList.add(new HostAndPort("192.168.31.33",8008));
        JedisCluster jedisCluster = new JedisCluster(nodesList,2000,2000,
                5,"123456", new GenericObjectPoolConfig());
        return jedisCluster;
    }
}