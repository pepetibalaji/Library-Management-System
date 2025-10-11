package auth.service.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import auth.service.service.AuthService;

@RestController
@RequestMapping("/delete")
@RequiredArgsConstructor
public class DeleteUserController {

    private final AuthService authService;

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        // Get logged-in username from SecurityContext
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Allow delete only if same user
        if (!loggedInUsername.equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Session Mismatch");
        }
        authService.deleteUser(username);
        return ResponseEntity.ok("User deleted: " + username);
    }

}
