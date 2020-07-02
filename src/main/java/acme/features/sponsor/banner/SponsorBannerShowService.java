
package acme.features.sponsor.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.banners.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorBannerShowService implements AbstractShowService<Sponsor, Banner> {

	@Autowired
	SponsorBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		boolean result;
		int bannerId;
		Banner banner;
		Sponsor sponsor;
		Principal principal;

		bannerId = request.getModel().getInteger("id");
		banner = this.repository.findOneBannerById(bannerId);
		sponsor = banner.getSponsor();
		principal = request.getPrincipal();
		result = sponsor.getUserAccount().getId() == principal.getAccountId();

		return result;
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
