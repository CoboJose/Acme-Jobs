
package acme.features.administrator.companyRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecords;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorCompanyRecordDeleteService implements AbstractDeleteService<Administrator, CompanyRecords> {

	@Autowired
	AdministratorCompanyRecordRepository repository;


	@Override
	public boolean authorise(final Request<CompanyRecords> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CompanyRecords> request, final CompanyRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
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
	public CompanyRecords findOne(final Request<CompanyRecords> request) {
		assert request != null;

		CompanyRecords result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<CompanyRecords> request, final CompanyRecords entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<CompanyRecords> request, final CompanyRecords entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
