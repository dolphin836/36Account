package dolphin.account.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author dolphin
 */
@Configuration
public class RedisConnectionFactory {
    @Bean
    public LettuceConnectionFactory cacheConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("127.0.0.0", 6379));
    }
}
