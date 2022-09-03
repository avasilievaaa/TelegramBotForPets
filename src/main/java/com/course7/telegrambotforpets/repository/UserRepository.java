package com.course7.telegrambotforpets.repository;


import com.course7.telegrambotforpets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByChatIdEquals(@NonNull long chatId);

}