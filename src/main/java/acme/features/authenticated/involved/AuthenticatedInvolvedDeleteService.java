
package acme.features.authenticated.involved;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.involved.Involved;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedInvolvedDeleteService implements AbstractDeleteService<Authenticated, Involved> {

	@Autowired
	AuthenticatedInvolvedRepository repository;


	@Override
	public boolean authorise(final Request<Involved> request) {
		assert request != null;
		return true;
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

		request.unbind(entity, model);
	}

	@Override
	public Involved findOne(final Request<Involved> request) {
		assert request != null;

		Involved result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneInvolved(id);

		return result;
	}

	@Override
	public void validate(final Request<Involved> request, final Involved entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Involved> request, final Involved entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
