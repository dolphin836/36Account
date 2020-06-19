package dolphin.account.Repository;

import dolphin.account.Entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * @author dolphin
 */
public interface MemberRepository extends CrudRepository<Member, Long>, Repository<Member, Long> {
    /**
     * 根据用户名查询用户
     * @param  username 用户名
     * @return Member
     */
    Member findByUsername (String username);

    /**
     * 根据用户名查询用户记录是否存在
     * @param  username 用户名
     * @return Member
     */
    boolean existsMemberByUsername (String username);
}
