package yummers.cloudconfig.appconfig;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderRunner  {

  public static void main(String... args) throws Exception {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawPassword = "monMotDePasse"; // Remplacez par votre mot de passe brut
    System.out.println("Mot de passe encodé : " + encoder.encode(rawPassword));
  }
}
