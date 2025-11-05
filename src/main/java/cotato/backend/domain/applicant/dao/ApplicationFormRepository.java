package cotato.backend.domain.applicant.dao;

import cotato.backend.domain.applicant.dto.response.ApplicationFormListResponse;
import cotato.backend.domain.applicant.entity.ApplicationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long> {

    @Query("SELECT new cotato.backend.domain.applicant.dto.response.ApplicationFormListResponse(" +
            "f.id, f.applicant.name, f.generation, f.part, COUNT(l)) " +
            "FROM ApplicationForm f " +
            "LEFT JOIN ApplicationFormLike l ON l.applicationForm.id = f.id " +
            "WHERE f.generation = :generation " +
            "GROUP BY f.id, f.applicant.name, f.generation, f.part")
    Page<ApplicationFormListResponse> findByGenerationWithLikeCount(
            @Param("generation") Integer generation,
            Pageable pageable
    );

    @Query("SELECT new cotato.backend.domain.applicant.dto.response.ApplicationFormListResponse(" +
            "f.id, f.applicant.name, f.generation, f.part, COUNT(l)) " +
            "FROM ApplicationForm f " +
            "LEFT JOIN ApplicationFormLike l ON l.applicationForm.id = f.id " +
            "GROUP BY f.id, f.applicant.name, f.generation, f.part " +
            "ORDER BY COUNT(l) DESC")
    Page<ApplicationFormListResponse> findAllOrderByLikes(Pageable pageable);

    @Query("SELECT new cotato.backend.domain.applicant.dto.response.ApplicationFormListResponse(" +
            "f.id, f.applicant.name, f.generation, f.part, COUNT(l)) " +
            "FROM ApplicationForm f " +
            "LEFT JOIN ApplicationFormLike l ON l.applicationForm.id = f.id " +
            "WHERE f.generation = :generation " +
            "GROUP BY f.id, f.applicant.name, f.generation, f.part " +
            "ORDER BY COUNT(l) DESC")
    Page<ApplicationFormListResponse> findByGenerationOrderByLikes(
            @Param("generation") Integer generation,
            Pageable pageable
    );

    @Query("SELECT new cotato.backend.domain.applicant.dto.response.ApplicationFormListResponse(" +
            "f.id, f.applicant.name, f.generation, f.part, COUNT(l)) " +
            "FROM ApplicationForm f " +
            "LEFT JOIN ApplicationFormLike l ON l.applicationForm.id = f.id " +
            "GROUP BY f.id, f.applicant.name, f.generation, f.part " +
            "ORDER BY f.submittedAt DESC")
    Page<ApplicationFormListResponse> findAllOrderBySubmittedAtDesc(Pageable pageable);

    @Query("SELECT new cotato.backend.domain.applicant.dto.response.ApplicationFormListResponse(" +
            "f.id, f.applicant.name, f.generation, f.part, COUNT(l)) " +
            "FROM ApplicationForm f " +
            "LEFT JOIN ApplicationFormLike l ON l.applicationForm.id = f.id " +
            "GROUP BY f.id, f.applicant.name, f.generation, f.part " +
            "ORDER BY f.submittedAt ASC")
    Page<ApplicationFormListResponse> findAllOrderBySubmittedAtAsc(Pageable pageable);

    @Query("SELECT new cotato.backend.domain.applicant.dto.response.ApplicationFormListResponse(" +
            "f.id, f.applicant.name, f.generation, f.part, COUNT(l)) " +
            "FROM ApplicationForm f " +
            "LEFT JOIN ApplicationFormLike l ON l.applicationForm.id = f.id " +
            "WHERE f.generation = :generation " +
            "GROUP BY f.id, f.applicant.name, f.generation, f.part " +
            "ORDER BY f.submittedAt DESC")
    Page<ApplicationFormListResponse> findByGenerationOrderBySubmittedAtDesc(
            @Param("generation") Integer generation,
            Pageable pageable
    );

    @Query("SELECT new cotato.backend.domain.applicant.dto.response.ApplicationFormListResponse(" +
            "f.id, f.applicant.name, f.generation, f.part, COUNT(l)) " +
            "FROM ApplicationForm f " +
            "LEFT JOIN ApplicationFormLike l ON l.applicationForm.id = f.id " +
            "WHERE f.generation = :generation " +
            "GROUP BY f.id, f.applicant.name, f.generation, f.part " +
            "ORDER BY f.submittedAt ASC")
    Page<ApplicationFormListResponse> findByGenerationOrderBySubmittedAtAsc(
            @Param("generation") Integer generation,
            Pageable pageable
    );

    // 지원자 정보로 지원서 조회 (이름 + 전화번호 + 기수)
    Optional<ApplicationForm> findByApplicantNameAndApplicantPhoneNumAndGeneration(
            String name,
            String phoneNum,
            Integer generation
    );
}
