package dolphin.account.Service;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import dolphin.account.Constant.CommonConstant;
import dolphin.account.Exception.Common.BusinessException;
import dolphin.account.Exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author dolphin
 */
@Service
public class OSSService {
    @Autowired
    private OSS ossClient;

    /**
     * 上传文件到 OSS
     * @param bucketName   Bucket 名称
     * @param filePathName 文件存储路径
     * @param inputStream  文件
     */
    public void uploadFile (String bucketName, String filePathName, InputStream inputStream) {
        try {
            ossClient.putObject(bucketName, filePathName, inputStream);
        } catch (Exception e) {
            throw new BusinessException(CommonException.ExceptionCode.FILE_UPLOAD_ERROR, e.getMessage());
        }
    }

    /**
     * 获取用户头像的 URL
     * @param bucketName   Bucket 名称
     * @param filePathName 文件存储路径
     */
    public String getMemberAvatarUrl (String bucketName, String filePathName) {
        Date memberAvatarExpiration                     = new Date(System.currentTimeMillis() + CommonConstant.MEMBER_AVATAR_EXPIRATION);
        GeneratePresignedUrlRequest memberAvatarRequest = new GeneratePresignedUrlRequest(bucketName, filePathName, HttpMethod.GET);
        memberAvatarRequest.setExpiration(memberAvatarExpiration);
        memberAvatarRequest.setProcess(CommonConstant.MEMBER_AVATAR_STYLE);

        URL memberAvatarUrl = ossClient.generatePresignedUrl(memberAvatarRequest);

        return memberAvatarUrl.toString();
    }
}
