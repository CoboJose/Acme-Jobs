
package acme.entities.roles;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Worker extends UserRole {

	//Serialisation Identifier
	private static final long	serialVersionUID	= 1L;

	//Atributes:

	@NotBlank
	private String				qualifications;

	@NotBlank
	private String				skills;


	@Transient
	public String getFullName() {
		return this.getIdentity().getFullName();
	}
}
