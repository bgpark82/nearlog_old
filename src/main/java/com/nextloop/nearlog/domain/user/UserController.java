package com.nextloop.nearlog.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/user")
    public User save() {
        User user = User.builder()
                    .username("bgpark")
                    .password("1234")
                    .build();
        return userRepository.save(user);
    }

    @GetMapping("/user")
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
