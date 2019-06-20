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
import java.util.List;
import java.util.Set;

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
    public String getIndex(Model m) {
        m.addAttribute("user", false);
        return "index";
    }

    @PostMapping("/user")
    public String createUser(String username, String password, String firstName, String lastName,
                             Date dateOfBirth, String bio) {
        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName,
                lastName, dateOfBirth, bio);
        appUserRepository.save(newUser);
        // For autologin
        ApplicationUser user = appUserRepository.findByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        long id = user.getId();
        return "redirect:/users/" + id;
    }

    @GetMapping("/login")
    public String getLoginPage(Model m) {
        m.addAttribute("user", false);
        return "login";
    }


    @GetMapping("/users/{id}")
    public String getMyProfile(Principal p, Model m) {
        //set for conditions
        ApplicationUser user = appUserRepository.findByUsername(p.getName());
        Iterable<ApplicationUser> allUsers = appUserRepository.findAll();
        List<ApplicationUser> friendsList = toList(allUsers);
        friendsList.remove(user);
        friendsList.removeAll(user.getFriends());
        m.addAttribute("friendsList", friendsList);

        m.addAttribute("userId", user.getId());
        m.addAttribute("principal", user);
        m.addAttribute("user", user);

        m.addAttribute("myProfile", true);
        m.addAttribute("user", true);
        return "myprofile";

    }

    @GetMapping("/myprofile")
    public String getMyProfilePage(Principal p, Model m) {
        ApplicationUser user = appUserRepository.findByUsername(p.getName());
        Iterable<ApplicationUser> allUsers = appUserRepository.findAll();
        List<ApplicationUser> friendsList = toList(allUsers);
        friendsList.remove(user);
        friendsList.removeAll(user.getFriends());
        m.addAttribute("friendsList", friendsList);

        m.addAttribute("userId", user.getId());
        m.addAttribute("principal", user);
        m.addAttribute("user", user);
        //set for conditions
        m.addAttribute("myProfile", true);
        m.addAttribute("user", true);
        return "myprofile";
    }

    //convert iterable=>list
    private List<ApplicationUser> toList(Iterable<ApplicationUser> iterable) {
        ArrayList<ApplicationUser> list = new ArrayList<ApplicationUser>();
        if (iterable != null) {
            for (ApplicationUser e : iterable) {
                list.add(e);
            }
        }
        return list;
    }

    @GetMapping("/signup")
    public String getSignupPage(Model m) {
        m.addAttribute("user", false);
        return "signup";
    }

    //To follow a user
    @PostMapping(value = "/users/{id}/friends")
    public RedirectView followUser(@PathVariable long id, Principal p, Model m) {
        //get user id from principal
        ApplicationUser user = (ApplicationUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        //add (principal) follower to followers add user to their followers
        ApplicationUser currentUser = appUserRepository.findById(id).get();
        ApplicationUser newFriend = appUserRepository.findById(user.getId()).get();

        if (!currentUser.getUsername().equals(p.getName()) || !newFriend.getUsername().equals(p.getName())) {
            throw new FriendDoesNotBelongToYouException("You must be friends.");
        }
        currentUser.getFriends().add(newFriend);
        newFriend.getFriends().add(currentUser);
        // save! yes please omg
        appUserRepository.save(currentUser);
        appUserRepository.save(newFriend);

        m.addAttribute("user", appUserRepository.findById(user.getId()).get());
        m.addAttribute("myProfile", true);
        m.addAttribute("user", true);
        // redirect back to the current user page
        return new RedirectView("/users/" + id);
    }

    // Route to feed page to view list of posts
    //TO DO: Fix this method
    @GetMapping(value = "/feed")
    public String getFeed(Model m, Principal p) {
        //get current user
        ApplicationUser currentUser = appUserRepository.findByUsername(p.getName());
        Set<ApplicationUser> followFriends = currentUser.getFriends();
        m.addAttribute("followingFriends", followFriends);

        return "feed";
    }

    //Route to view all users
    @GetMapping(value = "/users")
    public String getUsers(Principal p, Model m) {
        ApplicationUser user = appUserRepository.findByUsername(p.getName());
        Iterable<ApplicationUser> allUsers = appUserRepository.findAll();
        List<ApplicationUser> myList = toList(allUsers);
        System.out.println("running");
        System.out.println(myList.size());
        Set<ApplicationUser> followingFriends = user.getFriends();
        for (ApplicationUser f : followingFriends) {
            myList.remove(f);
        }
        //remove me from the list
        myList.remove(user);
        m.addAttribute("potentialFriends", myList);
        //TO-Do update template
        m.addAttribute("myProfile", true);
        m.addAttribute("user", true);

        return "users";
    }

    @GetMapping("/friends/{id}")
    public String getFriendPage(@PathVariable Long id, Principal p, Model m) {
        ApplicationUser user = appUserRepository.findById(id).get();

        //current logged in user
        ApplicationUser currentUser = appUserRepository.findByUsername(p.getName());

        if (currentUser.getFriends().contains(user)) {
            m.addAttribute("user", user);
            m.addAttribute("isFriend", true);

        } else {
            m.addAttribute("user", user);
            m.addAttribute("isFriend", false);
        }

        return "friendProfile";
    }

    @PostMapping("/friends/{id}")
    public String postToFollow(@PathVariable Long id, Principal p, Model m) {
        ApplicationUser user = appUserRepository.findById(id).get();
        //current logged in user
        ApplicationUser currentUser = appUserRepository.findByUsername(p.getName());

        if (currentUser.getFriends().contains(user)) {
            currentUser.getFriends().remove(user);
            appUserRepository.save(currentUser);
            m.addAttribute("user", user);
            m.addAttribute("isFriend", false);
        } else {
            currentUser.getFriends().add(user);
            m.addAttribute("user", user);
            m.addAttribute("isFriend", true);
        }
        return "friendProfile";
    }
}

// came from https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
@ResponseStatus(value = HttpStatus.FORBIDDEN)
class FriendDoesNotBelongToYouException extends RuntimeException {
    public FriendDoesNotBelongToYouException(String s) {
        super(s);
    }
}


