
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDutyShowService implements AbstractShowService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean result;

		int dutyId;
		Employer employer;
		Duty duty;
		Principal principal;

		principal = request.getPrincipal();
		dutyId = request.getModel().getInteger("id");
		duty = this.repository.findOneDutyById(dutyId);
		employer = duty.getDescriptor().getJob().getEmployer();
		result = duty.getDescriptor().getJob().getStatus() == Status.PUBLISHED || duty.getDescriptor().getJob().getStatus() == Status.DRAFT && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "titleDuty", "descriptionDuty", "percentage", "descriptor");
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(id);
		return result;
	}

}
