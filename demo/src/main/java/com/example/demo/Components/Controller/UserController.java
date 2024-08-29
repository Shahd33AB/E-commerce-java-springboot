package com.example.demo.Components.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import com.example.demo.Components.User;
import com.example.demo.Components.Services.UserServices;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServices userService;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody @Valid Map <String,String> loginRequest) {
        User login = new User(loginRequest.get("email"), loginRequest.get("password"));
        User user = userService.login(login);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/add")
    public User addUser(@RequestBody @Valid Map <String,String> request) {
        User userDto = new User(request.get("name"), request.get("email"),
        request.get("password"), request.get("phone"));
        return userService.addUser(userDto);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        Optional<User> userOptional = userService.getUserById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)).getBody();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody Map <String,String> request) {
        User userDto = new User(request.get("name"), request.get("email"),
        request.get("password"), request.get("phone"));
        User updatedUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<HashMap<String,List<String>>> handelException (BindException ex){
        List<String>errors=ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        HashMap<String,List<String>>errMap = new HashMap<>();
        errMap.put("errors",errors);
        return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
    }
}
