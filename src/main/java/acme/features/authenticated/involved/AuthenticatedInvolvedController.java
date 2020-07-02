
package acme.features.authenticated.involved;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.involved.Involved;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/involved/")
public class AuthenticatedInvolvedController extends AbstractController<Authenticated, Involved> {

	@Autowired
	private AuthenticatedInvolvedListService	listService;
	@Autowired
	private AuthenticatedInvolvedShowService	showService;
	@Autowired
	private AuthenticatedInvolvedCreateService	createService;
	@Autowired
	private AuthenticatedInvolvedDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}
}
