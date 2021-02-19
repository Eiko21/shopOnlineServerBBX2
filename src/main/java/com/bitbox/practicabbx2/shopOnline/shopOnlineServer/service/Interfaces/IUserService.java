package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<UserDTO> login(String username, String password);
    ResponseEntity<List<UserDTO>> getUsers();
    ResponseEntity<UserDTO> getUserByUsername(String username);
    ResponseEntity<UserDTO> createUser(UserDTO userDTO);
    ResponseEntity<Void> deleteUser(Long userId);
}
