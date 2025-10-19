package auth.service.dto;

import auth.service.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class editProfile {
    private String username;
    private Role role;
    private String email;
    private String status;
    private String membership_type;
}
