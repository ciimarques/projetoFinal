package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;

/**
 * Data Transfer Object for Crop entity.
 */

public record CropDto(Long id, String name, Double plantedArea,
                      Long farmId, LocalDate plantedDate, LocalDate harvestDate) {

  /**
   * Converts a Crop entity to a FarmDto.
   *
   * @param crop Crop entity to be converted.
   * @return CropDto object.
   */

  public static CropDto fromEntity(Crop crop) {

    return new CropDto(
      crop.getId(),
      crop.getName(),
      crop.getPlantedArea(),
      crop.getFarm().getId(),
      crop.getPlantedDate(),
      crop.getharvestDate()
    );
  }
}
