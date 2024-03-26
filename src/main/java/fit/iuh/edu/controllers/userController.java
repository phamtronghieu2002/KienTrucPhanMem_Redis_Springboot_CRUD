package fit.iuh.edu.controllers;

import fit.iuh.edu.entities.User;
import fit.iuh.edu.repositories.UserRepository;
import fit.iuh.edu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class userController {

    @Autowired
    private UserService userService;
    @Autowired
    private  UserRepository userRepository;







    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {

        return userService.findUserById(id);
    }
    @PostMapping("/user")
    public User listAll(@RequestBody User user) {

        return userService.save(user);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable String id, @RequestBody User User) {

    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
             userService.deleteUser(id);
    }
}
