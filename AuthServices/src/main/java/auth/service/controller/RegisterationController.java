package auth.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import auth.service.dto.*;
import auth.service.service.AuthService;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterationController {

    private final AuthService authService;

    @PostMapping("/admin")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        authService.registerForAdmin(req);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/member")
    public ResponseEntity<String> registerMember(@RequestBody RegisterRequest req) {
        authService.registerationForMember(req);
        return ResponseEntity.ok("Member registered successfully");
    }
}
