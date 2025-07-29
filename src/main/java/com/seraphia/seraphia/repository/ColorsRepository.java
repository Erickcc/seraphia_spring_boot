package com.seraphia.seraphia.repository;

import com.seraphia.seraphia.model.Colors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorsRepository extends JpaRepository<Colors, Long> {
    Optional<Colors> findByColorNameIs(String color);
}
