
package acme.entities.investorRecords;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "stars"), @Index(columnList = "sector")
})
public class InvestorRecord extends DomainEntity {

	//Serialisation identifer
	private static final long	serialVersionUID	= 1L;
	//Attributes
	@NotBlank
	private String				name;
	@NotBlank
	private String				sector;
	@NotBlank
	private String				statement;
	@Range(min = 0, max = 5)
	private Integer				stars;

}
