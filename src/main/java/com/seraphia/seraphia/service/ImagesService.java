package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Images;

import java.util.List;

public interface ImagesService {
    List<Images> getAllImages();

    Images getImageById(Long id);

    Images addImage(Images image);

    Images deleteImageById(Long id);

    Images updateImageById(Long id, Images imageUpdated);
}
