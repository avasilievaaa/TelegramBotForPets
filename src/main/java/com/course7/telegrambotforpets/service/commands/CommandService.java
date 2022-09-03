package com.course7.telegrambotforpets.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface CommandService {

    SendMessage start(Update update);

}
