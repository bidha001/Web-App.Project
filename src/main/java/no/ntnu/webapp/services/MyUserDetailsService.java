package no.ntnu.webapp.services;

import no.ntnu.webapp.models.User;
import no.ntnu.webapp.repositories.UserRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

   @Autowired
   public MyUserDetailsService(UserRepository userRepository) {
       this.userRepository = userRepository;
   }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("User not found"));

       // Check that both user and role are not null
        String password = user.getPasswordHash();
        if (password == null) {
            throw new UsernameNotFoundException("Password not found");
        }
        String role = user.getRole() != null ? user.getRole().name() : "REGISTERED";

        GrantedAuthority authority = new SimpleGrantedAuthority(role);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                password,
                Collections.singletonList(authority)

        );
    }
}
