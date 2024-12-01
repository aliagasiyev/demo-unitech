package az.edu.turing.msuser.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String fin;
    private String fullName;
    private String password;
    private String email;
}
