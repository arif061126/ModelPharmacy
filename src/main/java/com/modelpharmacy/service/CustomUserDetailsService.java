package com.modelpharmacy.service;

import com.modelpharmacy.entity.User;
import com.modelpharmacy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findUserByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found: " + userName));

/*        if (user.get().getPasswordExpiryDate().isBefore(LocalDateTime.now())) {
            throw new UsernameNotFoundException("Password Expired!");
        }*/

        return user.get();
    }
}
