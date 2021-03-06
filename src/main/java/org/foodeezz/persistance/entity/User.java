package org.foodeezz.persistance.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by GALIB on 4/18/2015.
 */
@Entity
public class User extends Persistent {

    @NotEmpty
    @Length(min = 5, max = 99)
    @Column(nullable = false, length = 99, unique = true)
    private String username;

    @NotEmpty
    @Email
    @Column(nullable = false, length = 99, unique = true)
    private String email;

    @NotEmpty
    @Length(min = 5, max = 99)
    @Column(nullable = false, length = 99)
    private String password;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Column(nullable = true, length = 99)
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
