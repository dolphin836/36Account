package dolphin.account.Constant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dolphin
 */

public enum ImageContentTypeConstant {
    /**
     * JPEG
     */
    JPEG ( "image/jpeg"),
    /**
     * GIF
     */
    GIF ( "image/gif"),
    /**
     * BMP
     */
    BMP ( "image/bmp"),
    /**
     * BMP
     */
    WEBP ( "image/webp"),
    /**
     * PNG
     */
    PNG ( "image/png");

    /**
     * 编码
     */
    @Getter
    @Setter
    private String code;

    ImageContentTypeConstant (String code) {
        this.code = code;
    }

    public static Boolean isImage (String code) {
        for (ImageContentTypeConstant imageContentTypeConstant : ImageContentTypeConstant.values()) {
            if (imageContentTypeConstant.getCode().equals(code)) {
                return true;
            }
        }

        return false;
    }
}
