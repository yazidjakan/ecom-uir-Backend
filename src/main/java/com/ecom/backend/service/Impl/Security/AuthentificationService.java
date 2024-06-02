package com.ecom.backend.service.Impl.Security;

import com.ecom.backend.entity.security.AuthentificationRequest;
import com.ecom.backend.entity.security.AuthentificationResponse;
import com.ecom.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userDao;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthentificationResponse authenticate (AuthentificationRequest request)
    {
        System.out.println("Authenticating user: " + request.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        var user = userDao.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + request.getUsername()));
        var jwtToken = jwtTokenProvider.generateToken(user);
        return AuthentificationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
