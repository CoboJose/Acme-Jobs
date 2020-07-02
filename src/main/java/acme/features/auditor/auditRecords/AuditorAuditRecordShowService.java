
package acme.features.auditor.auditRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.auditRecords.Status;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditRecordShowService implements AbstractShowService<Auditor, AuditRecords> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecords> request) {
		assert request != null;

		boolean result;

		int auditId;
		Auditor auditor;
		AuditRecords auditRecord;
		Principal principal;

		principal = request.getPrincipal();
		auditId = request.getModel().getInteger("id");
		auditRecord = this.repository.findOne(auditId);
		auditor = auditRecord.getAuditor();
		result = auditRecord.getStatus() == Status.PUBLISHED || auditRecord.getStatus() == Status.DRAFT && auditor.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<AuditRecords> request, final AuditRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Principal principal = request.getPrincipal();

		model.setAttribute("accountId", principal.getActiveRoleId());
		Integer auditId = request.getModel().getInteger("id");
		AuditRecords auditRecord = this.repository.findOne(auditId);
		model.setAttribute("auditorId", auditRecord.getAuditor().getId());
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
