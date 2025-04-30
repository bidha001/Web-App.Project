package no.ntnu.webapp.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/home",
                                "/login",
                                "/signup",
                                "/contactUs",
                                "/css/**",
                                "/images/**",
                                "/components/**",
                                "/informationTechnologies",
                                "/digitalMarketing",
                                "/dataScienceAnalytics",
                                "/businessEntrepreneurship",
                                "/course"
                        ).permitAll() // Tillat offentlig tilgang til de nevnte sidene
                        .requestMatchers("/admin").hasRole("ADMIN") // Kun ADMIN kan se admin-siden
                        .requestMatchers("/user").hasRole("USER")   // Kun USER kan se bruker-siden
                        .anyRequest().authenticated() // Alle andre sider krever autentisering
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true) // Etter vellykket login, send til home
                        .failureUrl("/login?error=true") // Feil login sendes tilbake til login med feilmelding
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/home") // Etter logout, send til home
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bruker BCrypt for passordkoding
    }
}
