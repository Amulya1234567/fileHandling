package com.ivoyant.filehandling.image.repository;

import com.ivoyant.filehandling.image.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByName(String filename);
}
