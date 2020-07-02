
package acme.features.sponsor.banner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.banners.Commercial;
import acme.entities.creditCards.CreditCard;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorBannerCreateCommercialService implements AbstractCreateService<Sponsor, Banner> {

	@Autowired
	SponsorBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		Principal principal;
		principal = request.getPrincipal();
		Boolean hasCreditCard = this.repository.hasCreditCardBySponsorId(principal.getActiveRoleId());

		return hasCreditCard;
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

		request.unbind(entity, model, "picture", "slogan", "target", "creditCardNumber", "cardHolder", "cvv", "expirationDate");
	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		Commercial result = new Commercial();

		CreditCard cc = this.repository.findOneCreditCardBySponsorId(this.repository.findOneSponsorByUserAccountId(request.getPrincipal().getAccountId()).getId());

		result.setCreditCardNumber(cc.getCreditCardNumber());
		result.setCardHolder(cc.getCardHolder());
		result.setCvv(cc.getCvv());
		result.setExpirationDate(cc.getExpirationDate());

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("expirationDate")) {
			String[] expirationDate = ((Commercial) entity).getExpirationDate().split("/");
			Integer actualYear = Calendar.getInstance().get(Calendar.YEAR);
			String expirationYear;

			//Suponiendo caducidad de 4 años
			if (actualYear % 100 >= 96 && actualYear % 100 <= 99) {
				expirationYear = Integer.toString((actualYear + 100) / 100) + expirationDate[1];
			} else {
				expirationYear = Integer.toString(actualYear / 100) + expirationDate[1];
			}

			Date expDate = Date.valueOf(expirationYear + "-" + expirationDate[0] + "-01");
			Calendar expCal = Calendar.getInstance();
			expCal.setTime(expDate);
			expCal.add(Calendar.MONTH, 1);

			Calendar currentCal = Calendar.getInstance();

			boolean isAfter = currentCal.before(expCal);
			errors.state(request, isAfter, "expirationDate", "sponsor.banner.error.the-card-has-expired");
		}
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
