package com.course7.telegrambotforpets.service.message;


import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface MessageService {

    SendMessage handle(Update update);

}