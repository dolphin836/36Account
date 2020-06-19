package dolphin.account.Constant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dolphin
 */
public enum ApplicationConstant {
    /**
     * 未知
     */
    UNKNOWN ( 0, "未知"),

    /**
     * 36photo.cn
     */
    PHOTO ( 1, "36photo.cn"),

    /**
     * 36password.com
     */
    PASSWORD ( 1, "36password.com"),

    /**
     * 36awesome.com
     */
    AWESOME ( 2, "36awesome.com");

    /**
     * 编码
     */
    @Getter
    @Setter
    private Integer code;

    /**
     * 名称
     */
    @Getter
    @Setter
    private String name;

    ApplicationConstant (Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Boolean isApplication (Integer code) {
        if (ApplicationConstant.UNKNOWN.getCode().equals(code)) {
            return false;
        }

        for (ApplicationConstant applicationConstant : ApplicationConstant.values()) {
            if (applicationConstant.getCode().equals(code)) {
                return true;
            }
        }

        return false;
    }
}
