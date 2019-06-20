# CodeFellowShip

## Author
Liz Mahoney

## Version 
1.0.0

## Overview
For your remaining labs before the midterm project, you’ll create an app called CodeFellowship that allows people 
learning to code to connect with each other and support each other on their coding journeys.

## Getting Started

***To Run Application***

In terminal run: `./gradlew bootrun`

***To view gradle commands***

In terminal run: `./gradlew tasks`

***To Run Test***

In terminal run: `./gradlew test`

## Setup

Create a new repo Codefellowship to hold your work for the last 5 Spring labs. Use the Spring `Initializr` to set 
up an app with dependencies on Web, Thymeleaf, JPA, Postgres, and Security (and optionally DevTools for auto refresh 
of app on building). Remember to do your initial commit on the master branch before creating your feature branch. Also, 
see the below note about configuring Spring Security.

## Features

### 6/20/19

-[x] Ensure that users can’t perform SQL injection or HTML injection with their posts.
-[x] Allow users to follow other users. Following a user means that their posts show up in the logged-in user’s feed,
 where they can see what all of their followed users have posted recently.
    -[x] Ensure there is some way (like a users index page) that a user can discover other users on the service.
    -[x] On a user profile page that does NOT belong to the currently logged-in user, display a “Follow” button. When a
    user clicks that follow button, the logged-in user is now following the viewed-profile-page user.
        note: this will require a self-join on ApplicationUsers.
-[x] A user can visit a url (like /feed) to view all of the posts from the users that they follow.
    -[x] Each post should have a link to the user profile of the user who wrote the post.
***Old feature tasks that are still required***
-[x] A splash page with basic information about the site
-[x] The ability for users to register for new accounts and log in.
-[x] The ability for logged-in users to create posts.
-[x] The ability to see a user’s posts, along with their profile information and a default profile picture, on their
 profile page.
-[x] A pleasing design throughout the site.
-[x] Thymeleaf templates & fragments used appropriately to keep view code DRY.
-[x] Smooth error handling with appropriate responses to bad requests.
-[x] Integration testing on (at minimum) the splash page, login, and sign up routes.

### 6/19/19

-[x] Allow users to log in to CodeFellowship and create posts.

-[x] Using the above cheat sheet, add the ability for users to log in to your app.
    -[x] Upon logging in, users should be taken to a /myprofile route that displays their information.
-[x] Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes 
should be limited to logged-in users.
-[x] Ensure that user registration also logs users into your app automatically.
-[X] Add a Post entity to your app.
    -[X] A Post has a body and a createdAt timestamp.
    -[X] A logged-in user should be able to create a Post, and a post should belong to the user that created it.
        **hint: this is a relationship between two pieces of data
-[X] A user’s posts should be visible on their profile page.
-[X] When a user is logged in, the app should display the user’s username on every page (probably in the heading).

***Feature tasks from yesterday that are still required***
-[x] The site should have a splash page at the root route (/) that contains basic information about the site, as well as 
a link to the “sign up” page.
-[x] An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth,
 bio, and any other fields you think are useful.
-[X] The site should allow users to create an ApplicationUser on the “sign up” page. (For now, there is no such thing
 as 
login.)
-[x] Your Controller should have an @Autowired private PasswordEncoder passwordEncoder; and use that to run 
passwordEncoder.encode(password) before saving the password into the new user.
-[x] The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
-[X] This should include a default profile picture, which is the same for every user, and their basic information.
-[X] The site should be well-styled and attractive.
-[X] The site should use reusable templates for its information. (At a minimum, it should have one Thymeleaf fragment 
that is used on multiple pages.)
-[x] The site should have a non-whitelabel error handling page that lets the user know, at minimum, the error code and a 
brief message about what went wrong.
-[X] The site should contain integration testing. At a minimum, there should be tests to ensure basic functionality 
for the splash page and the sign up page.

### 6/18/19

Build an app that allows users to create their profile on CodeFellowship.

- The site should have a splash page at the root route (/) that contains basic information about the site, as well as 
a link to the “sign up” page.
- An `ApplicationUser` should have a `username`, `password` ( hashed using `BCrypt`), `firstName`, `lastName`, `dateOfBirth`, `bio`, 
and any other fields you think are useful.
- The site should allow users to create an `ApplicationUser` on the “`sign up`” page.
- Your Controller should have an `@Autowired private PasswordEncoder passwordEncoder;` and use that to run 
`passwordEncoder.encode(password)` before saving the password into the new user.
- The site should have a page which allows viewing the data about a single `ApplicationUser`, at a route like `/users/{id}`.
- This should include a default profile picture, which is the same for every user, and their basic information.
- Using the above cheat sheet, add the ability for users to log in to your app.
- Upon logging in, users should be taken to a `/myprofile` route that displays their information.
- Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes 
should be limited to logged-in users.
- Ensure that user registration also logs users into your app automatically.
- The site should be reasonably styled. (This can be using CSS that you did not create yourself.)
- The site should contain integration testing. At a minimum, there should be tests to ensure basic functionality for 
the splash page and the sign up page.
- To correctly configure Spring Security, please copy-paste this entire code snippet into a file called 
WebSecurityConfig.java

## Resources 
https://github.com/codefellows/seattle-java-401d4/blob/master/SpringAuthCheatSheet.md
https://www.baeldung.com/hibernate-many-to-many

Collaborator Help: Xia Liu




