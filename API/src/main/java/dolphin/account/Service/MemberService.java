package dolphin.account.Service;

import dolphin.account.Entity.Member;
import dolphin.account.Response.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dolphin
 */
@Service
public class MemberService {
    @Autowired
    private CommonService commonService;

    /**
     * 设置会员实体的客户端和应用
     * @param member 会员实体
     */
    public void setMemberClientAndApplication (Member member) {
        member.setClient(commonService.getClient());
        member.setApplication(commonService.getApplication());
    }

    public MemberResponse getMemberResponse (Member member) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setMemberId(member.getId());
        memberResponse.setUsername(member.getUsername());

        return memberResponse;
    }
}
