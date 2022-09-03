package com.course7.telegrambotforpets.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements CommandService {


    public SendMessage start(Update update) {
                String message =  update.message().from().username() + ", Привет!"+ " \n"+"Чем могу помочь?";

        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"О приюте", "Как взять собаку из приюта"},
                new String[]{"Прислать отчет о питомце"},
                new String[]{"Позвать волонтера!"})
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);

        return new SendMessage(update.message().chat().id(), message).replyMarkup(replyKeyboardMarkup);
    }
}



