package com.seraphia.seraphia.repository;

import com.seraphia.seraphia.model.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SizesRepository extends JpaRepository<Sizes, Long> {
    Optional<Sizes> findBySizeNameIs(String size);
}
