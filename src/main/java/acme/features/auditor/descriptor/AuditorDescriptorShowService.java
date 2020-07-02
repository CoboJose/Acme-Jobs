
package acme.features.auditor.descriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Status;
import acme.entities.roles.Auditor;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorDescriptorShowService implements AbstractShowService<Auditor, Descriptor> {

	@Autowired
	AuditorDescriptorRepository repository;


	@Override
	public boolean authorise(final Request<Descriptor> request) {
		assert request != null;

		boolean result;

		int descriptorId;
		Employer employer;
		Descriptor descriptor;
		Principal principal;

		principal = request.getPrincipal();
		descriptorId = request.getModel().getInteger("id");
		descriptor = this.repository.findOneDescriptorById(descriptorId);
		employer = descriptor.getJob().getEmployer();
		result = descriptor.getJob().getStatus() == Status.PUBLISHED || descriptor.getJob().getStatus() == Status.DRAFT && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Descriptor> request, final Descriptor entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description");

	}

	@Override
	public Descriptor findOne(final Request<Descriptor> request) {
		assert request != null;

		Descriptor result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDescriptorById(id);
		return result;
	}

}
