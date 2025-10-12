package auth.service.dto;

import auth.service.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Profile {
    private String username;
    private Role role;
}
