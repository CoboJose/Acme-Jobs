
package acme.features.administrator.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goldGoal", "goldReward", "silverGoal", "silverReward", "bronzeGoal", "bronzeReward");
	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;

		Challenge result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//DEADLINE
		if (!errors.hasErrors("deadline")) {
			boolean isAfter;
			isAfter = entity.getDeadline().after(new Date(System.currentTimeMillis()));
			errors.state(request, isAfter, "deadline", "administrator.challenge.error.deadline-must-be-in-the-future");
		}

		//REWARDS
		if (!errors.hasErrors("bronzeReward")) {
			boolean bronzeInEuros;
			Money bronzeReward = entity.getBronzeReward();
			bronzeInEuros = bronzeReward.getCurrency().equals("€") || bronzeReward.getCurrency().equals("EUR");
			errors.state(request, bronzeInEuros, "bronzeReward", "administrator.challenge.error.reward-must-be-in-euros");
		}

		if (!errors.hasErrors("silverReward")) {
			boolean silverInEuros;
			Money silverReward = entity.getSilverReward();
			silverInEuros = silverReward.getCurrency().equals("€") || silverReward.getCurrency().equals("EUR");
			errors.state(request, silverInEuros, "silverReward", "administrator.challenge.error.reward-must-be-in-euros");
		}

		if (!errors.hasErrors("goldReward")) {
			boolean goldInEuros;
			Money goldReward = entity.getGoldReward();
			goldInEuros = goldReward.getCurrency().equals("€") || goldReward.getCurrency().equals("EUR");
			errors.state(request, goldInEuros, "goldReward", "administrator.challenge.error.reward-must-be-in-euros");
		}
	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
