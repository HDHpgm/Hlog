package com.dev.hlog.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
public class User extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    private String username;
    private String password;
    private String roles; // USER, ADMIN



    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public User() {

    }

    @Builder
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.roles = role;
    }
}
