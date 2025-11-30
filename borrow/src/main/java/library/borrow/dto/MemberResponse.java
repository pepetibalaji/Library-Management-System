package library.borrow.dto;

import library.borrow.entity.Role;
import lombok.Data;

@Data
public class MemberResponse {
    private Long id;
    private String username;
    private Role role;
    private String email;
    private String status;
    private String membership_type;
}
