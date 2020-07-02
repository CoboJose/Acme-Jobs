
package acme.features.administrator.dashboard;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	//Internal state
	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "gridLabels", "dataInvestor", "dataCompany", "statusApplicationLabels", "statusJobLabels", "dataJob", "dataApplication", "pendingApplicationData", "pendingApplicationLabels", "sizePending", "acceptedApplicationData",
			"acceptedApplicationLabels", "sizeAccepted", "maxGraph", "numberAnnouncement", "rejectedApplicationData", "rejectedApplicationLabels", "sizeRejected", "numberCompanyRecords", "numberInvestorRecord",
			"avgNumberJobsPerEmployer", "avgNumberApplicationsPerEmployer", "avgNumberApplicationsPerWorker");
	}

	@SuppressWarnings("deprecation")
	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Date d = Calendar.getInstance().getTime();

		//DO2:
		Dashboard result;
		result = new Dashboard();

		//D04:

		//D05:

		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -28);
		Date d2 = now.getTime();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateToStringMin = formatter.format(d2).toString();
		String dateToStringMax = formatter.format(d).toString();

		String[] pendingLabels = this.repository.pendingApplicationsLabels();
		Integer[] pendingValues = this.repository.pendingApplicationsValue();

		if (!ArrayUtils.contains(pendingLabels, dateToStringMin)) {

			pendingLabels = ArrayUtils.add(pendingLabels, 0, dateToStringMin);
			pendingValues = ArrayUtils.add(pendingValues, 0, 0);
		}

		if (!ArrayUtils.contains(pendingLabels, dateToStringMax)) {

			pendingLabels = ArrayUtils.add(pendingLabels, dateToStringMax);
			pendingValues = ArrayUtils.add(pendingValues, 0);
		}

		Integer maxPending = Arrays.stream(pendingValues).max(Comparator.naturalOrder()).get();

		result.setPendingApplicationData(pendingValues);
		result.setPendingApplicationLabels(pendingLabels);
		result.setSizePending(pendingValues.length - 1);

		String[] acceptedLabels = this.repository.acceptedApplicationsLabels();
		Integer[] acceptedValues = this.repository.acceptedApplicationsValue();

		if (!ArrayUtils.contains(acceptedLabels, dateToStringMin)) {

			acceptedLabels = ArrayUtils.add(acceptedLabels, 0, dateToStringMin);
			acceptedValues = ArrayUtils.add(acceptedValues, 0, 0);
		}

		if (!ArrayUtils.contains(acceptedLabels, dateToStringMax)) {

			acceptedLabels = ArrayUtils.add(acceptedLabels, dateToStringMax);
			acceptedValues = ArrayUtils.add(acceptedValues, 0);
		}

		Integer maxAccepted = Arrays.stream(acceptedValues).max(Comparator.naturalOrder()).get();

		result.setAcceptedApplicationData(acceptedValues);
		result.setAcceptedApplicationLabels(acceptedLabels);
		result.setSizeAccepted(acceptedLabels.length - 1);

		String[] rejectedLabels = this.repository.rejectedApplicationsLabels();
		Integer[] rejectedValues = this.repository.rejectedApplicationsValue();

		if (!ArrayUtils.contains(rejectedLabels, dateToStringMin)) {

			rejectedLabels = ArrayUtils.add(rejectedLabels, 0, dateToStringMin);
			rejectedValues = ArrayUtils.add(rejectedValues, 0, 0);
		}

		if (!ArrayUtils.contains(rejectedLabels, dateToStringMax)) {

			rejectedLabels = ArrayUtils.add(rejectedLabels, dateToStringMax);
			rejectedValues = ArrayUtils.add(rejectedValues, 0);
		}

		Integer maxRejected = Arrays.stream(rejectedValues).max(Comparator.naturalOrder()).get();

		result.setRejectedApplicationData(rejectedValues);
		result.setRejectedApplicationLabels(rejectedLabels);
		result.setSizeRejected(rejectedLabels.length - 1);

		Integer[] maxVals = {
			maxAccepted, maxPending, maxRejected
		};
		result.setMaxGraph(Arrays.stream(maxVals).max(Comparator.naturalOrder()).get() + 1);

		//Listing D02:
		result.setNumberAnnouncement(this.repository.countAllAnnouncement());
		result.setNumberCompanyRecords(this.repository.countAllCompanyRecord());
		result.setNumberInvestorRecord(this.repository.countAllInvestorRecord());

		//Listing D04:
		result.setAvgNumberJobsPerEmployer(this.repository.avgNumberJobsPerEmployer() != null ? this.repository.avgNumberJobsPerEmployer() : 0.);
		result.setAvgNumberApplicationsPerEmployer(this.repository.avgNumberApplicationsPerEmployer() != null ? this.repository.avgNumberApplicationsPerEmployer() : 0.);
		result.setAvgNumberApplicationsPerWorker(this.repository.avgNumberApplicationsPerWorker() != null ? this.repository.avgNumberApplicationsPerWorker() : 0.);

		//Tablero Sectores

		String[] iSector = this.repository.investorSector();
		String[] cSector = this.repository.companySector(iSector.length != 0 ? iSector : new String[1]);
		String[] labels = iSector;
		labels = ArrayUtils.addAll(labels, cSector);

		String[] tempDataCompany = this.repository.dataCompany();
		String[] dataCompany = new String[labels.length];

		for (String element : tempDataCompany) {
			String[] s = element.split(",");

			for (int i = 0; i < labels.length; i++) {
				if (labels[i].matches(s[0])) {
					dataCompany[i] = s[1];
				}
			}
		}

		String[] dataInvestor = this.repository.dataInvestor();

		dataInvestor = ArrayUtils.addAll(dataInvestor, new String[labels.length - dataInvestor.length]);

		result.setGridLabels(labels);

		result.setDataCompany(dataCompany);

		result.setDataInvestor(dataInvestor);

		//Tablero Status

		result.setStatusJobLabels(this.repository.jobStatus());

		result.setStatusApplicationLabels(this.repository.applicationStatus());

		result.setDataJob(this.repository.dataJob());

		result.setDataApplication(this.repository.dataApplication());

		return result;
	}

}
