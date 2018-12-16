package com.ock.au.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Class describes users of com.ock.au.entity
 *
 *
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @Type(type = "string")
    @NotEmpty
    private String name;

    @Column(name = "surname")
    @Type(type = "string")
    @NotEmpty
    private String surname;

    @Column(name = "email")
    @Type(type = "string")
    @NotEmpty
    @Email
    private String email;

    @Column(name = "password")
    @Type(type = "string")
    @NotEmpty
    @Size(min = 4, max = 18)
    private String password;

    @NotEmpty
    @Size(min = 4, max = 18)
    private String confirmedPassword;

    @Column(name = "status", length = 10)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "is_admin")
    @Type(type = "yes_no")
    private boolean isAdmin;

    @ManyToMany
    @JoinTable(name = "users_roles", schema = "au",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<Lot> lots = new ArrayList<>();

    public void addRole(Role role) {
        if (!this.hasRole(role)) {
            this.roles.add(role);
        }
    }

    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }
}
