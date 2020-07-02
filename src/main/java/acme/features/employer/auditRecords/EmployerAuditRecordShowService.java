
package acme.features.employer.auditRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.auditRecords.Status;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerAuditRecordShowService implements AbstractShowService<Employer, AuditRecords> {

	@Autowired
	EmployerAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecords> request) {
		assert request != null;

		boolean result;

		int auditId;
		AuditRecords auditRecord;

		auditId = request.getModel().getInteger("id");
		auditRecord = this.repository.findOne(auditId);

		result = auditRecord.getStatus() == Status.PUBLISHED;

		return result;
	}

	@Override
	public void unbind(final Request<AuditRecords> request, final AuditRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment", "body", "auditor.userAccount.username", "auditor.userAccount.identity.fullName", "job.reference", "job.id");
	}

	@Override
	public AuditRecords findOne(final Request<AuditRecords> request) {
		assert request != null;

		AuditRecords result;
		int auditId = request.getModel().getInteger("id");
		result = this.repository.findOne(auditId);

		return result;
	}

}
