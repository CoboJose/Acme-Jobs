
package acme.features.auditor.auditRecords;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecords> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecords> request) {
		assert request != null;

		Integer jobId = request.getModel().getInteger("jobId");

		Integer auditorId = request.getPrincipal().getActiveRoleId();

		Integer count = this.repository.checkIfAuditRecordExist(jobId, auditorId);

		if (count > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void bind(final Request<AuditRecords> request, final AuditRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AuditRecords> request, final AuditRecords entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment", "body", "auditor", "job.reference", "job.id");

		if (request.getMethod() == HttpMethod.GET) {
			model.setAttribute("jobId", request.getModel().getInteger("jobId"));
		}
	}

	@Override
	public AuditRecords instantiate(final Request<AuditRecords> request) {
		AuditRecords result = new AuditRecords();

		Integer auditorId = request.getPrincipal().getActiveRoleId();
		Auditor auditor = this.repository.findAuditor(auditorId);

		Integer jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findJob(jobId);

		result.setAuditor(auditor);
		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecords> request, final AuditRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditRecords> request, final AuditRecords entity) {
		assert request != null;
		assert entity != null;

		Date d = new Date(System.currentTimeMillis() - 1);

		Integer jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findJob(jobId);

		Integer auditorId = request.getPrincipal().getActiveRoleId();
		Auditor auditor = this.repository.findAuditor(auditorId);

		entity.setJob(job);
		entity.setAuditor(auditor);
		entity.setCreationMoment(d);

		this.repository.save(entity);

	}

}
