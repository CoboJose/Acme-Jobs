
package acme.features.administrator.requestAuditor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.RequestAuditor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorRequestAuditorRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select r from RequestAuditor r where r.id = ?1")
	RequestAuditor findOneRequestAuditorById(int id);

	@Query("select r from RequestAuditor r")
	Collection<RequestAuditor> findAllRequestAuditor();
}
