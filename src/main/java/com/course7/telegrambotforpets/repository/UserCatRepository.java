package com.course7.telegrambotforpets.repository;

import com.course7.telegrambotforpets.model.UserCat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCatRepository extends JpaRepository<UserCat, Long> {
    Optional<UserCat> findByChatId(Long chatId);
}