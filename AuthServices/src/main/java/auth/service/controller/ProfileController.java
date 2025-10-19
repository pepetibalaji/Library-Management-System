package auth.service.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import auth.service.dto.Profile;

import auth.service.dto.editProfile;
import auth.service.service.AuthService;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final AuthService authService;

    @GetMapping("/{username}")
    public Profile fetchProfile(@PathVariable String username) {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        // Allow delete only if same user
        if (loggedInUsername.equals(username)) {
            Profile profile = authService.profile(username);
            return profile;
        }
        return null;
    }

    @PutMapping("/{username}")
    public ResponseEntity<String> editProfile(@RequestBody editProfile profile) {
        authService.updateUserProfile(profile);
        return ResponseEntity.ok("Profile Updated Successfully");
    }

}
