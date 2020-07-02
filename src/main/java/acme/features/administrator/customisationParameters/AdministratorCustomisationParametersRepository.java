
package acme.features.administrator.customisationParameters;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomisationParametersRepository extends AbstractRepository {

	@Query("select c from CustomisationParameters c where c.identifier = ?1")
	CustomisationParameters findOne(Integer identifier);

	@Query("select c from CustomisationParameters c where c.id = ?1")
	CustomisationParameters findOneById(Integer id);

}
