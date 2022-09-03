package com.course7.telegrambotforpets.service.message;

import com.course7.telegrambotforpets.model.MessageCounter;
import com.course7.telegrambotforpets.model.User;
import com.course7.telegrambotforpets.repository.MessageCounterRepository;
import com.course7.telegrambotforpets.repository.UserRepository;
import com.course7.telegrambotforpets.service.commands.CommandService;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public  class MessageServiceImpl implements MessageService {

    private final UserRepository userRepository;
    private final CommandService commandService;
    private final MessageCounterRepository messageCounterRepository;


    public MessageServiceImpl(UserRepository userRepository, CommandService commandService, MessageCounterRepository messageCounterRepository, MessageCounterRepository messageCounterRepository1, MessageCounterRepository messageCounterRepository2) {
        this.userRepository = userRepository;
        this.commandService = commandService;
        this.messageCounterRepository = messageCounterRepository;
    }


    public SendMessage handle(Update update) {
        User user = getUser(update.message());
        increaseMessageCounter(user);

        if (update.message().text().equals("/start")) {
            return commandService.start(update);
        }

        return new SendMessage(
                update.message().chat().id(),
                "Введите другую команду"
        );
    }

    private User getUser(Message message) {
        User user = userRepository.findByChatIdEquals(message.chat().id());

        if (user != null) {
            return user;
        }
        user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setTelegramId(message.from().id());
        user.setChatId(message.chat().id());
        user.setUsername(message.from().username());
        user.setFirstName(message.from().firstName());
        user.setLastName(message.from().lastName());
        user.setBot(message.from().isBot());

        return userRepository.save(user);
    }


    private void increaseMessageCounter(User user) {
     MessageCounter messageCounter = messageCounterRepository.findByUserIdEquals(user.getId());

        if (messageCounter == null) {
            messageCounter = new MessageCounter();
            messageCounter.setUserId(user.getId());
            messageCounter.setCounter(0);
        }

        messageCounter.increaseCounter();

        messageCounterRepository.save(messageCounter);
    }

}

