package auth.service.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import auth.service.dto.Profile;
import auth.service.service.AuthService;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final AuthService authService;

    @GetMapping("/{username}")
    public Profile getMethodName(@PathVariable String username) {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        // Allow delete only if same user
        if (loggedInUsername.equals(username)) {
            Profile profile = authService.profile(username);
            return profile;
        }
        return null;
    }

}
