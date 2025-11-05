package cotato.backend.domain.user;

import cotato.backend.domain.user.dto.UserResponse;
import cotato.backend.domain.user.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저를 찾을 수 없습니다. ID: " + userId));

        return new UserResponse(user);
    }


    @Transactional
    public UserResponse updateUserInfo(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("해당 유저를 찾을 수 없습니다. ID: " + userId));

        user.updateProfile(request.getName(), request.getAge(), request.getPhoneNumber());

        return new UserResponse(user);
    }
}
