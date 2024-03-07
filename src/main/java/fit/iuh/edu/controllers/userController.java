package fit.iuh.edu.controllers;

import fit.iuh.edu.entities.User;
import fit.iuh.edu.repositories.UserRepository;
import fit.iuh.edu.services.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class userController {

    @Autowired
    private UserDao userService;






    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    @PostMapping("/users")
    public User listAll(@RequestBody User user) {

        return userService.save(user);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable String id, @RequestBody User User) {

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
             userService.deleteUser(id);
    }
}
