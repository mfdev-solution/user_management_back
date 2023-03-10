package sn.mfdev.usermgt.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.mfdev.usermgt.Models.UserModel;
import sn.mfdev.usermgt.config.JwtService;
import sn.mfdev.usermgt.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = UserModel.builder()
                .name(registerRequest.getName())
                .age(registerRequest.getAge())
                .email(registerRequest.getEmail())
                .role(((registerRequest.getRole())))
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken( user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest registerRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getUsername()
                        ,registerRequest.getPassword()
                )
        );
        var user = repository.findByEmail(registerRequest.getUsername()).orElseThrow();
//        System.out.println("is a this valid"+user.getEmail());

        var jwtToken = jwtService.generateToken( user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
