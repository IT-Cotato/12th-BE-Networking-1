package cotato.backend.domain.recruitment.application;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cotato.backend.domain.recruitment.dao.ApplicantRepository;
import cotato.backend.domain.recruitment.dao.ManagementRepository;
import cotato.backend.domain.recruitment.dto.response.ApplicantDetailResponseDTO;
import cotato.backend.domain.recruitment.dto.response.ManagementDetailResponseDTO;
import cotato.backend.domain.recruitment.entity.Applicant;
import cotato.backend.domain.recruitment.entity.Management;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ManagementService {

	private final ManagementRepository managementRepository;

	public ManagementDetailResponseDTO getManagement(Long managementId) {

		// managementId로 찾은 Optional 객체 값이 비어있는 경우 예외 발생, 값이 존재하는 경우 변수 management에 값 저장
		Management management = managementRepository.findByManagementId(managementId)
			.orElseThrow(() -> new NoSuchElementException("Management with ID " + managementId + " does not exist"));

		return new ManagementDetailResponseDTO(
			management.getName(), management.getAge(),
			management.getPhoneNumber(), management.getManagementRole()
		);
	}
}