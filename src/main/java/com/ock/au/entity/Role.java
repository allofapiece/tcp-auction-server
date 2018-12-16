package com.ock.au.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Class describes roles of the users.
 *
 * @author Listratsenka Stanislau
 * @version 1.0
 */
@Entity
@Table(name = "roles")
public enum Role {
    ANONYMOUS,
    USER,
    ADMIN,
    SOLVENT;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
