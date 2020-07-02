
package acme.features.authenticated.requestAuditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.RequestAuditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedRequestAuditorShowService implements AbstractShowService<Authenticated, RequestAuditor> {

	@Autowired
	AuthenticatedRequestAuditorRepository repository;


	@Override
	public boolean authorise(final Request<RequestAuditor> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<RequestAuditor> request, final RequestAuditor entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibilityStatement");
	}

	@Override
	public RequestAuditor findOne(final Request<RequestAuditor> request) {
		// TODO Auto-generated method stub
		assert request != null;

		RequestAuditor result;

		Principal principal;
		principal = request.getPrincipal();

		result = this.repository.findOneRequestAuditorById(principal.getAccountId());

		return result;
	}

}
