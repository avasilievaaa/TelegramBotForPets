package com.course7.telegrambotforpets.service;

import com.course7.telegrambotforpets.listener.TelegramBotUpdatesListener;
import com.course7.telegrambotforpets.model.Picture;
import com.course7.telegrambotforpets.repository.PictureRepository;
import com.course7.telegrambotforpets.repository.UserCatRepository;
import com.course7.telegrambotforpets.repository.UserDogRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.pengrad.telegrambot.model.File;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Service
@Transactional
public class PictureService {
    @Value("Picture")
    private String picturesDir;
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final PictureRepository pictureRepository;
    private final UserCatRepository userCatRepository;
    private final UserDogRepository userDogRepository;

    public PictureService(TelegramBot telegramBot, PictureRepository pictureRepository, UserCatRepository userCatRepository, UserDogRepository userDogRepository) {
        this.telegramBot = telegramBot;
        this.pictureRepository = pictureRepository;
        this.userCatRepository = userCatRepository;
        this.userDogRepository = userDogRepository;
    }

    /**
     * вызов метода репозитория по сохранению в БД
     *
     * @param chatId
     * @param pictureFile
     * @param file
     * @throws IOException
     */
    public void uploadPicture(Long chatId, byte[] pictureFile, File file, boolean isCat) throws IOException {
        logger.info("Был вызван метод для загрузки фотографии  '{}'", chatId);
        Path filePath = Path.of(picturesDir, "pictures" + "." + getExtensions(Objects.requireNonNull(file.filePath())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        Picture picture = findPicture(chatId);
        picture.setFilePath(filePath.toString());
        picture.setFileSize(file.fileSize());
        picture.setData(pictureFile);
        if (isCat) {
            try {
                picture.setUserCat(userCatRepository.findByChatId(chatId).orElseThrow());
                pictureRepository.save(picture);
                telegramBot.execute(new SendMessage(chatId, "Отлично! Мы видим, что питомец счастлив в новом доме!"));

            } catch (Exception e) {
                logger.error("pictureservice");
                telegramBot.execute(new SendMessage(chatId, "Пожалуйста, усыновите сначала питомца"));
            }
        } else {
            try {
                picture.setUserDog(userDogRepository.findByChatId(chatId).orElseThrow());
                pictureRepository.save(picture);
                telegramBot.execute(new SendMessage(chatId, "Отлично! Мы видим, что питомец счастлив в новом доме!"));

            } catch (Exception e) {
                logger.error("pictureservice");
                telegramBot.execute(new SendMessage(chatId, "Пожалуйста, усыновите сначала питомца"));
            }

        }
    }

    /**
     * метод поиска фото, вызывающий метод репозитория по поиску из БД
     *
     * @param chatId
     * @return
     */
    public Picture findPicture(Long chatId) {
        try {
            logger.info("Был вызван метод для поиска фотографии '{}'", chatId);
            return pictureRepository.findByUserCatId(chatId).orElse(new Picture());
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}