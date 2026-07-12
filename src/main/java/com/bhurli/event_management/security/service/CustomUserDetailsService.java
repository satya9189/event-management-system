package com.bhurli.event_management.security.service;

import com.bhurli.event_management.entity.User;
import com.bhurli.event_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Loads user details from database for Spring Security.
 */
@Service
@RequiredArgsConstructor

public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
             throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));


        user.getAuthorities().forEach(a ->
                System.out.println("AUTHORITY -> " + a.getAuthority()));
        System.out.println("==================================");

        return user;
    }

}
