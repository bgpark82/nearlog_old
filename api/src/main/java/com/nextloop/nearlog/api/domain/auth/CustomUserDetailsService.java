package com.nextloop.nearlog.api.domain.auth;

import com.nextloop.nearlog.api.domain.user.User;
import com.nextloop.nearlog.api.domain.user.UserPrincipal;
import com.nextloop.nearlog.api.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        return UserPrincipal.create(user);
    }
}
