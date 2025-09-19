package sys.sistema_cadastro_empresa_cliente.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Sistema de Cadastro - API JWT");
        response.put("status", "online");
        response.put("endpoints", Map.of(
            "public", "/api/test/public",
            "swagger", "/swagger-ui.html",
            "auth", "/api/auth/login"
        ));
        return response;
    }
}
