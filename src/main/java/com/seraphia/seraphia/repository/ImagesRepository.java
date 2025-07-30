package com.seraphia.seraphia.repository;

import com.seraphia.seraphia.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagesRepository extends JpaRepository<Images, Long> {
    Optional<Images> deleteByProductId(Long id);
}
