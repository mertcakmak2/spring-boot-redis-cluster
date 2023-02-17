package com.example.rediscluster.config;

import io.lettuce.core.ReadFrom;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCaching
@Profile("prod")
public class RedisClusterConfig {

    @Bean
    LettuceConnectionFactory redisConnectionFactory(RedisClusterConfiguration redisConfiguration) {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .commandTimeout(Duration.ofSeconds(120))
                .build();
        return new LettuceConnectionFactory(redisConfiguration, clientConfig);
    }

    @Bean
    RedisClusterConfiguration redisConfiguration() {
        List<String> clusterNodes = Arrays.asList("redis1:6379", "redis2:6379", "redis3:6379", "redis4:6379", "redis5:6379", "redis6:6379");
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterNodes);
        redisClusterConfiguration.setMaxRedirects(5);
        return redisClusterConfiguration;
    }

    @Bean
    public RedisTemplate<String, Object> template(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        System.out.println("Redis Cluster template bean");
        return template;
    }

}
