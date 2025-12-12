package auth.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import auth.service.service.AuthService;

@RestController
@RequestMapping("/delete")
@RequiredArgsConstructor
public class DeleteUserController {

    private final AuthService authService;

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        authService.deleteUser(loggedInUsername);
        return ResponseEntity.ok("User deleted: " + username);
    }

}
