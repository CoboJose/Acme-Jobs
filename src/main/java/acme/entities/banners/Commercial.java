
package acme.entities.banners;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Commercial extends Banner {

	private static final long	serialVersionUID	= 1L;

	@CreditCardNumber
	@NotBlank
	private String				creditCardNumber;

	@Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ]+\\s{1}[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\\s{1}[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*$")
	@NotBlank
	private String				cardHolder;

	@Pattern(regexp = "^[0-9]{3}$")
	@NotBlank
	private String				cvv;

	@Pattern(regexp = "^((0[1-9]{1})|(1[0-2]{1}))\\/[0-9]{1}[0-9]{1}$")
	@NotBlank
	private String				expirationDate;
}
