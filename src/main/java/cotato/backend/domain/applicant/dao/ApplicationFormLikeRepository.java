package cotato.backend.domain.applicant.dao;

import cotato.backend.domain.applicant.entity.ApplicationFormLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationFormLikeRepository extends JpaRepository<ApplicationFormLike, Long> {

    Optional<ApplicationFormLike> findByMemberIdAndApplicationFormId(Long memberId, Long applicationFormId);

    boolean existsByMemberIdAndApplicationFormId(Long memberId, Long applicationFormId);

    Long countByApplicationFormId(Long applicationFormId);

    List<ApplicationFormLike> findByMemberId(Long memberId);
}
