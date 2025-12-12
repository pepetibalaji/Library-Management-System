package auth.service.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auth.service.Repository.UserRepository;
import auth.service.config.JwtService;
import auth.service.dto.LoginRequest;
import auth.service.dto.LoginResponse;
import auth.service.dto.Profile;
import auth.service.dto.RegisterRequest;
import auth.service.dto.editProfile;
import auth.service.dto.editProfileByAdmin;
import auth.service.entity.Role;
import auth.service.entity.User;
import auth.service.globalException.IncorrectUserNameOrPassword;
import auth.service.globalException.UserAlreadyExists;
import auth.service.globalException.userDoesNotExist;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @Transactional(rollbackFor = Exception.class)
    public void registerForAdmin(RegisterRequest req) throws UserAlreadyExists {
        if (repo.existsByUsername(req.getUsername()))
            throw new UserAlreadyExists("Username already exists");
        User u = User.builder()
                .username(req.getUsername())
                .password(encoder.encode(req.getPassword()))
                .role(req.getRole())
                .email(req.getEmail())
                .status(req.getStatus())
                .membership_type(req.getMembership_type())
                .build();
        repo.save(u);
    }

    @Transactional(rollbackFor = Exception.class)
    public void registerationForMember(RegisterRequest req) throws UserAlreadyExists {
        if (repo.existsByUsername(req.getUsername()))
            throw new UserAlreadyExists("Username already exists");
        req.getRole();
        User u = User.builder()
                .username(req.getUsername())
                .password(encoder.encode(req.getPassword()))
                .role(Role.MEMBER)
                .email(req.getEmail())
                .status(req.getStatus())
                .membership_type(req.getMembership_type())
                .build();
        repo.save(u);
    }

    public Profile profile(String username) {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new userDoesNotExist("User Does not Exist"));

        return Profile.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .status(user.getStatus())
                .email(user.getEmail())
                .membership_type(user.getMembership_type())
                .build();
    }

    public LoginResponse login(LoginRequest userCredentials) throws IncorrectUserNameOrPassword {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userCredentials.getUsername(),
                            userCredentials.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new IncorrectUserNameOrPassword("Incorrect password");
        } catch (DisabledException ex) {
            throw new IncorrectUserNameOrPassword("Your account is disabled. Contact support.");
        } catch (LockedException ex) {
            throw new IncorrectUserNameOrPassword("Your account is locked. Contact support.");
        }
        User user = repo.findByUsername(userCredentials.getUsername())
                .orElseThrow(() -> new userDoesNotExist("User Does not Exist"));
        String access = jwtService.generateAccessToken(user.getUsername(), user.getRole().name());
        String refresh = jwtService.generateRefreshToken(user.getUsername());
        return new LoginResponse(access, refresh, user.getRole().name(),
                System.currentTimeMillis() + 900_000);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(String username) {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new userDoesNotExist("User Does not Exist"));
        repo.delete(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUserProfileByAdmin(editProfileByAdmin profile, String username) {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new userDoesNotExist("User not found"));
        user.setRole(profile.getRole());
        user.setEmail(profile.getEmail());
        user.setStatus(profile.getStatus());
        user.setMembership_type(profile.getMembership_type());

        repo.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUserProfile(editProfile profile) {
        // Get the username from SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = repo.findByUsername(username)
                .orElseThrow(() -> new userDoesNotExist("User not found"));

        user.setRole(profile.getRole());
        user.setEmail(profile.getEmail());
        user.setStatus(profile.getStatus());
        user.setMembership_type(profile.getMembership_type());

        repo.save(user);
    }

    public List<Profile> getAllUsersProfiles() {
        List<User> users = repo.findAll();
        return users.stream()
                .map(u -> new Profile(
                        u.getId(),
                        u.getUsername(),
                        u.getRole(),
                        u.getEmail(),
                        u.getStatus(),
                        u.getMembership_type()))
                .collect(Collectors.toList());
    }

}
