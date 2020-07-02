
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	//used in listMine
	@Query("SELECT m FROM MessageThread m WHERE m.id IN (SELECT i.messageThread.id FROM Involved i WHERE i.userAccount.id = ?1) OR m.creator.id = ?1")
	Collection<MessageThread> findManyMessageThreadsByUserId(int id);
	//used in authorise
	//select all messageThreads where userAccountId is involved or creator and check if the given messageThread is in those threads
	@Query("select case when count(m) > 0 then true else false end FROM MessageThread m WHERE m.id = ?2 AND ( m.id IN (SELECT i.messageThread.id FROM Involved i WHERE i.userAccount.id = ?1) OR m.creator.id = ?1)")
	Boolean userInvolvedInMessageThread(int userAccountId, int messageThreadId);
	//used in show
	@Query("select m from MessageThread m WHERE m.id = ?1")
	MessageThread findOneMessageThread(int msgThreadId);
	//used in create
	@Query("select u from UserAccount u WHERE u.id = ?1")
	UserAccount findOneUserAccount(int id);
}
