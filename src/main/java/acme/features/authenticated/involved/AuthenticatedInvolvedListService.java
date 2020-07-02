
package acme.features.authenticated.involved;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.involved.Involved;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedInvolvedListService implements AbstractListService<Authenticated, Involved> {

	@Autowired
	AuthenticatedInvolvedRepository repository;


	@Override
	public Collection<Involved> findMany(final Request<Involved> request) {
		assert request != null;

		Collection<Involved> result;
		int messageThreadId = request.getModel().getInteger("messageThreadId");
		result = this.repository.findManyInvolvedsByMessageThread(messageThreadId);

		return result;
	}
	@Override
	public void unbind(final Request<Involved> request, final Involved entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "userAccount.username", "userAccount.id");

	}
	@Override
	public boolean authorise(final Request<Involved> request) {
		assert request != null;
		boolean result;

		Integer messageThreadId = request.getModel().getInteger("messageThreadId");
		Integer userId = request.getPrincipal().getAccountId();
		result = this.repository.userInvolvedInMessageThread(userId, messageThreadId);

		return result;
	}
}
