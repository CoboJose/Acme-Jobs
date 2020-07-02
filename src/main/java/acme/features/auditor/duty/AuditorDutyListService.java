
package acme.features.auditor.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class AuditorDutyListService implements AbstractListService<Auditor, Duty> {

	@Autowired
	AuditorDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "titleDuty", "descriptionDuty");
	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {

		assert request != null;

		Collection<Duty> result;
		int descriptorId;

		descriptorId = request.getModel().getInteger("descriptorId");
		result = this.repository.findDuty(descriptorId);

		return result;
	}

}
