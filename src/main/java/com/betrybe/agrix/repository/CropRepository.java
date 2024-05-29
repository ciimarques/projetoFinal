package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for the Crop entity.
 */

public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findByFarmId(Long farmId);

  List<Crop> findAllByHarvestDateBetween(LocalDate start, LocalDate end);
}
