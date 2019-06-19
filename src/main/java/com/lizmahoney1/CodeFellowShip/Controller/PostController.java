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

//    @PostMapping("/updateSong/{id}")
//    public String updateSong(@PathVariable Long id, @RequestParam String title, @RequestParam int length,
//                             @RequestParam int trackNumber){
//        Song s = songRepository.findById(id).get();
//        s.setTitle(title);
//        s.setLength(length);
//        s.setTrackNumber(trackNumber);
//        songRepository.save(s);
//        return "redirect:/albumDetail/{id}";
//    }
//
//    @PostMapping(value="/post", method=RequestMethod.POST)
//    public RedirectView create(@RequestParam String body, Principal p) {
//
//        ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
//
//        //create a post & save it
//        Post newPost = new Post(body, new Date());
//        newPost.appUser = appUserRepo.findById(user.id).get();
//        postRepo.save(newPost);
//
//        //redirect to myprofile
//        return new RedirectView("/myprofile");
//    }
}
