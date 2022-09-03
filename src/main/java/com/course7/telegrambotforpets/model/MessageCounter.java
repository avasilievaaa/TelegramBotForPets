package com.course7.telegrambotforpets.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = MessageCounter.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class MessageCounter {

    public static final String TABLE_NAME = "message_counter";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "counter")
    private long counter;

    public void increaseCounter() {
        ++counter;
    }

}