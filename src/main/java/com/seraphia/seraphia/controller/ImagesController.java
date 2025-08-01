package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.model.Images;
import com.seraphia.seraphia.service.ImagesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/images")
@AllArgsConstructor
public class ImagesController {
    private ImagesService imagesService;

    @GetMapping
    public List<Images> getAllImages() {
        return this.imagesService.getAllImages();
    }

    @GetMapping(path = "{imageId}")
    public Images getImageById(@PathVariable("imageId") Long id) {
        return this.imagesService.getImageById(id);
    }

    @PostMapping
    public Images addImage(@RequestBody Images image) {
        return this.imagesService.addImage(image);
    }

    @DeleteMapping(path = "{imageId}")
    public Images deleteImageById(@PathVariable("imageId") Long id) {
        return this.imagesService.deleteImageById(id);
    }

    @PutMapping(path = "{imageId}/change-image")
    public Images updateImageById(@PathVariable("imageId") Long id, @RequestBody Images imageUpdated) {
        return this.imagesService.updateImageById(id, imageUpdated);
    }
}
