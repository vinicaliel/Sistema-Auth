package sys.sistema_cadastro_empresa_cliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sys.sistema_cadastro_empresa_cliente.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    boolean existsByDocumentNumber(String documentNumber);
    
    Optional<User> findByEmailAndUserType(String email, User.UserType userType);
}
