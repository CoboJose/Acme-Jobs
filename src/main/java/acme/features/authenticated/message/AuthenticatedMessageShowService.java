
package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	// Internal state

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		boolean result;

		Integer messageId = request.getModel().getInteger("id");

		MessageThread messageThread = this.repository.findOneMessageThreadByMessageId(messageId);
		if (messageThread != null) {
			int messageThreadId = messageThread.getId();
			Integer userId = request.getPrincipal().getAccountId();
			result = this.repository.userInvolvedInMessageThread(userId, messageThreadId);

		} else {
			result = false;
		}

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body", "messageThread.title", "creator.username", "messageThread.id");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		Message result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneMessageById(id);

		return result;
	}

	//Interface

}
