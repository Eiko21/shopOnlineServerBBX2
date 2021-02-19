package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.UserDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDTODetailtsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userRepository.findByUserName(username);
        return user != null ? user : (UserDetails) new UsernameNotFoundException("Username "+username+" not found");
    }
}
