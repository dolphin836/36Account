package dolphin.account.Constant;

/**
 * @author dolphin
 */
public class CacheConstant {
    /**
     * 全局前缀
     */
    public static final String COMMON_KEY_PREFIX   = "account";

    /**
     * KEY 连接符
     */
    public static final String COMMON_CONNECT_CHAR = ".";

    /**
     * 用户模块功能前缀
     */
    public static final String MEMBER_KEY          = "member";

    /**
     * Token 缓存 Key
     */
    public static final String TOKEN_KEY           = "token";

    /**
     * Token 缓存过期时长：单位秒
     */
    public static final Integer TOKEN_TIMEOUT      = 3600 * 8;
}
