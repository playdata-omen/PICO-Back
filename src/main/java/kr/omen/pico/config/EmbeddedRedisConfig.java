package kr.omen.pico.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 로컬 환경일경우 내장 레디스가 실행된다.
 */
@Profile("local")
@Configuration
public class EmbeddedRedisConfig {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
//        redisServer = RedisServer.builder()
//                .port(redisPort)
//                //.redisExecProvider(customRedisExec) //com.github.kstyrc (not com.orange.redis-embedded)
//                .setting("maxmemory 128M") //maxheap 128M
//                .build();
//        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
//redisServer = RedisServer.builder()
//                .port(redisPort)
//                //.redisExecProvider(customRedisExec) //com.github.kstyrc (not com.orange.redis-embedded)
//                .setting("maxmemory 128M") //maxheap 128M
//                .build();
//        redisServer.start();