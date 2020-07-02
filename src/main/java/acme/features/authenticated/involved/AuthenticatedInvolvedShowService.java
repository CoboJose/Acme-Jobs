
package acme.features.authenticated.involved;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.involved.Involved;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvolvedShowService implements AbstractShowService<Authenticated, Involved> {

	// Internal state

	@Autowired
	private AuthenticatedInvolvedRepository repository;


	@Override
	public boolean authorise(final Request<Involved> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Involved> request, final Involved entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username", "messageThread.title");
	}

	@Override
	public Involved findOne(final Request<Involved> request) {
		// TODO Auto-generated method stub
		Involved result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneInvolved(id);

		return result;
	}

}
