
package acme.features.authenticated.companyRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companyRecords.CompanyRecords;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedCompanyRecordRepository extends AbstractRepository {

	@Query("select c from CompanyRecords c")
	Collection<CompanyRecords> findMany();

	@Query("select c from CompanyRecords c where c.id = ?1")
	CompanyRecords findOneById(int id);

}
