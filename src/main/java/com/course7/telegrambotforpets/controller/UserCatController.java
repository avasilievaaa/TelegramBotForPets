package com.course7.telegrambotforpets.controller;

import com.course7.telegrambotforpets.model.UserCat;
import com.course7.telegrambotforpets.service.UserCatService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("cat-user")
public class UserCatController {
    private final UserCatService userCatService;

    public UserCatController(UserCatService userCatService) {
        this.userCatService = userCatService;
    }

    /**
     * вызов метода сервиса по созданию объекта userCat в БД
     * * @param userCat
     *
     * @return
     */
    @PostMapping
    public UserCat createCatsUser(@RequestBody UserCat userCat) {
        return userCatService.createUserCat(userCat);
    }

    /**
     * вызов метода сервиса по извлечению обьекта userDog из БД
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public UserCat getCatsUserInfo(@PathVariable Long id) {
        return userCatService.findUserCat(id);
    }

    /**
     * вызов метода сервиса по редактированию в БД
     *
     * @param userCat
     * @return
     */
    @PutMapping
    public UserCat editCatsUser(@RequestBody UserCat userCat) {
        return userCatService.editUserCat(userCat);
    }

    @DeleteMapping("/{id}")
    public void deleteCatsUser(@PathVariable Long id) {
        userCatService.deleteUserCat(id);
    }

    @GetMapping
    public Collection<UserCat> getAllCatsUser() {
        return userCatService.getAllUsersCat();
    }
}