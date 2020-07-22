package com.nextloop.nearlog.api.domain.user;

import com.nextloop.nearlog.api.domain.base.BaseTime;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NotBlank
    @Size(max=100)
    private String password;

    // email로 SecurityContext 검증
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    private String profile;

    @Column(length = 5)
    @Enumerated(EnumType.STRING)
    private User.Role role;

    public enum Role {
        USER, ADMIN
    }


}
