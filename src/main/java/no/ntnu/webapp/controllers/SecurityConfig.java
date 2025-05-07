package no.ntnu.webapp.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig.java
 * This class configures the security settings for the web application.
 * It defines which URLs are accessible to which roles and sets up form login and logout.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application.
     * It specifies which URLs are accessible to which roles and sets up form login and logout.
     * @param http HttpSecurity object to configure security settings
     * @return SecurityFilterChain object
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection for simplicity
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
                                "/course",
                                "/search",
                                "/payment",
                                "/checkout",
                                "/processPayment",
                                "/MyCourses",
                                "/favoriteCourses"
                        ).permitAll()
                        .requestMatchers("/admin").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                // Enable CSRF protection
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                // Enable logout functionality
                .logout(logout -> logout
                        .logoutSuccessUrl("/home")
                        .permitAll()
                );

        // Disable CSRF protection for simplicity
        return http.build();
    }

    /**
     * Password encoder bean for encoding passwords.
     * This uses BCrypt hashing algorithm for secure password storage.
     * @return PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}