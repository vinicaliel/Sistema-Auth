package sys.sistema_cadastro_empresa_cliente.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sys.sistema_cadastro_empresa_cliente.dto.AuthResponse;
import sys.sistema_cadastro_empresa_cliente.dto.LoginRequest;
import sys.sistema_cadastro_empresa_cliente.dto.RegisterRequest;
import sys.sistema_cadastro_empresa_cliente.entity.User;
import sys.sistema_cadastro_empresa_cliente.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    
    public AuthResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            
            User user = (User) authentication.getPrincipal();
            String token = jwtUtil.generateToken(user);
            
            return new AuthResponse(
                    token,
                    user.getEmail(),
                    user.getName(),
                    user.getUserType().name(),
                    user.getId()
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Email ou senha incorretos");
        }
    }
    
    public AuthResponse register(RegisterRequest request) {
        User user = userService.registerUser(request);
        String token = jwtUtil.generateToken(user);
        
        // Enviar email de boas-vindas
        try {
            emailService.sendWelcomeEmail(user);
        } catch (Exception e) {
            // Log do erro mas não falha o registro
            System.err.println("Erro ao enviar email de boas-vindas: " + e.getMessage());
        }
        
        return new AuthResponse(
                token,
                user.getEmail(),
                user.getName(),
                user.getUserType().name(),
                user.getId()
        );
    }
}
