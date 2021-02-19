package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.UserDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDTO findByUserName(String username);
}
