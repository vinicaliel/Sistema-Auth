package sys.sistema_cadastro_empresa_cliente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sys.sistema_cadastro_empresa_cliente.dto.AuthResponse;
import sys.sistema_cadastro_empresa_cliente.dto.LoginRequest;
import sys.sistema_cadastro_empresa_cliente.dto.RegisterRequest;
import sys.sistema_cadastro_empresa_cliente.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register/student")
    public ResponseEntity<AuthResponse> registerStudent(@Valid @RequestBody RegisterRequest request) {
        request.setUserType(sys.sistema_cadastro_empresa_cliente.entity.User.UserType.STUDENT);
        return register(request);
    }
    
    @PostMapping("/register/company")
    public ResponseEntity<AuthResponse> registerCompany(@Valid @RequestBody RegisterRequest request) {
        request.setUserType(sys.sistema_cadastro_empresa_cliente.entity.User.UserType.COMPANY);
        return register(request);
    }
}
