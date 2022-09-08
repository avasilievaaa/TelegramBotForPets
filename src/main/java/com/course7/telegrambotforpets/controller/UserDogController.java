package com.course7.telegrambotforpets.controller;

import com.course7.telegrambotforpets.model.UserDog;
import com.course7.telegrambotforpets.service.UserDogService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("dog-user")
public class UserDogController {
    private final UserDogService userDogService;

    public UserDogController(UserDogService userDogService) {
        this.userDogService = userDogService;
    }

    /**
     * вызов метода сервиса по созданию объекта userDog в БД
     *
     * @param userDog
     * @return
     */
    @PostMapping
    public UserDog createDogsUser(@RequestBody UserDog userDog) {
        return userDogService.createUserDog(userDog);
    }

    /**
     * вызов метода сервиса по извлечению обьекта userDog из БД
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public UserDog getDogsUserInfo(@PathVariable Long id) {
        return userDogService.findUserDog(id);
    }

    /**
     * вызов метода сервиса по редактированию в БД
     *
     * @param userDog
     * @return
     */
    @PutMapping
    public UserDog editDogsUser(@RequestBody UserDog userDog) {
        return userDogService.editUserDog(userDog);
    }

    @DeleteMapping("/{id}")
    public void deleteDogsUser(@PathVariable Long id) {
        userDogService.deleteUserDog(id);
    }

    @GetMapping
    public Collection<UserDog> getAllDogsUser() {
        return userDogService.getAllUsersDog();
    }
}