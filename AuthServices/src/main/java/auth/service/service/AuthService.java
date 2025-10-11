package auth.service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import auth.service.Repository.UserRepository;
import auth.service.config.JwtService;
import auth.service.dto.LoginRequest;
import auth.service.dto.LoginResponse;
import auth.service.dto.RegisterRequest;
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

    public void registerForAdmin(RegisterRequest req) throws UserAlreadyExists {
        if (repo.existsByUsername(req.getUsername()))
            throw new UserAlreadyExists("Username already exists");
        User u = User.builder()
                .username(req.getUsername())
                .password(encoder.encode(req.getPassword()))
                .role(req.getRole())
                .build();
        repo.save(u);
    }

    public void registerationForMember(RegisterRequest req) throws UserAlreadyExists {
        if (repo.existsByUsername(req.getUsername()))
            throw new UserAlreadyExists("Username already exists");
        req.getRole();
        User u = User.builder()
                .username(req.getUsername())
                .password(encoder.encode(req.getPassword()))
                .role(Role.MEMBER)
                .build();
        repo.save(u);
    }

    public LoginResponse login(LoginRequest req) throws IncorrectUserNameOrPassword {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (BadCredentialsException ex) {
            // Password is incorrect
            throw new IncorrectUserNameOrPassword("Incorrect password");
        } catch (DisabledException ex) {
            throw new IncorrectUserNameOrPassword("Your account is disabled. Contact support.");
        } catch (LockedException ex) {
            throw new IncorrectUserNameOrPassword("Your account is locked. Contact support.");
        }
        User u = repo.findByUsername(req.getUsername())
                .orElseThrow(() -> new userDoesNotExist("User Does not Exist"));
        String access = jwtService.generateAccessToken(u.getUsername(), u.getRole().name());
        String refresh = jwtService.generateRefreshToken(u.getUsername());
        return new LoginResponse(access, refresh, u.getRole().name(),
                System.currentTimeMillis() + 900_000);
    }

    public void deleteUser(String req) {
        User u = repo.findByUsername(req)
                .orElseThrow(() -> new userDoesNotExist("User Does not Exist"));
        repo.delete(u);
    }
}
