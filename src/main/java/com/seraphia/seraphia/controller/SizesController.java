package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.model.Sizes;
import com.seraphia.seraphia.service.SizesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping(path = "api/sizes")
@AllArgsConstructor
public class SizesController {
    private SizesService sizesService;

    @GetMapping
    public List<Sizes> getAllSizes() {
        return this.sizesService.getAllSizes();
    }

    @GetMapping(path = "{sizeId}")
    public Sizes getSizeById(@PathVariable("sizeId") Long id) {
        return this.sizesService.getSizeById(id);
    }

    @PostMapping
    public Sizes addSize(@RequestBody Sizes size) {
        return this.sizesService.addSize(size);
    }

    @DeleteMapping(path = "{sizeId}")
    public Sizes deleteSizeById(@PathVariable("sizeId") Long id) {
        return this.sizesService.deleteSizeById(id);
    }

    @PutMapping(path = "{sizeId}/change-size")
    public Sizes updateSizeById(@PathVariable("sizeId") Long id, @RequestBody Sizes sizeUpdated) {
        return sizesService.updateSizeById(id, sizeUpdated);
    }
}
