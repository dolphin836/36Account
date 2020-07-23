package dolphin.account.Exception;

import dolphin.account.Constant.ExceptionConstant;
import dolphin.account.Exception.Common.ExceptionCodeDefine;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dolphin
 */
public interface CommonException {
    enum ExceptionCode implements ExceptionCodeDefine {
        /**
         * 记录不存在
         */
        RECORD_NOT_EXIST( ExceptionConstant.COMMON_MODULE_CODE + "00", "记录不存在"),
        /**
         * 缺少 Client Header 参数
         */
        HTTP_HEADER_CLIENT_IS_NULL( ExceptionConstant.COMMON_MODULE_CODE + "01", "缺少 Header Client 参数"),
        /**
         * 缺少 Application Header 参数
         */
        HTTP_HEADER_APPLICATION_IS_NULL( ExceptionConstant.COMMON_MODULE_CODE + "02", "缺少 Header Application 参数"),
        /**
         * 缺少 Client Header 错误
         */
        HTTP_HEADER_CLIENT_IS_ERROR( ExceptionConstant.COMMON_MODULE_CODE + "03", "Header Client 参数错误"),
        /**
         * 缺少 Application Header 错误
         */
        HTTP_HEADER_APPLICATION_IS_ERROR( ExceptionConstant.COMMON_MODULE_CODE + "04", "Header Application 参数错误"),
        /**
         * 数据库错误
         */
        DB_ERROR( ExceptionConstant.COMMON_MODULE_CODE + "05", "数据库错误：%s"),
        /**
         * 上传文件为空
         */
        FILE_UPLOAD_EMPTY( ExceptionConstant.COMMON_MODULE_CODE + "06", "上传文件为空"),
        /**
         * 上传文件类型错误
         */
        FILE_UPLOAD_TYPE_ERROR( ExceptionConstant.COMMON_MODULE_CODE + "07", "不允许上传的文件类型"),
        /**
         * 上传文件大小错误
         */
        FILE_UPLOAD_SIZE_ERROR( ExceptionConstant.COMMON_MODULE_CODE + "08", "上传的文件大小超出限制"),
        /**
         * 文件上传错误
         */
        FILE_UPLOAD_ERROR( ExceptionConstant.COMMON_MODULE_CODE + "09", "文件上传失败：%s");

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
