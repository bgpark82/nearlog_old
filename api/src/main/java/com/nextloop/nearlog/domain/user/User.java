package com.nextloop.nearlog.domain.user;

import com.nextloop.nearlog.domain.base.BaseTime;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;

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

    @NotBlank
    @Email
    private String email;

    private String profile;

    @Column(length = 5)
    @Enumerated(EnumType.STRING)
    private User.Role role;

    public enum Role {
        USER, ADMIN
    }


}
