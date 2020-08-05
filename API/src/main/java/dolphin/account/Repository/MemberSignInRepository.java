package dolphin.account.Repository;

import dolphin.account.Entity.MemberSignIn;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * @author dolphin
 */
public interface MemberSignInRepository extends CrudRepository<MemberSignIn, Long>, Repository<MemberSignIn, Long> {

}
