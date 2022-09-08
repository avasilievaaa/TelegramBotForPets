package com.course7.telegrambotforpets.service;

import com.course7.telegrambotforpets.listener.TelegramBotUpdatesListener;
import com.course7.telegrambotforpets.model.UserDog;
import com.course7.telegrambotforpets.repository.CatsDogsInterface;
import com.course7.telegrambotforpets.repository.UserDogRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDogService implements CatsDogsInterface {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final UserDogRepository userDogRepository;

    public UserDogService(UserDogRepository userDogRepository, TelegramBot telegramBot) {
        this.userDogRepository = userDogRepository;
        this.telegramBot = telegramBot;
    }

    private final TelegramBot telegramBot;

    @Override
    /**
     * метод, возвращающий меню в ветке собачий приют
     */
    public void getMenu(Update update) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Узнать информацию о приюте");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Как взять собаку из приюта");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("информация1");
        button2.callbackData("взять1");
        button3.callbackData("отчет1");
        button4.callbackData("волонтер1");
        keyboardMarkup.addRow(button1);
        keyboardMarkup.addRow(button2);
        keyboardMarkup.addRow(button3);
        keyboardMarkup.addRow(button4);

        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Отлично! Чем могу помочь?").replyMarkup(keyboardMarkup));

    }

    @Override
    /**
     * метод, возвращающий подменю 1 этапа
     */
    public void stepOne(Update update) {
        InlineKeyboardMarkup keyboardMarkupForStepOne = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Расписание, адрес, схема проезда");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Оформить пропуск на авто");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Техника безопасности");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Оставить контактные данные");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("расписание1");
        button2.callbackData("автомобиль1");
        button3.callbackData("ТБ1");
        button4.callbackData("контакты1");
        button5.callbackData("волонтер1");
        keyboardMarkupForStepOne.addRow(button1);
        keyboardMarkupForStepOne.addRow(button2);
        keyboardMarkupForStepOne.addRow(button3);
        keyboardMarkupForStepOne.addRow(button4);
        keyboardMarkupForStepOne.addRow(button5);

        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Добро пожаловать в собачий приют! Что будем делать?").replyMarkup(keyboardMarkupForStepOne));
    }

    @Override
    /**
     * метод, возвращающий подменю 2 этапа
     */
    public void stepTwo(Update update) {
        InlineKeyboardMarkup keyboardMarkupForStepTwo = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Список необходимых документов");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Рекомендации по транспортировке");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Обустройство дома для питомца");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Советы кинолога");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Список кинологов");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Причины отказа");
        InlineKeyboardButton button7 = new InlineKeyboardButton("Оставить контактные данные");
        InlineKeyboardButton button8 = new InlineKeyboardButton("Позвать волонтера");
        button1.callbackData("документы1");
        button2.callbackData("транспортировка1");
        button3.callbackData("обустройство1");
        button4.callbackData("кинолог1");
        button5.callbackData("списокКинологов1");
        button6.callbackData("отказ1");
        button7.callbackData("контакты1");
        button8.callbackData("волонтер1");
        keyboardMarkupForStepTwo.addRow(button1);
        keyboardMarkupForStepTwo.addRow(button2);
        keyboardMarkupForStepTwo.addRow(button3);
        keyboardMarkupForStepTwo.addRow(button4);
        keyboardMarkupForStepTwo.addRow(button5);
        keyboardMarkupForStepTwo.addRow(button6);
        keyboardMarkupForStepTwo.addRow(button7);
        keyboardMarkupForStepTwo.addRow(button8);

        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Здравствуйте! Мы очень рады, что Вы решили завести нового друга! " +
                        "Чем можем помочь?").replyMarkup(keyboardMarkupForStepTwo));
    }

    @Override
    public void stepThree(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Для отчета просим прислать фото животного, его рацион, общее самочувствие и информацию об изменении в поведении."));
    }

    @Override
    /**
     * метод, возвращающий адрес приюта
     */
    public void sendAddress(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Наш адрес: г.Москва, ул.Московская, дом 3"));
    }

    @Override
    /**
     * метод, возвращающий сообщение с информацией о том, как оформить пропуск
     */
    public void autoPass(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Чтобы получить пропуск для своего автомобиля, позвоните в отдел безопасности по номеру 8*******"));
    }

    @Override
    /**
     * метод, возвращающий правила техники безопасности
     */
    public void beSafe(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Находясь на территории приюта, пожалуйста, соблюдайте наши правила и технику безопасности. " +
                        "В частности, не оставляййте после себя мусор, не кормите животных, " +
                        "самостоятельно не открывайте вольеры."));
    }

    @Override
    /**
     * метод, запрашивающий контактные данные потенциального нового хозяина питомца
     */
    public void giveMeYourName(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Пожалуйста, введите свои ФИО в следующем виде: Петров Петр Петрович"));
    }

    @Override
    /**
     * метод, сохраняющий в БД пользователя
     */
    public void saveUser(Message message) {
        if (message != null) {
            Pattern pattern = Pattern.compile("([А-ЯЁ][а-яё]+[\\-\\s]?){3,}");
            Matcher matcher = pattern.matcher(message.text());
            logger.info("поделил");
            if (matcher.matches()) {
                String ownerName = matcher.group();
                LocalDate probationDate = LocalDate.now().plusDays(30);
                UserDog object = new UserDog(message.chat().id(), ownerName, "no", probationDate);
                userDogRepository.save(object);
                logger.info("сохранил");
            }
        }
    }

    @Override
    /**
     *метод, возвращающий сообщение на клик "позвать волонтера"
     */
    public void volunteer(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Ожидайте, скоро с Вами свяжется волонтер"));
    }

    @Override
    /**
     * метод, возвращающий сообщение про необходимые документы
     */
    public void docs(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Для того, чтобы взять щенка, просим предоставить следующие документы: справка с места работы, " +
                        "отсутствие вирусных инфекций в будущем доме."));
    }

    @Override
    /**
     * метод, возвращающий сообщение с правилами транспортировки животного
     */
    public void transport(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Транспортировать животное в новый дом желательно на личном автомобиле, " +
                        "создав заранее комфортные условия для щенка." +
                        " При себе иметь поводок, ошейник по размеру и намордник."));
    }

    @Override
    /**
     * метод, возвращающий сообщение о правилах домашнего содержания
     */
    public void home(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Первым делома покажите щенку его миску и игрушки." +
                        " Окружите питомца лаской и заботой, постарайтесь быть рядом в первое время, " +
                        "животные не быстро привыкают к новому дому, относятся ко всему с опаской."));
    }

    @Override
    /**
     * метод, возвращающий сообщение о возможном отказе в усыновлении
     */
    public void refusal(Update update) {
        telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Мы вправе отказать Вам в усыновлении питомца при грубом поведении по отношению к животному, " +
                        " а также отсутствии необходимых документов"));
    }

    /**
     * метод, возвращающий сообщение о правилах воспитания собаки
     */
    public SendResponse cynologist(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Разговаривайте со своей собакой, следите за её состоянием, " +
                        "ставьте границы допустимого."));
    }

    /**
     * метод, возвращающий сообщение о подборе кинолога для собаки
     *
     * @param update
     * @return
     */
    public SendResponse cynologistList(Update update) {
        return telegramBot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                "Позвоните нам по номеру 8******* и мы подберем для Вас проверенного кинолога"));
    }

    public UserDog createUserDog(UserDog userDog) {
        return userDogRepository.save(userDog);
    }

    public UserDog findUserDog(Long id) {
        return userDogRepository.findById(id).get();
    }

    public UserDog editUserDog(UserDog userDog) {
        return userDogRepository.save(userDog);
    }

    public void deleteUserDog(Long id) {
        userDogRepository.deleteById(id);
    }

    public Collection<UserDog> getAllUsersDog() {
        return userDogRepository.findAll();
    }
}