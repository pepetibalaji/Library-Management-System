package auth.service.dto;

import auth.service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    private Long id;
    private String username;
    private Role role;
    private String email;
    private String status;
    private String membership_type;
}
