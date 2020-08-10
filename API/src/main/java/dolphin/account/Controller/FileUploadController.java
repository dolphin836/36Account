package dolphin.account.Controller;

import dolphin.account.Business.MemberBusiness;
import dolphin.account.Response.MemberResponse;
import dolphin.account.Response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dolphin
 */
@RequestMapping("upload")
@RestController
@Tag(name = "文件上传")
public class FileUploadController {
    @Autowired
    private MemberBusiness memberBusiness;

    /**
     * 上传用户头像
     * @param file 文件
     * @param memberToken Token
     * @return MemberResponse
     */
    @PostMapping("avatar")
    @Operation(summary = "上传用户头像")
    public Response<MemberResponse> memberAvatar (@RequestParam("file") MultipartFile file, @RequestHeader("Token") String memberToken)
    {
        return Response.success(memberBusiness.memberAvatar(file, memberToken));
    }
}
