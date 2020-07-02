
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneDutyById(int dutyId);

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> findDuty(int descriptorId);

	@Query("select d from Descriptor d where d.id = ?1")
	Descriptor findOneDescriptorById(int descriptorId);

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> findManyDutiesByDescriptorId(int descriptorId);

	@Query("select sum(d.percentage) from Duty d where d.descriptor.id = ?1 and d.id != ?2")
	Integer sumDutiesByDescriptorId(int descriptorId, int dutyId);

	@Query("select sum(d.percentage) from Duty d where d.descriptor.id = ?1")
	Integer sumDutiesCreateByDescriptorId(int descriptorId);

	@Query("select d from Duty d where d.descriptor.job.id = ?1")
	Collection<Duty> findManyDutiesByJobId(int jobId);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int jobId);

	@Query("select d from Descriptor d where d.job.id = ?1")
	Descriptor findOneDescriptorByJobId(int jobId);

}
