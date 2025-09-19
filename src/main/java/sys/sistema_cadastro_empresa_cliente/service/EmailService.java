package sys.sistema_cadastro_empresa_cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import sys.sistema_cadastro_empresa_cliente.entity.User;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${app.frontend.url:http://localhost:3000}")
    private String frontendUrl;

    public void sendWelcomeEmail(User user) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(user.getEmail());
            helper.setSubject("Bem-vindo ao Sistema de Cadastro!");

            // Criar contexto para o template
            Context context = new Context();
            context.setVariable("userName", user.getName());
            context.setVariable("userEmail", user.getEmail());
            context.setVariable("userType", user.getUserType().name());
            context.setVariable("frontendUrl", frontendUrl);

            // Processar template HTML
            String htmlContent = templateEngine.process("welcome-email", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar email de boas-vindas", e);
        }
    }

    public void sendSimpleWelcomeEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(user.getEmail());
        message.setSubject("Bem-vindo ao Sistema de Cadastro!");
        message.setText(createSimpleWelcomeText(user));

        mailSender.send(message);
    }

    private String createSimpleWelcomeText(User user) {
        return String.format("""
            Olá %s!
            
            Seja bem-vindo(a) ao Sistema de Cadastro!
            
            Seus dados de cadastro:
            - Nome: %s
            - Email: %s
            - Tipo: %s
            - Data de cadastro: %s
            
            Agora você pode fazer login em nossa plataforma e aproveitar todos os recursos disponíveis.
            
            Se você não se cadastrou em nosso sistema, ignore este email.
            
            Atenciosamente,
            Equipe do Sistema de Cadastro
            """, 
            user.getName(),
            user.getName(),
            user.getEmail(),
            user.getUserType().name(),
            java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        );
    }
}
