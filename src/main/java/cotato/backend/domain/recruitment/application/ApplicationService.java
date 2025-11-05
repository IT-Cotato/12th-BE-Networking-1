package cotato.backend.domain.recruitment.application;

import static cotato.backend.domain.recruitment.entity.enums.FilterBy.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.api.dto.request.SubmitApplicationDTO;
import cotato.backend.api.dto.response.ApplicationListDTO;
import cotato.backend.api.dto.response.ApplicationListResponseDTO;
import cotato.backend.domain.recruitment.dao.ApplicantRepository;
import cotato.backend.domain.recruitment.dao.ApplicationRepository;
import cotato.backend.domain.recruitment.dto.response.ApplicationDetailResponseDTO;
import cotato.backend.domain.recruitment.entity.Applicant;
import cotato.backend.domain.recruitment.entity.Application;
import cotato.backend.domain.recruitment.entity.enums.FilterBy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationService {

	private final ApplicationRepository applicationRepository;

	private final ApplicantRepository applicantRepository;

	@Transactional
	public Long submitApplication(SubmitApplicationDTO submitApplicationDTO) {

		Integer age = submitApplicationDTO.age();

		Applicant applicant = applicantRepository.findByPhoneNumber(submitApplicationDTO.phoneNumber())
			.map(existing -> {
				// 만약 이미 저장되어있는 지원자인데 DB에 저장된 나이와 현재 나이가 다르다면,
				// 연도가 바뀌고 재지원한 것이기 때문에 나이를 현재 나이로 수정해준다.
				if (existing.getAge() != submitApplicationDTO.age()) {
					existing.updateAge(submitApplicationDTO.age());
				}
				return existing;
			})
			.orElseGet(() -> applicantRepository.save(
				// 새롭게 지원한 지원자일 경우 입력된 값을 기반으로 DB에 저장한다.
				Applicant.builder()
					.name(submitApplicationDTO.name())
					.age(submitApplicationDTO.age())
					.phoneNumber(submitApplicationDTO.phoneNumber())
					.build()
			));

		Application application = Application.builder()
			.applicant(applicant)
			.period(submitApplicationDTO.period())
			.part(submitApplicationDTO.part())
			.ability(submitApplicationDTO.ability())
			.passion(submitApplicationDTO.passion())
			.applicationTime(LocalDateTime.now())
			.build();

		return applicationRepository.save(application).getApplicationId();
	}

	public ApplicationDetailResponseDTO getApplication(Long applicationId) {

		// applicationId로 찾은 Optional 객체 값이 비어있는 경우 예외 발생, 값이 존재하는 경우 변수 application에 값 저장
		Application application = applicationRepository.findWithApplicantByApplicationId(applicationId)
		    .orElseThrow(() -> new NoSuchElementException("Application with ID " + applicationId + " does not exist"));

		Applicant applicant = application.getApplicant();

		return new ApplicationDetailResponseDTO(
			applicant.getName(), application.getPeriod(), applicant.getAge(), application.getPart(),
			application.getAbility(), application.getPassion(), applicant.getPhoneNumber(), application.getApplicationTime()
		);
	}

	public ApplicationListResponseDTO getApplicationList(FilterBy filterBy, Integer period, Integer page) {

		// 세 가지 필터링 모두 10건씩 조회한다는 요구사항이 동일하므로 pageSize값을 10으로 고정함
		Integer pageSize = 10;

		// 페이지 객체 생성
		Pageable pageable = PageRequest.of(page, pageSize);

		Page<ApplicationListDTO> result;

		switch (filterBy) {
				case period:
					// 특정 기수를 대상으로 최신순 조회
					result = applicationRepository
						.findByPeriodOrderByApplicationTimeDesc(period, pageable);

					return new ApplicationListResponseDTO(
						filterBy,
						page + 1,
						result.getContent()
					);
				case likes :
					// 전체 기수를 대상으로 좋아요순 조회
					result = applicationRepository
						.findAllOrderByLikeCountDesc(pageable);

					return new ApplicationListResponseDTO(
						filterBy,
						page + 1,
						result.getContent()
					);
				case both :
					// 특정 기수를 대상으로 좋아요순 조회
					result = applicationRepository
						.findByPeriodOrderByLikeCountDesc(period, pageable);

					return new ApplicationListResponseDTO(
						filterBy,
						page + 1,
						result.getContent()
					);
				default :
					throw new IllegalArgumentException("유효하지 않은 필터 기준입니다: " + filterBy);
		}
	}
}