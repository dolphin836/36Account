package dolphin.account.Repository;

import dolphin.account.Entity.Member;
import org.springframework.data.repository.CrudRepository;

/**
 * @author dolphin
 */
public interface MemberRepository extends CrudRepository<Member, Long> {

}
