package cotato.backend.domain.user.dto;

import cotato.backend.domain.user.User;

public class UserResponse {
    private final String name;
    private final int age;
    private final String phoneNumber;

    public UserResponse(User user) {
        this.name = user.getName();
        this.age = user.getAge();
        this.phoneNumber = user.getPhoneNumber();
    }
}
