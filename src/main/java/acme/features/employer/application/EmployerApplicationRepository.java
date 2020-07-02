
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.job.employer.id = ?1")
	Collection<Application> findManyByEmployerId(int employerId);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.reference")
	Collection<Application> findAppToMyJobsGroupByReference(int employerId);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.status")
	Collection<Application> findAppToMyJobsGroupByStatus(int employerId);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.moment")
	Collection<Application> findAppToMyJobsGroupByCreationMoment(int employerId);

}
