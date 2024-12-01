package az.edu.turing.msuser.dto.response;

import lombok.Data;

@Data
public class UserRegisterResponse {
    private String fin;
    private String fullName;
    private String email;
    private String role; // User və ya Admin
    private String status;
}
