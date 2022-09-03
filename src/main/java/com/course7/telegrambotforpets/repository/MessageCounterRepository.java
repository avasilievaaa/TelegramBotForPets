package com.course7.telegrambotforpets.repository;


import com.course7.telegrambotforpets.model.MessageCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface MessageCounterRepository extends JpaRepository<MessageCounter, Long> {

    MessageCounter findByUserIdEquals(@NonNull long userId);

}