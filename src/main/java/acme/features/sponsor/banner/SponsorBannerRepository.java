
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorBannerRepository extends AbstractRepository {

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor findOneSponsorById(int id);

	@Query("select case when count(c) > 0 then true else false end from CreditCard c where c.sponsor.id = ?1")
	Boolean hasCreditCardBySponsorId(int id);

	@Query("select b from Banner b where b.id = ?1")
	Banner findOneBannerById(int id);

	@Query("select b from Banner b where b.sponsor.id = ?1")
	Collection<Banner> findManyBySponsorId(int sponsorId);

	@Query("select cp from CustomisationParameters cp where cp.identifier = '1'")
	CustomisationParameters findOneCustomisationParameters();

	@Query("select c from CreditCard c where c.sponsor.id = ?1")
	CreditCard findOneCreditCardBySponsorId(int sponsorId);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findOneSponsorByUserAccountId(int id);
}
