package myenglish.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;
//RedisReactiveSessionRepository

@Configuration
//@EnableRedisWebSession()
public class SessionConfig {
}