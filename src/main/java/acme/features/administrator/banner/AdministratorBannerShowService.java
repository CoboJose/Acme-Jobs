
package acme.features.administrator.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.banners.Commercial;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorBannerShowService implements AbstractShowService<Administrator, Banner> {

	@Autowired
	private AdministratorBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity instanceof Commercial) {
			request.unbind(entity, model, "picture", "slogan", "target", "creditCardNumber", "cardHolder", "cvv", "expirationDate");
		} else {
			request.unbind(entity, model, "picture", "slogan", "target", "jingle");
		}
		if (entity.getSponsor() != null) {
			request.unbind(entity, model, "sponsor.userAccount.identity.name", "sponsor.userAccount.identity.surname");
		}

	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;
		Banner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneBannerById(id);

		return result;
	}

}
