package com.lizmahoney1.CodeFellowShip.Controller;

import com.lizmahoney1.CodeFellowShip.AppUserRepository;
import com.lizmahoney1.CodeFellowShip.Model.ApplicationUser;
import com.lizmahoney1.CodeFellowShip.Model.Post;
import com.lizmahoney1.CodeFellowShip.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
