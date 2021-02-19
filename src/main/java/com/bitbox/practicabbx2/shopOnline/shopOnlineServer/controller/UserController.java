package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.controller;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.UserDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private IUserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/api/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/api/users/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable(name = "username") String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody String user) throws JsonProcessingException {
        UserDTO userDTO = objectMapper.readValue(user, UserDTO.class);
        return userService.createUser(userDTO);
    }

    @DeleteMapping(value = "/api/users/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
