
package acme.features.authenticated.involved;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.involved.Involved;
import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedInvolvedCreateService implements AbstractCreateService<Authenticated, Involved> {

	@Autowired
	AuthenticatedInvolvedRepository repository;


	@Override
	public boolean authorise(final Request<Involved> request) {
		assert request != null;
		boolean result;

		Integer messageThreadId = request.getModel().getInteger("messageThreadId");
		Integer userId = request.getPrincipal().getAccountId();
		result = this.repository.userInvolvedInMessageThread(userId, messageThreadId);

		return result;
	}

	@Override
	public void bind(final Request<Involved> request, final Involved entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Involved> request, final Involved entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("messageThreadId", request.getModel().getInteger("messageThreadId"));
		}
	}

	@Override
	public Involved instantiate(final Request<Involved> request) {
		Involved result = new Involved();
		return result;
	}

	@Override
	public void validate(final Request<Involved> request, final Involved entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String username = request.getModel().getString("userAccount.username");
		UserAccount userAccount = this.repository.findOneUserAccount(username);

		//we can't add a user that doesn't exist, and i check it this way to avoid null pointer exceptions
		if (userAccount == null) {
			if (!errors.hasErrors("userAccount.username")) {
				boolean exist = false;
				errors.state(request, exist, "userAccount.username", "involved.duplicated.error.doesnt-exist");
			}
		} else {
			int userAccountId = userAccount.getId();
			int messageThreadId = request.getModel().getInteger("messageThreadId");

			//we can't add an already involved user
			if (!errors.hasErrors("userAccount.username")) {
				boolean notDuplicated = this.repository.userInvolvedInMessageThread(userAccountId, messageThreadId) == false;
				errors.state(request, notDuplicated, "userAccount.username", "involved.duplicated.error.alreay-involved");
			}
		}
	}

	@Override
	public void create(final Request<Involved> request, final Involved entity) {
		assert request != null;
		assert entity != null;

		String username = request.getModel().getString("userAccount.username");
		UserAccount userAccount = this.repository.findOneUserAccount(username);
		entity.setUserAccount(userAccount);

		int messageThreadId = request.getModel().getInteger("messageThreadId");
		MessageThread messageThread = this.repository.findOneMessageThread(messageThreadId);
		entity.setMessageThread(messageThread);

		this.repository.save(entity);

	}

}
