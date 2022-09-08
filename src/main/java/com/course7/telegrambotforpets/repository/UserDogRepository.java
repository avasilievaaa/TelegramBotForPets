package com.course7.telegrambotforpets.repository;

import com.course7.telegrambotforpets.model.UserDog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDogRepository extends JpaRepository<UserDog, Long> {
    Optional<UserDog> findByChatId(Long chatId);
}