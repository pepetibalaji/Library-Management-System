package auth.service.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import auth.service.dto.Profile;

import auth.service.dto.editProfile;
import auth.service.dto.editProfileByAdmin;
import auth.service.entity.User;
import auth.service.service.AuthService;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final AuthService authService;

    @GetMapping
    public Profile fetchProfile() {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = authService.profile(loggedInUsername);
        return profile;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsersProfiles() {
        List<User> userProfiles = authService.getAllUsersProfiles();
        return userProfiles;
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateUserProfileByAdmin(@RequestBody editProfileByAdmin profile,
            @PathVariable String username) {
        authService.updateUserProfileByAdmin(profile, username);
        return ResponseEntity.ok("Profile Updated Successfully");
    }

    @PutMapping
    public ResponseEntity<String> editProfile(@RequestBody editProfile profile) {
        authService.updateUserProfile(profile);
        return ResponseEntity.ok("Profile Updated Successfully");
    }

}
