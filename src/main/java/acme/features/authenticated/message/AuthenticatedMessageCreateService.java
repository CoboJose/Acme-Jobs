
package acme.features.authenticated.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		boolean result;

		Integer messageThreadId = request.getModel().getInteger("messageThreadId");
		Integer userId = request.getPrincipal().getAccountId();
		result = this.repository.userInvolvedInMessageThread(userId, messageThreadId);

		return result;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body", "creator.username");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("messageThreadId", request.getModel().getInteger("messageThreadId"));
		}
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result = new Message();

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.message.error.must-accept");

		errors.state(request, !this.isSpam(entity.getTitle()), "title", "authenticated.message.error.isSpam");
		errors.state(request, !this.isSpam(entity.getTags()), "tags", "authenticated.message.error.isSpam");
		errors.state(request, !this.isSpam(entity.getBody()), "body", "authenticated.message.error.isSpam");
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		int messsageThreadId = request.getModel().getInteger("messageThreadId");
		MessageThread messageThread = this.repository.findOneMessageThreadById(messsageThreadId);
		entity.setMessageThread(messageThread);

		int userId = request.getPrincipal().getAccountId();
		UserAccount userAccount = this.repository.findOneUserAccount(userId);
		entity.setCreator(userAccount);

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

	private boolean isSpam(final String text) {

		// Put in repository:
		// @Query("select cp from CustomisationParameters cp where cp.identifier = '1'")
		// CustomisationParameters findOneCustomisationParameters();

		boolean result = false;
		List<String> spamList = new ArrayList<String>();
		Float spamThreshold;
		CustomisationParameters cp = this.repository.findOneCustomisationParameters();
		float count = 0;

		spamList = Arrays.asList(cp.getSpamList().trim().split(";"));
		spamThreshold = cp.getSpamThreshold();

		for (String s : spamList) {
			count += StringUtils.countOccurrencesOf(text.toLowerCase(), s);
		}

		float numberWords = text.trim().split(" ").length;
		float spamPorcentage = count / numberWords;

		result = spamPorcentage >= spamThreshold;

		return result;
	}

}
