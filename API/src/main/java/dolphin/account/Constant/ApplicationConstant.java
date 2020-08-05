package dolphin.account.Constant;

import dolphin.account.Exception.Common.BusinessException;
import dolphin.account.Exception.CommonException;
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
    PASSWORD ( 2, "36password.com"),

    /**
     * 36awesome.com
     */
    AWESOME ( 3, "36awesome.com"),

    /**
     * 36college.com
     */
    COLLEGE ( 4, "36college.com");

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

    public static ApplicationConstant getApplication (Integer code) {
        for (ApplicationConstant applicationConstant : ApplicationConstant.values()) {
            if (applicationConstant.getCode().equals(code)) {
                return applicationConstant;
            }
        }

        throw new BusinessException(CommonException.ExceptionCode.HTTP_HEADER_APPLICATION_IS_ERROR);
    }
}
