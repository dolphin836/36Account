package dolphin.account.Constant;

import dolphin.account.Exception.Common.BusinessException;
import dolphin.account.Exception.CommonException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dolphin
 */
public enum ClientConstant {
    /**
     * 未知
     */
    UNKNOWN ( 0, "未知"),

    /**
     * 系统生成
     */
    SYSTEM_GENERATE ( 1, "系统生成"),

    /**
     * PC web
     */
    WEB ( 2, "桌面端 web"),

    /**
     * 移动 web
     */
    MOBILE ( 3, "移动端 web"),

    /**
     * iOS
     */
   IOS ( 4, "iOS"),

    /**
     * Android
     */
    ANDROID ( 5, "Android"),

    /**
     * 微信
     */
    WECHAT ( 6, "微信");

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

    ClientConstant (Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Boolean isClient (Integer code) {
        if (ClientConstant.UNKNOWN.getCode().equals(code)) {
            return false;
        }

        for (ClientConstant clientConstant : ClientConstant.values()) {
            if (clientConstant.getCode().equals(code)) {
                return true;
            }
        }

        return false;
    }

    public static ClientConstant getClient (Integer code) {
        for (ClientConstant clientConstant : ClientConstant.values()) {
            if (clientConstant.getCode().equals(code)) {
                return clientConstant;
            }
        }

        throw new BusinessException(CommonException.ExceptionCode.HTTP_HEADER_CLIENT_IS_ERROR);
    }
}
