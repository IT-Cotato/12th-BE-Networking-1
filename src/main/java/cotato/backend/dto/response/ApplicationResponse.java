package cotato.backend.dto.response;

import cotato.backend.domain.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

public class ApplicationResponse {

    private final Long applicationId;
    private final String name;
    private final int period;
    private final int age;
    private final String part;
    private final int ability;
    private final int passion;
    private final String phoneNumber;
    private final int likesNum;

    public ApplicationResponse(Application app) {
        this.applicationId = app.getApplicationId();
        this.name = app.getName();
        this.period = app.getPeriod();
        this.age = app.getAge();
        this.part = app.getPart().name();
        this.ability = app.getAbility();
        this.passion = app.getPassion();
        this.phoneNumber = app.getPhoneNumber();
        this.likesNum = app.getLikesNum();
    }


}
