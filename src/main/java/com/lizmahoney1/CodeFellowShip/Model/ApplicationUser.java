package com.lizmahoney1.CodeFellowShip.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.sql.Date;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    @Column(unique=true)
    String username;
    String password;
    String firstName;
    String lastName;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    Date dateOfBirth;
    String bio;

    //login
    public ApplicationUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    public ApplicationUser(){}
    //sign up
    public ApplicationUser(String username, String password, String firstName, String lastName, Date dateOfBirth,
                           String bio) {
        this.username = username;
        this.password = password;
        this.firstName =firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public long getId() {
        return id;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return  true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
