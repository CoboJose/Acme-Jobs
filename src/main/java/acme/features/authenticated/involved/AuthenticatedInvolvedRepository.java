
package acme.features.authenticated.involved;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.involved.Involved;
import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvolvedRepository extends AbstractRepository {

	//used in listMine
	@Query("select i from Involved i WHERE i.messageThread.id = ?1")
	Collection<Involved> findManyInvolvedsByMessageThread(int messageThreadId);
	//used in authorise
	@Query("select case when count(m) > 0 then true else false end FROM MessageThread m WHERE m.id = ?2 AND ( m.id IN (SELECT i.messageThread.id FROM Involved i WHERE i.userAccount.id = ?1) OR m.creator.id = ?1)")
	Boolean userInvolvedInMessageThread(int userAccountId, int messageThreadId);
	//used in create
	@Query("select u from UserAccount u WHERE u.username = ?1")
	UserAccount findOneUserAccount(String username);
	//used in create
	@Query("select m from MessageThread m WHERE m.id = ?1")
	MessageThread findOneMessageThread(int id);
	//used in create
	@Query("select i from Involved i WHERE i.id = ?1")
	Involved findOneInvolved(int id);
}
