package com.lizmahoney1.CodeFellowShip.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.sql.Date;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy="appUser")
    public List<Post> posts;

    @ManyToMany
    Set<ApplicationUser> friends;

    //default constructor
    public ApplicationUser(){}

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
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public long getId() {
        return id;
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

    public void setUsername(String username) { this.username = username; }

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

    public List<Post> getPosts() { return posts; }

    public void setPosts(List<Post> posts) { this.posts = posts; }

    public Set<ApplicationUser> getFriends() { return friends; }

    public void setFriends(Set<ApplicationUser> friends) { this.friends = friends; }


    // Auth stuff

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
