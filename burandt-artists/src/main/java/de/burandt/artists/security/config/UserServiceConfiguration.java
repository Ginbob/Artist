package de.burandt.artists.security.config;

import de.burandt.artists.security.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserServiceConfiguration {

    @Autowired
    UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        final String encoderId = "bcrypt";
        final BCryptPasswordEncoder defaultEncoder = new BCryptPasswordEncoder();

        // if I choose to use something else than bcrypt...
        final Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(
                encoderId, defaultEncoder);
        encoders.put(
                "pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put(
                "scrypt", new SCryptPasswordEncoder());

        final DelegatingPasswordEncoder delegatingPasswordEncoder =
                new DelegatingPasswordEncoder(encoderId, encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder);
        return delegatingPasswordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findOneByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        // no Roles defined / needed
                        new ArrayList<>()
                )).orElseThrow(() -> new UsernameNotFoundException(username));

    }
}
