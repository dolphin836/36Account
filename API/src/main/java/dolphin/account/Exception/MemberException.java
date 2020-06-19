package dolphin.account.Exception;

import dolphin.account.Constant.ExceptionConstant;
import dolphin.account.Exception.Common.ExceptionCodeDefine;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dolphin
 */
public interface MemberException {
    enum ExceptionCode implements ExceptionCodeDefine {
        /**
         * 用户名重复
         */
        USERNAME_REPEAT( ExceptionConstant.MEMBER_MODULE_CODE + "00", "用户名 %s 已经被注册"),
        /**
         * 用户名重复
         */
        USERNAME_NOT_EXIST( ExceptionConstant.MEMBER_MODULE_CODE + "01", "用户 %s 不存在"),
        /**
         * 用户名重复
         */
        PASSWORD_ERROR( ExceptionConstant.MEMBER_MODULE_CODE + "02", "密码错误");

        /**
         * 状态码
         */
        @Getter
        @Setter
        private String code;

        /**
         * 提示消息
         */
        @Getter
        @Setter
        private String message;

        ExceptionCode (String code, String message) {
            this.code    = code;
            this.message = message;
        }
    }
}
