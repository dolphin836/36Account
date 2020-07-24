package dolphin.account.Service;

import dolphin.account.Constant.CacheConstant;
import dolphin.account.Constant.CommonConstant;
import dolphin.account.Entity.Member;
import dolphin.account.Entity.MemberContent;
import dolphin.account.Exception.Common.BusinessException;
import dolphin.account.Exception.MemberException;
import dolphin.account.Library.RedisLibrary;
import dolphin.account.Repository.MemberContentRepository;
import dolphin.account.Repository.MemberRepository;
import dolphin.account.Response.MemberResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author dolphin
 */
@Service
public class MemberService {
    @Autowired
    private CommonService commonService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberContentRepository memberContentRepository;

    @Autowired
    private OSSService ossService;

    @Autowired
    private RedisLibrary redis;

    /**
     * 设置会员实体的客户端和应用
     * @param member 会员实体
     */
    public void setMemberClientAndApplication (Member member) {
        member.setClient(commonService.getClient());
        member.setApplication(commonService.getApplication());
    }

    public MemberResponse getMemberResponse (Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            MemberResponse memberResponse = new MemberResponse();
            memberResponse.setMemberId(memberId);
            memberResponse.setUsername(member.getUsername());
            // 查询详细信息
            MemberContent memberContent = memberContentRepository.findByMemberId(memberId);

            if (null != memberContent) {
                BeanUtils.copyProperties(memberContent, memberResponse);
                // 获取头像的完整访问地址
                String memberAvatar    = memberContent.getAvatar();

                if (! "".equals(memberAvatar)) {
                    String memberAvatarUrl = ossService.getMemberAvatarUrl(CommonConstant.BUCKET_NAME, memberAvatar);
                    memberResponse.setAvatar(memberAvatarUrl);
                }
            }

            return memberResponse;
        }

        throw new BusinessException(MemberException.ExceptionCode.USER_NOT_EXIST);
    }

    public Long getMemberIdByToken (String memberToken) {
        String cacheKey   = redis.getCompleteKey(CacheConstant.MEMBER_KEY, CacheConstant.TOKEN_KEY, memberToken);
        String cacheValue = redis.get(cacheKey);

        if (null == cacheValue) {
            throw new BusinessException(MemberException.ExceptionCode.TOKEN_NOT_EXIST);
        }

        return  (long) Integer.parseInt(cacheValue);
    }
}
