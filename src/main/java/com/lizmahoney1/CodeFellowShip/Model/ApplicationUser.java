package com.lizmahoney1.CodeFellowShip.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.sql.Date;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    String username;
    String password;
    String firstName;
    String lastname;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    Date dateOfBirth;
    String bio;

    //login
    public ApplicationUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    //sign up
    public ApplicationUser(String username, String password, String firstName, String lastName, Date dateOfBirth,
                           String bio) {
        this.username = username;
        this.password = password;
        this.firstName =firstName;
        this.lastname = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Date getDateOfBirth() { return dateOfBirth; }

    public String getBio() { return bio; }

    public String getFirstName() { return firstName; }

    public String getLastname() { return lastname; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public void setBio(String bio) { this.bio = bio; }

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
