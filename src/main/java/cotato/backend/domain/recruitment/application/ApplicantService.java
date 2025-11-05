package cotato.backend.domain.recruitment.application;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.domain.recruitment.dao.ApplicantRepository;
import cotato.backend.domain.recruitment.dao.ApplicationRepository;
import cotato.backend.domain.recruitment.dto.response.ApplicantDetailResponseDTO;
import cotato.backend.domain.recruitment.dto.response.ApplicationDetailResponseDTO;
import cotato.backend.domain.recruitment.entity.Applicant;
import cotato.backend.domain.recruitment.entity.Application;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicantService {

	private final ApplicantRepository applicantRepository;


	public ApplicantDetailResponseDTO getApplicant(Long applicantId) {

		// applicantId로 찾은 Optional 객체 값이 비어있는 경우 예외 발생, 값이 존재하는 경우 변수 applicant에 값 저장
		Applicant applicant = applicantRepository.findByApplicantId(applicantId)
			.orElseThrow(() -> new NoSuchElementException("Applicant with ID " + applicantId + " does not exist"));

		return new ApplicantDetailResponseDTO(
			applicant.getName(), applicant.getAge(), applicant.getPhoneNumber()
		);
	}
}
