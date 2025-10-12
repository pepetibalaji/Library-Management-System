package auth.service.dto;

import auth.service.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
    private String email;
    private String status;
    private String membership_type;
}
