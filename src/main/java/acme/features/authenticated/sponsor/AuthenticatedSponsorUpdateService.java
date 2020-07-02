
package acme.features.authenticated.sponsor;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedSponsorUpdateService implements AbstractUpdateService<Authenticated, Sponsor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedSponsorRepository repository;


	@Override
	public boolean authorise(final Request<Sponsor> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Sponsor> request, final Sponsor entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "organisationName");

		CreditCard cc = this.repository.findOneCreditCardBySponsorId(this.repository.findOneSponsorByUserAccountId(request.getPrincipal().getAccountId()).getId());
		if (request.isMethod(HttpMethod.GET) && cc != null) {
			model.setAttribute("creditCardNumber", cc.getCreditCardNumber());
			model.setAttribute("cardHolder", cc.getCardHolder());
			model.setAttribute("cvv", cc.getCvv());
			model.setAttribute("expirationDate", cc.getExpirationDate());
		}
	}

	@Override
	public Sponsor findOne(final Request<Sponsor> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Sponsor result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		result = this.repository.findOneSponsorByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void validate(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean creditCardNumberNotBlank, cardHolderNotBlank, cvvNotBlank, expirationDateNotBlank;

		if (!request.getModel().getString("creditCardNumber").isEmpty() || !request.getModel().getString("cardHolder").isEmpty() || !request.getModel().getString("cvv").isEmpty() || !request.getModel().getString("expirationDate").isEmpty()) {
			if (!errors.hasErrors("creditCardNumber")) {
				creditCardNumberNotBlank = request.getModel().getString("creditCardNumber").trim().isEmpty();
				if (creditCardNumberNotBlank) {
					String errorMsg = request.getLocale().getDisplayLanguage().equals("English") ? "Must not be blank" : "No puede estar vacío";
					errors.add("creditCardNumber", errorMsg);
				} else if (!this.isFake(request.getModel().getString("creditCardNumber"))) {
					String errorMsg = request.getLocale().getDisplayLanguage().equals("English") ? "The credit card number cannot be fake" : "El número de la tarjeta no puede ser falso";
					errors.add("creditCardNumber", errorMsg);
				}
			}
			if (!errors.hasErrors("cardHolder")) {
				cardHolderNotBlank = request.getModel().getString("cardHolder").trim().isEmpty();
				if (cardHolderNotBlank) {
					String errorMsg = request.getLocale().getDisplayLanguage().equals("English") ? "Must not be blank" : "No puede estar vacío";
					errors.add("cardHolder", errorMsg);
				} else if (!request.getModel().getString("cardHolder").matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ]+\\s{1}[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\\s{1}[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*$")) {
					errors.state(request, false, "cardHolder", "authenticated.sponsor.error.cardHolder");
				}
			}
			if (!errors.hasErrors("cvv")) {
				cvvNotBlank = request.getModel().getString("cvv").trim().isEmpty();
				if (cvvNotBlank) {
					String errorMsg = request.getLocale().getDisplayLanguage().equals("English") ? "Must not be blank" : "No puede estar vacío";
					errors.add("cvv", errorMsg);
				} else if (!request.getModel().getString("cvv").matches("^[0-9]{3}$")) {
					errors.state(request, false, "cvv", "authenticated.sponsor.error.cvv");
				}
			}
			if (!errors.hasErrors("expirationDate")) {
				expirationDateNotBlank = request.getModel().getString("expirationDate").trim().isEmpty();
				if (expirationDateNotBlank) {
					String errorMsg = request.getLocale().getDisplayLanguage().equals("English") ? "Must not be blank" : "No puede estar vacío";
					errors.add("expirationDate", errorMsg);
				} else if (!request.getModel().getString("expirationDate").matches("^((0[1-9]{1})|(1[0-2]{1}))\\/[0-9]{1}[0-9]{1}$")) {
					errors.state(request, false, "expirationDate", "authenticated.sponsor.error.the-card-format");
				} else {
					String[] expirationDate = request.getModel().getString("expirationDate").split("/");
					Integer actualYear = Calendar.getInstance().get(Calendar.YEAR);
					String expirationYear;
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
					errors.state(request, isAfter, "expirationDate", "authenticated.sponsor.error.the-card-has-expired");
				}
			}
		}
	}

	@Override
	public void update(final Request<Sponsor> request, final Sponsor entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		if (!request.getModel().getString("creditCardNumber").isEmpty() && !request.getModel().getString("cardHolder").isEmpty() && !request.getModel().getString("cvv").isEmpty() && !request.getModel().getString("expirationDate").isEmpty()) {
			CreditCard cc = new CreditCard();
			if (this.repository.findOneCreditCardBySponsorId(entity.getId()) == null) {
				cc.setSponsor(entity);
			} else {
				cc = this.repository.findOneCreditCardBySponsorId(entity.getId());
			}
			cc.setCreditCardNumber(request.getModel().getString("creditCardNumber"));
			cc.setCardHolder(request.getModel().getString("cardHolder"));
			cc.setCvv(request.getModel().getString("cvv"));
			cc.setExpirationDate(request.getModel().getString("expirationDate"));
			this.repository.save(cc);
		} else if (this.repository.findOneCreditCardBySponsorId(entity.getId()) != null && request.getModel().getString("creditCardNumber").isEmpty() && request.getModel().getString("cardHolder").isEmpty() && request.getModel().getString("cvv").isEmpty()
			&& request.getModel().getString("expirationDate").isEmpty()) {
			CreditCard cc = this.repository.findOneCreditCardBySponsorId(entity.getId());
			this.repository.delete(cc);
		}

		this.repository.save(entity);
	}
	@Override
	public void onSuccess(final Request<Sponsor> request, final Response<Sponsor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
	private boolean isFake(final String string) {
		boolean result = string.length() == 16;
		if (result) {
			int sum = 0;
			int aux = 0;
			char[] n = string.toCharArray();
			for (int i = 0; i < n.length - 1; i++) {
				if (i == 0 || i % 2 == 0) {
					aux = Character.getNumericValue(n[i]);
					aux = aux * 2;
					if (aux > 9) {
						aux = aux - 9;
					}
				} else {
					aux = Character.getNumericValue(n[i]);
				}
				sum += aux;
			}
			result = sum * 9 % 10 == Character.getNumericValue(n[n.length - 1]);
		}
		return result;
	}

}
