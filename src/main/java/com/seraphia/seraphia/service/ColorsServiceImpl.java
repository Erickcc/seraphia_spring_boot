package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Colors;
import com.seraphia.seraphia.repository.ColorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ColorsServiceImpl implements ColorsService {
    private final ColorsRepository colorsRepository;


    @Override
    public List<Colors> getAllColors() {
        return colorsRepository.findAll();
    }

    @Override
    public Colors getColorById(Long id) {
        return colorsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El color con id " + id + " no existe")
        );
    }

    @Override
    public Colors addColor(Colors color) {
        return colorsRepository.save(color);
    }

    @Override
    public Colors deleteColorById(Long id) {
        Colors tmp = null;
        if (!colorsRepository.existsById(id)) return tmp;
        tmp = colorsRepository.findById(id).get();
        colorsRepository.deleteById(id);
        return tmp;
    }

    @Override
    public Colors updateColorById(Long id, Colors colorUpdated) {
        Optional<Colors> optionalColor = colorsRepository.findById(id);
        if (optionalColor.isEmpty())
            throw new IllegalArgumentException("El color con id " + id + " no existe");
        Colors originalColor = optionalColor.get();
        if (colorUpdated.getColorName() != null) originalColor.setColorName(colorUpdated.getColorName());
        return colorsRepository.save(originalColor);
    }
}
