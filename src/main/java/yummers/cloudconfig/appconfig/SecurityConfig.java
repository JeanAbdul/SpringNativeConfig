package yummers.cloudconfig.appconfig;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // Configurer les autorisations
    http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/actuator/**").permitAll()
                    .anyRequest().authenticated()
            )
            .httpBasic(httpBasicCustomizer -> {})
            .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/encrypt", "/decrypt")
            );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    // Utiliser BCrypt pour encoder les mots de passe
    return new BCryptPasswordEncoder();
  }
}
