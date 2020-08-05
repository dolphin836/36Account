package dolphin.account.Service;

import dolphin.account.Entity.MemberSignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dolphin
 */
@Service
public class MemberSignInService {
    @Autowired
    private CommonService commonService;

    /**
     * 设置会员登录历史实体的客户端和应用
     * @param memberSignIn 会员登录历史实体
     */
    public void setMemberClientAndApplication (MemberSignIn memberSignIn) {
        memberSignIn.setClient(commonService.getClient());
        memberSignIn.setApplication(commonService.getApplication());
    }
}
