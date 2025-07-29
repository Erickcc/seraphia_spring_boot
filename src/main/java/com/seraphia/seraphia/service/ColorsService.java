package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Colors;

import java.util.List;

public interface ColorsService {
    List<Colors> getAllColors();

    Colors getColorById(Long id);

    Colors addColor(Colors color);

    Colors deleteColorById(Long id);

    Colors updateColorById(Long id, Colors colorUpdated);
}
