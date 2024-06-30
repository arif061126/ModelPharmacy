/*
package com.modelpharmacy.config;

import com.modelpharmacy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;
    //private final DesPasswordEncoder desPasswordEncoder;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        */
/*String aesKey = "SKkgQjoTbPODVJEEVLyuyk0xASiTiRq577UOLkuJi7g=";  // Use a securely generated key
        return new AesPasswordEncoder(aesKey);*//*

        //return aesPasswordEncoder();
        //return desPasswordEncoder();
    }

    */
/*@Bean
    public AesPasswordEncoder aesPasswordEncoder() {
        // Use a securely generated AES key
        String aesKey = "OQbLIAys97TqWMu9zw+6pO1DUuvv9IErRsef9f9rNOQ=";  // Ensure this is a valid Base64 encoded AES key
        return new AesPasswordEncoder(aesKey);
    }*//*

    */
/*@Bean
    public DesPasswordEncoder desPasswordEncoder() {
       return new DesPasswordEncoder();
    }*//*

}
*/
