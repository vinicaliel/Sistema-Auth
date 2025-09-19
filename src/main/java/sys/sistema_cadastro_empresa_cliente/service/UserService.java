package sys.sistema_cadastro_empresa_cliente.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sys.sistema_cadastro_empresa_cliente.dto.RegisterRequest;
import sys.sistema_cadastro_empresa_cliente.entity.User;
import sys.sistema_cadastro_empresa_cliente.exception.BusinessException;
import sys.sistema_cadastro_empresa_cliente.exception.ResourceNotFoundException;
import sys.sistema_cadastro_empresa_cliente.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
    
    public User registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email já está em uso");
        }
        
        if (userRepository.existsByDocumentNumber(request.getDocumentNumber())) {
            throw new BusinessException("Documento já está em uso");
        }
        
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDocumentNumber(request.getDocumentNumber());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setUserType(request.getUserType());
        
        return userRepository.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + email));
    }
}
