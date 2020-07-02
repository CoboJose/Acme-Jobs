
package acme.features.authenticated.auditRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecords;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecords a WHERE a.job.id = ?1")
	Collection<AuditRecords> findManyAuditRecords(int jobId);

	@Query("select a from AuditRecords a WHERE a.job.id = ?1 and a.status = 1")
	Collection<AuditRecords> findManyAuditRecordsAndPublsihed(int jobId);

	@Query("select a from AuditRecords a WHERE a.id = ?1")
	AuditRecords findOne(int auditId);
}
