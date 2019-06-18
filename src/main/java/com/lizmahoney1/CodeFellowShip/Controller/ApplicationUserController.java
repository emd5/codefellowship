package com.lizmahoney1.CodeFellowShip.Controller;

import com.lizmahoney1.CodeFellowShip.Model.ApplicationUser;
import com.lizmahoney1.CodeFellowShip.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.sql.Date;

@Controller
public class ApplicationUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    // Get root page
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @PostMapping("/user")
    public RedirectView createUser(String username, String password, String firstName, String lastName,
                                   Date dateOfBirth, String bio ){
       ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName,
               lastName, dateOfBirth, bio);
       appUserRepository.save(newUser);

        // For autologin
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
       return new RedirectView("/myprofile");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

//    @PostMapping("/findUser")
//    public RedirectView findUser(@PathVariable String username, @PathVariable String password){
//        String encodedPassword = bCryptPasswordEncoder.encode(password);
//        ApplicationUser checkUser = appUserRepository.findByUsername(username);
//        if(checkUser.getPassword().equals(encodedPassword)){
//            // For autologin
//            Authentication authentication = new UsernamePasswordAuthenticationToken(checkUser, null, new ArrayList<>());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return new RedirectView("/myprofile");
//        } else{
//            return new RedirectView("/login");
//        }
//    }

    @GetMapping("/myprofile")
    public String getMyProfilePage(Principal p, Model m){
        System.out.println(p.getName());
        m.addAttribute("principal", p);
        return "myprofile";
    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }

}
