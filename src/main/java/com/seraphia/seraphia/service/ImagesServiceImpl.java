package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Images;
import com.seraphia.seraphia.model.Products;
import com.seraphia.seraphia.repository.ImagesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImagesServiceImpl implements ImagesService {
    private final ImagesRepository imagesRepository;

    @Override
    public List<Images> getAllImages() {
        return imagesRepository.findAll();
    }

    @Override
    public Images getImageById(Long id) {
        return imagesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La imagen con el id " + id + " no existe")
        );
    }

    @Override
    public Images addImage(Images image) {
        return this.imagesRepository.save(image);
    }

    @Override
    public Images deleteImageById(Long id) {
        Images tmp = null;
        if (!imagesRepository.existsById(id)) return tmp;
        tmp = imagesRepository.findById(id).get();
        imagesRepository.deleteById(id);
        return tmp;
    }

    @Override
    public Images updateImageById(Long id, Images imageUpdated) {
        Optional<Images> optionalImage = imagesRepository.findById(id);
        if (optionalImage.isEmpty()) throw new IllegalArgumentException("La imagen con el id " + id + " no existe");
        Images originalImage = optionalImage.get();
        if (imageUpdated.getImageUrl() != null) originalImage.setImageUrl(imageUpdated.getImageUrl());
        if (imageUpdated.getImageOrder() != null) originalImage.setImageOrder(imageUpdated.getImageOrder());
        return imagesRepository.save(originalImage);
    }
}
