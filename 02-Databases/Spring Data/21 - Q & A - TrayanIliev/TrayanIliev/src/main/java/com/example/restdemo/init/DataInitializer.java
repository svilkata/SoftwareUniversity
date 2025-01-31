package com.example.restdemo.init;

import com.example.restdemo.model.Post;
import com.example.restdemo.model.User;
import com.example.restdemo.service.PostService;
import com.example.restdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.restdemo.model.User.ROLE_ADMIN;
import static com.example.restdemo.model.User.ROLE_USER;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    private static final List<Post> SAMPLE_POSTS = List.of(
            new Post("Welcome to Spring Data", "Developing data access object with Spring Data is easy ...",
                    "https://www.publicdomainpictures.net/pictures/320000/velka/rosa-klee-blute-blume.jpg"),
            new Post("Reactive Spring Data", "Check R2DBC for reactive JDBC API ...",
                    "https://www.publicdomainpictures.net/pictures/70000/velka/spring-grass-in-sun-light.jpg"),
            new Post("New in Spring 5", "Webflux provides reactive and non-blocking web service implemntation ...",
                    "https://www.publicdomainpictures.net/pictures/320000/velka/blute-blumen-garten-bluhen-1577191608UTW.jpg"),
            new Post("Beginnig REST with Spring 5", "Spring MVC and WebFlux make implemeting RESTful services really easy ...",
                    "https://www.publicdomainpictures.net/pictures/20000/velka/baby-lamb.jpg")
    );

    private static final List<User> SAMPLE_USERS = List.of(
            new User("Default", "Admin", "admin", "admin", ROLE_ADMIN,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Crystal_Clear_kdm_user_female.svg/500px-Crystal_Clear_kdm_user_female.svg.png"),
            new User("Ivan", "Pertov", "ivan", "ivan", ROLE_USER,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Crystal_Clear_app_Login_Manager.svg/500px-Crystal_Clear_app_Login_Manager.svg.png")
    );

    @Override
    public void run(String... args) throws Exception {
        SAMPLE_USERS.forEach(user -> userService.createUser(user));
        log.info("Created Users: {}", userService.getUsers());
        SAMPLE_POSTS.forEach(post -> {
            post.setAuthor(userService.getUserById(1L));
            postService.createPost(post);
        });
        log.info("Created Posts: {}", postService.getPosts());
    }
}
