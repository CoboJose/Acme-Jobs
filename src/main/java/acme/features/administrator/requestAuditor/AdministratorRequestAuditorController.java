
package acme.features.administrator.requestAuditor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.RequestAuditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/request-auditor/")
public class AdministratorRequestAuditorController extends AbstractController<Administrator, RequestAuditor> {

	@Autowired
	private AdministratorRequestAuditorShowService		showService;

	@Autowired
	private AdministratorRequestAuditorListService		listService;

	@Autowired
	private AdministratorRequestAuditorCreateService	createService;

	@Autowired
	private AdministratorRequestAuditorDeleteService	deleteService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
