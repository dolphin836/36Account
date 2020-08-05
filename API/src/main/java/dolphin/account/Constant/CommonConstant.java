package dolphin.account.Constant;

/**
 * @author dolphin
 */
public class CommonConstant {
    /**
     * 密码加密强度：值越大越安全，但是加密时间越长
     */
    public static final Integer PASSWORD_HASH_LENGTH = 10;

    /**
     * 用户 Token 字符串长度
     */
    public static final Integer MEMBER_TOKEN_LENGTH  = 36;

    /**
     * OSS Bucket Name
     */
    public static final String BUCKET_NAME = "36account";

    /**
     * 用户头像允许上传的最大大小 2M
     */
    public static final Long MEMBER_AVATAR_MAX_SIZE = 2097152L;

    /**
     * 上传文件文件名称随机字符串长度
     */
    public static final Integer UPLOAD_FILE_RANDOM_FILE_NAME = 32;

    /**
     * 用户头像图片处理参数
     */
    public static final String MEMBER_AVATAR_STYLE = "image/resize,m_fill,w_200,h_200";

    /**
     * 用户头像授权访问有效时间：30 天
     */
    public static final Long MEMBER_AVATAR_EXPIRATION = 30 * 24 * 3600 * 1000L;
}
