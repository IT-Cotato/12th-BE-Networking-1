package cotato.backend.domain.application;

import cotato.backend.domain.application.dto.ApplicationSubmitRequest;
import cotato.backend.domain.user.Role;
import cotato.backend.domain.user.User;
import cotato.backend.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Transactional
    public Long submitApplication(ApplicationSubmitRequest request) {

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .name(request.getName())
                            .age(request.getAge())
                            .phoneNumber(request.getPhoneNumber())
                            .role(Role.APPLICANT)
                            .build();
                    return userRepository.save(newUser);
                });

        if (user.getRole() == Role.APPLICANT) {
            user.updateProfile(request.getName(), request.getAge(),request.getPhoneNumber());
        }

        Application application = Application.builder()
                .user(user)
                .period(request.getPeriod())
                .name(request.getName())
                .age(request.getAge())
                .phoneNumber(request.getPhoneNumber())
                .part(new PartConverter().convertToEntityAttribute(request.getPart()))
                .ability(request.getAbility())
                .passion(request.getPassion())
                .applicationTime(LocalDateTime.parse(request.getApplicationTime(), TIME_FORMATTER))
                .build();

        Application savedApplication = applicationRepository.save(application);

        return savedApplication.getId();
    }
}