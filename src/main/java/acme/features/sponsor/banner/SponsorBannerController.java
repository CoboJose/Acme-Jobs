
package acme.features.sponsor.banner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.banners.Banner;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/banner/")
public class SponsorBannerController extends AbstractController<Sponsor, Banner> {

	@Autowired
	private SponsorBannerShowService				showService;

	@Autowired
	private SponsorBannerListMineService			listMineService;

	@Autowired
	private SponsorBannerCreateCommercialService	createCommercialService;

	@Autowired
	private SponsorBannerCreateNonCommercialService	createNonCommercialService;

	@Autowired
	private SponsorBannerUpdateCommercialService	updateCommercialService;

	@Autowired
	private SponsorBannerUpdateNonCommercialService	updateNonCommercialService;

	@Autowired
	private SponsorBannerDeleteService				deleteService;


	@PostConstruct
	private void initialise() {

		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);

		super.addBasicCommand(BasicCommand.SHOW, this.showService);

		super.addCustomCommand(CustomCommand.CREATE_COMMERCIAL, BasicCommand.CREATE, this.createCommercialService);

		super.addCustomCommand(CustomCommand.CREATE_NON_COMMERCIAL, BasicCommand.CREATE, this.createNonCommercialService);

		super.addCustomCommand(CustomCommand.UPDATE_COMMERCIAL, BasicCommand.UPDATE, this.updateCommercialService);

		super.addCustomCommand(CustomCommand.UPDATE_NON_COMMERCIAL, BasicCommand.UPDATE, this.updateNonCommercialService);

		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}
}
