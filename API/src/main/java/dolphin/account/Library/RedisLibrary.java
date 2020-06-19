package dolphin.account.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author dolphin
 */
@Component
public class RedisLibrary {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void set (String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
