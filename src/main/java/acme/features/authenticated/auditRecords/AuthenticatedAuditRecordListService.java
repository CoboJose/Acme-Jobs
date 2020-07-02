
package acme.features.authenticated.auditRecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuditRecordListService implements AbstractListService<Authenticated, AuditRecords> {

	@Autowired
	AuthenticatedAuditRecordRepository repository;


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

		request.unbind(entity, model, "title", "creationMoment", "auditor.userAccount.username");
	}

	@Override
	public Collection<AuditRecords> findMany(final Request<AuditRecords> request) {

		assert request != null;

		Collection<AuditRecords> result;
		int jobId = request.getModel().getInteger("id");

		result = this.repository.findManyAuditRecordsAndPublsihed(jobId);

		return result;
	}

}
