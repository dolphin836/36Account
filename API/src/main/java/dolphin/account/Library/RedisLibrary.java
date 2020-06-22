package dolphin.account.Library;

import dolphin.account.Constant.CacheConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author dolphin
 */
@Component
public class RedisLibrary {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String getCompleteKey (String ... key) {
        StringBuilder completeKey = new StringBuilder();
        // 公共前缀
        completeKey.append(CacheConstant.COMMON_KEY_PREFIX).append(CacheConstant.COMMON_CONNECT_CHAR);
        // 拼接 KEY
        for (String k : key) {
            completeKey.append(k).append(CacheConstant.COMMON_CONNECT_CHAR);
        }
        // 删除最后一个链接字符
        completeKey.deleteCharAt(completeKey.length() - 1);

        return completeKey.toString();
    }

    public void set (String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set (String key, String value, Duration timeout) {
        redisTemplate.opsForValue().set(key, value, timeout);
    }

    public String get (String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
