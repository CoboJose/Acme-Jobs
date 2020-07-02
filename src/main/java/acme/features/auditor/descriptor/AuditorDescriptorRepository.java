
package acme.features.auditor.descriptor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Descriptor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorDescriptorRepository extends AbstractRepository {

	@Query("select d from Descriptor d where d.job.id = ?1")
	Descriptor findOneDescriptorById(int jobId);
}
