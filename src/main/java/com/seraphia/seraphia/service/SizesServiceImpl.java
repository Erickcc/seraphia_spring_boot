package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Sizes;
import com.seraphia.seraphia.repository.SizesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SizesServiceImpl implements SizesService {
    private final SizesRepository sizesRepository;

    @Override
    public List<Sizes> getAllSizes() {
        return sizesRepository.findAll();
    }

    @Override
    public Sizes getSizeById(Long id) {
        return sizesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El tamaño con id " + id + " no existe")
        );
    }

    @Override
    public Sizes addSize(Sizes size) {
        return sizesRepository.save(size);
    }

    @Override
    public Sizes deleteSizeById(Long id) {
        Sizes tmp = null;
        if (!sizesRepository.existsById(id)) return tmp;
        tmp = sizesRepository.findById(id).get();
        sizesRepository.deleteById(id);
        return tmp;
    }

    @Override
    public Sizes updateSizeById(Long id, Sizes sizeUpdated) {
        Optional<Sizes> optionalSize = sizesRepository.findById(id);
        if (optionalSize.isEmpty())
            throw new IllegalArgumentException("El tamaño con el id " + id + " no existe");
        Sizes originalSize = optionalSize.get();
        if (sizeUpdated.getSizeName() != null)
            originalSize.setSizeName(sizeUpdated.getSizeName());
        return sizesRepository.save(originalSize);
    }
}
