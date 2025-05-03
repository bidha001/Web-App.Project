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
        // Search for the user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Bruker ikke funnet: " + username));

        // convert role to spring security authority
        String authority = "ROLE_" + user.getRole().name();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPasswordHash(),
                Collections.singletonList(grantedAuthority)
        );
    }
}
