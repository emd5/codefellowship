package com.lizmahoney1.CodeFellowShip.Controller;

import com.lizmahoney1.CodeFellowShip.AppUserRepository;
import com.lizmahoney1.CodeFellowShip.Model.ApplicationUser;
import com.lizmahoney1.CodeFellowShip.Model.Post;
import com.lizmahoney1.CodeFellowShip.PostRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @PostMapping("/post")
    public RedirectView createPost(@RequestParam String body, Principal p){
        ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        Post newPost = new Post(body, new Date());
        newPost.appUser = appUserRepository.findById(user.getId()).get();
        postRepository.save(newPost);
        //redirect to myprofile
        return new RedirectView("/myprofile");
    }

    @GetMapping("/posts/{id}")
    public String getOnePost(@PathVariable Long id, Principal p, Model m) {
        Post post = postRepository.findById(id).get();
        m.addAttribute("post", post);
        //for the nav bar
        m.addAttribute("myProfile", true);
        m.addAttribute("user", true);
        return "post";
    }
    // came from https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    class PostException extends RuntimeException {
        public PostException(String s) {
            super(s);
        }
    }
}
