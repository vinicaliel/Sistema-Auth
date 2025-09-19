package sys.sistema_cadastro_empresa_cliente.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sys.sistema_cadastro_empresa_cliente.entity.User;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {
    
    @GetMapping("/public")
    public ResponseEntity<Map<String, String>> publicEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Este é um endpoint público - não requer autenticação");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/protected")
    public ResponseEntity<Map<String, Object>> protectedEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Este é um endpoint protegido - requer autenticação JWT");
        response.put("user", user.getName());
        response.put("email", user.getEmail());
        response.put("userType", user.getUserType());
        response.put("authorities", user.getAuthorities());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/student-only")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Map<String, String>> studentOnlyEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Este endpoint é apenas para estudantes");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/company-only")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Map<String, String>> companyOnlyEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Este endpoint é apenas para empresas");
        return ResponseEntity.ok(response);
    }
}
