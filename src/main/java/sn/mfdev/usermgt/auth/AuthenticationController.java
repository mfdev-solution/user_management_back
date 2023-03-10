package sn.mfdev.usermgt.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ){
        return  ResponseEntity.ok(authenticationService.register(registerRequest));

    }

        @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>  authRequest(
            @RequestBody AuthenticationRequest registerRequest
    ){
        return  ResponseEntity.ok(authenticationService.authenticate(registerRequest));
    }
}
