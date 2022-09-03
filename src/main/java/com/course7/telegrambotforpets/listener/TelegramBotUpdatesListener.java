package com.course7.telegrambotforpets.listener;

import com.course7.telegrambotforpets.service.message.MessageService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    private final MessageService messageService;

    public TelegramBotUpdatesListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            telegramBot.execute(messageService.handle(update));});
         /*   if (update.message().text().equals("/start")){
              telegramBot.execute(new SendMessage(update.message().chat().id(), "privet").replyMarkup(new ReplyKeyboardMarkup(
                      new String[]{"О приюте", "Как взять собаку из приюта"},
                      new String[]{"Другой приют", "Прислать отчет о питомце", "Позвать волонтёра"})
                      .oneTimeKeyboard(true)
                      .resizeKeyboard(true)
                      .selective(true)));

            }
            if (update.message().text().equals("/DOG")){
                telegramBot.execute(new SendMessage(update.message().chat().id(), "Gav-gav"));}

        });*/
           return UpdatesListener.CONFIRMED_UPDATES_ALL;}
}