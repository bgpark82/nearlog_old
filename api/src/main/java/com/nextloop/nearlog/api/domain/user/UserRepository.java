package com.nextloop.nearlog.api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<UserPrincipal> findByEmail(String email);
    User save(User user);
}
