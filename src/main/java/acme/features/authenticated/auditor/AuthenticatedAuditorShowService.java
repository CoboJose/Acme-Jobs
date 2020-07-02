
package acme.features.authenticated.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuditorShowService implements AbstractShowService<Authenticated, Auditor> {

	@Autowired
	AuthenticatedAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibilityStatement");

	}

	@Override
	public Auditor findOne(final Request<Auditor> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Auditor result;

		Principal principal;
		principal = request.getPrincipal();

		result = this.repository.findOneAuditorById(principal.getAccountId());

		return result;
	}

}
