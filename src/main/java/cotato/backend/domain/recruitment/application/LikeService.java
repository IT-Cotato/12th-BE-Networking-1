package cotato.backend.domain.recruitment.application;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.domain.recruitment.dao.ApplicationRepository;
import cotato.backend.domain.recruitment.dao.LikeRepository;
import cotato.backend.domain.recruitment.dao.ManagementRepository;
import cotato.backend.domain.recruitment.entity.Application;
import cotato.backend.domain.recruitment.entity.Like;
import cotato.backend.domain.recruitment.entity.Management;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeService {
	private final LikeRepository likeRepository;
	private final ApplicationRepository applicationRepository;
	private final ManagementRepository managementRepository;

	@Transactional
	public Long likeApplication(Long applicationId, Long managementId) {
		// applicationId로 찾은 Optional 객체 값이 비어있는 경우 예외 발생, 값이 존재하는 경우 변수 application에 값 저장
		Application application = applicationRepository.findByApplicationId(applicationId)
			.orElseThrow(() -> new NoSuchElementException("Application with ID " + applicationId + " does not exist"));

		// managementId로 찾은 Optional 객체 값이 비어있는 경우 예외 발생, 값이 존재하는 경우 변수 management에 값 저장
		Management management = managementRepository.findByManagementId(managementId)
			.orElseThrow(() -> new NoSuchElementException("Management with ID " + managementId + " does not exist"));


		if (likeRepository.existsByApplicationAndManagement(application, management)) {
			// 이미 해당 서류에 좋아요를 누른 운영진일 경우
			// 한 운영진이 동일한 서류에 중복으로 좋아요 누르는 것을 방지하기 위함
			throw new IllegalStateException("이미 해당 서류에 좋아요를 누른 운영진입니다.");
		}

		Like like = Like.builder()
			.application(application)
			.management(management)
			.build();

		return likeRepository.save(like).getLikeId();
	}
}
