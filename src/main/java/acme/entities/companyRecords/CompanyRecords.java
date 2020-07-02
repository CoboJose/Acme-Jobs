
package acme.entities.companyRecords;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "rating"), @Index(columnList = "workSector")
})
public class CompanyRecords extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				companyName;

	@NotBlank
	private String				workSector;

	@NotBlank
	private String				ceoName;

	@NotBlank
	private String				activityDesc;

	@NotBlank
	@URL
	private String				webSite;

	@NotBlank
	@Pattern(regexp = "^([+]([1-9][0-9]{1,2}|[0-9]{0,2}[1-9]|[0-9][1-9][0-9])\\s)?([(][0-9]{1,4}[)]\\s)?([0-9]{6,10})$", flags = Flag.UNICODE_CASE)
	private String				contactPhone;

	@NotBlank
	@Email
	private String				contactEmail;

	@NotNull
	private Boolean				incorporated;

	@Range(min = 0, max = 5)
	private Integer				rating;


	@Transient
	public String getFullName() {
		StringBuilder result;

		result = new StringBuilder();
		result.append(this.getCompanyName());

		if (this.getIncorporated()) {
			result.append(", Inc.");
		} else {
			result.append(", LLC");
		}

		return result.toString();
	}
}
