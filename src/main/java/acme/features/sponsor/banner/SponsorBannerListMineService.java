
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.banners.NonCommercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SponsorBannerListMineService implements AbstractListService<Sponsor, Banner> {

	@Autowired
	SponsorBannerRepository repository;


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

		Principal principal;
		principal = request.getPrincipal();
		Boolean hasCreditCard = this.repository.hasCreditCardBySponsorId(principal.getActiveRoleId());

		model.setAttribute("hasCreditCard", hasCreditCard);
		request.unbind(entity, model, "picture", "slogan");

	}

	@Override
	public Collection<Banner> findMany(final Request<Banner> request) {
		assert request != null;

		Collection<Banner> result;
		Principal principal;

		principal = request.getPrincipal();

		result = this.repository.findManyBySponsorId(principal.getActiveRoleId());

		if (result.isEmpty()) {
			result.add(new NonCommercial());
		}

		return result;
	}

}
