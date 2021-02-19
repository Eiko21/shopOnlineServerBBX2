package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.controller.auth;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.UserDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.login.LoginRequest;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.login.LoginResponse;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.logout.LogoutResponse;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.security.util.SecurityCipher;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

//    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<LoginResponse> login(@CookieValue(name = "accessToken", required = false) String accessToken,
//                                               @CookieValue(name = "refreshToken", required = false) String refreshToken,
//                                               @RequestBody LoginRequest loginRequest){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
//        String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
//        return userService.login(loginRequest, decryptedAccessToken, decryptedRefreshToken);
//    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> login(@RequestBody String userLogged) throws JsonProcessingException {
        UserDTO userDTO = objectMapper.readValue(userLogged, UserDTO.class);
        return userService.login(userDTO.getUsername(), userDTO.getPassword());
    }

//    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<LoginResponse> refreshToken(@CookieValue(name = "accessToken", required = false) String accessToken,
//                                                      @CookieValue(name = "refreshToken", required = false) String refreshToken) {
//        String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
//        String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
//        return userService.refresh(decryptedAccessToken, decryptedRefreshToken);
//    }
}
