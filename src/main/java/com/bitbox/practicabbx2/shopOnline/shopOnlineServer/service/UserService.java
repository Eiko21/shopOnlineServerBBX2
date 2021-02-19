package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.converters.UserDTOAssembler;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.UserDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.auth.TokenDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.login.LoginRequest;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.login.LoginResponse;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.logout.LogoutResponse;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.exception.ResourceNotFoundException;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.User;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository.UserRepository;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.security.util.CookieUtil;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDTOAssembler userDTOAssembler;

    @Autowired
    private TokenProviderService tokenProvider;

    @Autowired
    private CookieUtil cookieUtil;

    @Override
    public ResponseEntity<UserDTO> login(String username, String password) {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()){
            for (User user: users) {
                if(user.getUserName().equalsIgnoreCase(username) && user.getUserpassword().equalsIgnoreCase(password))
                    return ResponseEntity.ok().body(userDTOAssembler.convertToDto(user));
            }
        } return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();

        if(!users.isEmpty()){
            for (User user: users) usersDTO.add(userDTOAssembler.convertToDto(user));
            return ResponseEntity.ok().body(usersDTO);
        } return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserDTO> getUserByUsername(String username) {
        UserDTO userDTO = userRepository.findByUserName(username);
        return userDTO != null ? ResponseEntity.ok().body(userDTO) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        userRepository.save(userDTOAssembler.convertToPojo(userDTO));
        return ResponseEntity.ok().body(userDTO);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    private void addAccessTokenCookie(HttpHeaders responseHeaders, TokenDTO newAccessToken) {
        responseHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(newAccessToken.getTokenValue(),
                newAccessToken.getDuration()).toString());
    }

}
