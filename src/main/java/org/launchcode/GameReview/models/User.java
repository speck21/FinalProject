package org.launchcode.GameReview.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Entity
public class User extends AbstractEntity{

    //TODO:Add many to many with reviews/comments
    @NotNull
    private String username;
    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User(){};

    public User(String name, String pwHash) {
        this.username = name;
        this.pwHash = encoder.encode(pwHash);
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password){return encoder.matches(password, pwHash);}
}
