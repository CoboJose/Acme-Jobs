
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	// D02 Attributes:
	String[]					gridLabels;
	String[]					dataInvestor;
	String[]					dataCompany;
	Integer						numberAnnouncement;
	Integer						numberCompanyRecords;
	Integer						numberInvestorRecord;

	// D04 Attributes:
	Double						avgNumberJobsPerEmployer;
	Double						avgNumberApplicationsPerEmployer;
	Double						avgNumberApplicationsPerWorker;
	String[]					statusApplicationLabels;
	String[]					statusJobLabels;
	String[]					dataJob;
	String[]					dataApplication;

	//D05 Attributes:
	Integer[]					pendingApplicationData;
	String[]					pendingApplicationLabels;
	Integer						sizePending;

	Integer[]					acceptedApplicationData;
	String[]					acceptedApplicationLabels;
	Integer						sizeAccepted;

	Integer[]					rejectedApplicationData;
	String[]					rejectedApplicationLabels;
	Integer						sizeRejected;

	Integer						maxGraph;
}
