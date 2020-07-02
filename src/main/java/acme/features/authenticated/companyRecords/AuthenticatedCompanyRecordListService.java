
package acme.features.authenticated.companyRecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedCompanyRecordListService implements AbstractListService<Authenticated, CompanyRecords> {

	@Autowired
	AuthenticatedCompanyRecordRepository repository;


	@Override
	public boolean authorise(final Request<CompanyRecords> request) {

		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CompanyRecords> request, final CompanyRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		//request.unbind(entity, model, "companyName", "workSector", "ceoName", "activityDesc", "webSite", "contactPhone", "contactEmail", "incorporated");
		request.unbind(entity, model, "companyName", "workSector", "activityDesc", "webSite", "rating", "fullName");
	}

	@Override
	public Collection<CompanyRecords> findMany(final Request<CompanyRecords> request) {
		assert request != null;

		Collection<CompanyRecords> result;

		result = this.repository.findMany();

		return result;
	}

}
