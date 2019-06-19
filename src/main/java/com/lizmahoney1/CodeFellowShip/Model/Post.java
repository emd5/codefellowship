package com.lizmahoney1.CodeFellowShip.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String body;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    Date createdAt;

    @ManyToOne
    public ApplicationUser appUser;

    public Post(){}

    public Post(String body, Date createdAt){
        this.body = body;
        this.createdAt = createdAt;
    }

}
