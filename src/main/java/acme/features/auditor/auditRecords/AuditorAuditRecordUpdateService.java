
package acme.features.auditor.auditRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.auditRecords.Status;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditRecordUpdateService implements AbstractUpdateService<Auditor, AuditRecords> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecords> request) {
		assert request != null;

		Integer auditRecordId = request.getModel().getInteger("id");

		AuditRecords audit = this.repository.findOne(auditRecordId);

		boolean res = audit.getAuditor().getId() == request.getPrincipal().getActiveRoleId();
		boolean auditStatus = audit.getStatus() == Status.DRAFT;

		return res && auditStatus;
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

	}

	@Override
	public AuditRecords findOne(final Request<AuditRecords> request) {

		assert request != null;

		Integer auditId = request.getModel().getInteger("id");
		AuditRecords audit = this.repository.findOne(auditId);

		return audit;
	}

	@Override
	public void validate(final Request<AuditRecords> request, final AuditRecords entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<AuditRecords> request, final AuditRecords entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
