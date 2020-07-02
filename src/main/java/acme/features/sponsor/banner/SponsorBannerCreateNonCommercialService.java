
package acme.features.sponsor.banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.banners.NonCommercial;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorBannerCreateNonCommercialService implements AbstractCreateService<Sponsor, Banner> {

	@Autowired
	SponsorBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "target", "jingle");
	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		NonCommercial result = new NonCommercial();

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("slogan")) {
			errors.state(request, !this.isSpam(entity.getSlogan()), "slogan", "sponsor.banner.error.it-is-spam");
		}
	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
		assert request != null;
		
		Principal principal;
		principal = request.getPrincipal();
		Sponsor s = this.repository.findOneSponsorById(principal.getActiveRoleId());

		entity.setSponsor(s);

		this.repository.save(entity);
	}

	private boolean isSpam(final String word) {

		boolean result = false;
		List<String> spamList = new ArrayList<String>();
		List<String> words = new ArrayList<>();
		Float spamThreshold;
		CustomisationParameters cp = this.repository.findOneCustomisationParameters();
		float count = 0;

		spamList = Arrays.asList(cp.getSpamList().trim().split(";"));
		words = Arrays.asList(word.split(" "));
		spamThreshold = cp.getSpamThreshold();

		for (String s : spamList) {
			for (String w : words) {
				count += w.equalsIgnoreCase(s) ? 1 : 0;
			}
		}

		float numberWords = words.size();
		float spamPorcentage = count / numberWords;

		result = spamPorcentage >= spamThreshold;

		return result;
	}

}
