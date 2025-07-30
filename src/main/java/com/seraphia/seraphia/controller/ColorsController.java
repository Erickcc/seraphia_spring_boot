package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.model.Colors;
import com.seraphia.seraphia.service.ColorsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping(path = "api/colors")
@AllArgsConstructor
public class ColorsController {
    private ColorsService colorsService;

    @GetMapping
    public List<Colors> getAllColors() {
        return this.colorsService.getAllColors();
    }

    @GetMapping(path = "{colorId}")
    public Colors getColorById(@PathVariable("colorId") Long id) {
        return this.colorsService.getColorById(id);
    }

    @PostMapping
    public Colors addColor(@RequestBody Colors color) {
        return this.colorsService.addColor(color);
    }

    @DeleteMapping(path = "{colorId}")
    public Colors deleteColorById(@PathVariable("colorId") Long id) {
        return this.colorsService.deleteColorById(id);
    }

    @PutMapping(path = "{colorId}/change-color")
    public Colors updateColorById(@PathVariable("colorId") Long id, @RequestBody Colors colorUpdated) {
        return colorsService.updateColorById(id, colorUpdated);
    }
}
