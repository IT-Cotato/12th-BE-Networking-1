package cotato.backend.appllication.repository;

import cotato.backend.appllication.entity.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // 지원 기수별 10건씩 조회
    List<Application> findByGenerationOrderByIdDesc(int generation, Pageable pageable);

    // 좋아요 수 기준 10건씩 조회
    List<Application> findAllByOrderByLikeCountDesc(Pageable pageable);

    // 지원기수 + 좋아요 복합정렬
    List<Application> findByGenerationOrderByLikeCountDesc(int generation, Pageable pageable);
}
