package com.punjab.elasticsearch.autocomplete.api.rest;

import com.punjab.elasticsearch.autocomplete.service.ElasticSearchDataLoader;
import com.punjab.elasticsearch.autocomplete.model.User;
import com.punjab.elasticsearch.autocomplete.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private ElasticSearchDataLoader dataLoader;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.listAll();
    }

    @GetMapping("hello")
    public String getHello() {
        return "Hello Kubernetes";
    }

    @GetMapping(path = "/search")
    public List<User> searchUsers(@RequestParam String keywords) {
        return this.userService.search(keywords);
    }

    @SneakyThrows
    @PostMapping("/loadusers")
    public List<User> loadUsers(@RequestParam String keywords) {
        return dataLoader.loadUsersFromFile();
    }

}
