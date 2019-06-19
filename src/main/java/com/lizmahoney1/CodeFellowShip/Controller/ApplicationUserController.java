package com.lizmahoney1.CodeFellowShip.Controller;

import com.lizmahoney1.CodeFellowShip.Model.ApplicationUser;
import com.lizmahoney1.CodeFellowShip.AppUserRepository;
import com.lizmahoney1.CodeFellowShip.Model.Post;
import com.lizmahoney1.CodeFellowShip.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.sql.Date;

@Controller
public class ApplicationUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    // Get root page
    @GetMapping("/")
    public String getIndex(Model m){
        m.addAttribute("user", false);
        return "index";
    }

    @PostMapping("/user")
    public String createUser(String username, String password, String firstName, String lastName,
                                   Date dateOfBirth, String bio ){
       ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName,
               lastName, dateOfBirth, bio);
       appUserRepository.save(newUser);
        // For autologin
        ApplicationUser user = appUserRepository.findByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        long id = user.getId();
        return "redirect:/users/"+id;
    }

    @GetMapping("/login")
    public String getLoginPage(Model m){
        m.addAttribute("user", false);
        return "login"; }

    @GetMapping("/users/{id}")
    public String getMyProfile(Principal p, Model m){
        ApplicationUser currentUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();

        m.addAttribute("user", appUserRepository.findById(currentUser.getId()).get());
        m.addAttribute("myProfile", false);
        m.addAttribute("userId", currentUser.getId());
        m.addAttribute("principal",currentUser);
        return "myprofile";
    }

    @GetMapping("/myprofile")
    public String getMyProfilePage(Principal p, Model m){
        //ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        ApplicationUser post = appUserRepository.findByUsername(p.getName());
        m.addAttribute("principal", post);
        m.addAttribute("myProfile", true);
        m.addAttribute("user", true);
        return "myprofile";
    }

    @GetMapping("/signup")
    public String getSignupPage(Model m)
    {
        m.addAttribute("user", false);
        return "signup";
    }

//    @ResponseStatus(value = HttpStatus.FORBIDDEN)
//    public class badRequestException extends RuntimeException(){
//        public badRequestException(String s){
//                super();
//            }
//    }
}

