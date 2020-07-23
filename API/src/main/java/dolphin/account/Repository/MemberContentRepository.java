package dolphin.account.Repository;

import dolphin.account.Entity.MemberContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * @author dolphin
 */
public interface MemberContentRepository extends CrudRepository<MemberContent, Long>, Repository<MemberContent, Long> {

    /**
     * 根据 MemberId 查询用户详细数据
     * @param memberId 用户 Id
     * @return MemberContent
     */
    MemberContent findByMemberId (Long memberId);
}
