
package acme.entities.involved;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(indexes = {
//	@Index(columnList = "title")
//})
public class Involved extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Relationships -----------------------------------------------
	@ManyToOne(optional = false)
	private UserAccount			userAccount;

	@ManyToOne(optional = false)
	private MessageThread		messageThread;
}
