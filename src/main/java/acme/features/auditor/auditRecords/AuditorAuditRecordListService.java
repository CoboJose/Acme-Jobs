
package acme.features.auditor.auditRecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditRecordListService implements AbstractListService<Auditor, AuditRecords> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecords> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AuditRecords> request, final AuditRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer principalId = request.getPrincipal().getActiveRoleId();
		model.setAttribute("principalId", principalId);
		request.unbind(entity, model, "title", "creationMoment", "status", "auditor.userAccount.username", "auditor.id");
	}

	@Override
	public Collection<AuditRecords> findMany(final Request<AuditRecords> request) {

		assert request != null;
		Principal principal;
		Collection<AuditRecords> result;
		int jobId = request.getModel().getInteger("jobId");
		principal = request.getPrincipal();

		result = this.repository.findManyPublishedAuditRecords(jobId, principal.getActiveRoleId());

		return result;
	}

}
