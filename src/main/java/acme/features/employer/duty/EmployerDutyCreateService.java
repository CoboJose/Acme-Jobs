
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		boolean result = true;

		//Assure this is the owner of the job
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();

		result = result && employer.getUserAccount().getId() == principal.getAccountId();

		//Assure the job is in draft

		result = result && job.getStatus() == Status.DRAFT;

		return result;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "titleDuty", "descriptionDuty", "percentage", "descriptor");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("jobId", request.getModel().getInteger("jobId"));
		}
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {

		Duty result;
		result = new Duty();
		Descriptor descriptor;

		int jobId = request.getModel().getInteger("jobId");
		descriptor = this.repository.findOneDescriptorByJobId(jobId);

		result.setDescriptor(descriptor);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Integer sumDuties;
		boolean notMore100;

		// percentages not greater than 100%
		if (!errors.hasErrors("percentage")) {

			sumDuties = this.repository.sumDutiesCreateByDescriptorId(entity.getDescriptor().getId());
			notMore100 = entity.getPercentage() <= 100;

			if (sumDuties != null) {
				notMore100 = sumDuties + entity.getPercentage() <= 100;
			}

			errors.state(request, notMore100, "percentage", "employer.job.form.error.percentages-more-100");
		}
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
