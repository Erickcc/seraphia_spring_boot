package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Sizes;

import java.util.List;

public interface SizesService {
    List<Sizes> getAllSizes();

    Sizes getSizeById(Long id);

    Sizes addSize(Sizes size);

    Sizes deleteSizeById(Long id);

    Sizes updateSizeById(Long id, Sizes sizeUpdated);
}
