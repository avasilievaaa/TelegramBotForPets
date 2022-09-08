package com.course7.telegrambotforpets.repository;

import com.course7.telegrambotforpets.model.Picture;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PictureRepository extends PagingAndSortingRepository<Picture, Long> {
    Optional<Picture> findByUserCatId(Long chatId);
}