package cotato.backend.domain.like.dao;

import cotato.backend.domain.application.entity.ApplicationEntity;
import cotato.backend.domain.like.entity.LikeEntity;
import cotato.backend.domain.staff.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    // 좋아요 중복 확인
    boolean existsByApplicationAndStaff(ApplicationEntity application, StaffEntity staff);

}