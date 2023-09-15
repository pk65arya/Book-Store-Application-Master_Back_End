package com.brigelabz.bookstoreapp.repository;

import com.brigelabz.bookstoreapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getEncodePasswordByEmail(String email);

    User getEmailIdByEmail(String email);

    User getUserById(Long id);

    User getTokenByEmail(String email);
}