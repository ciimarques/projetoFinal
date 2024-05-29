package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * Data Transfer Object for Fertilizer entity.
 */

public record FertilizerDto(Long id, String name, String brand,
                            String composition) {

  /**
   * Converts a Fertilizer entity to a FertilizerDto.
   *
   * @param fertilizer The Fertilizer entity.
   * @return The FertilizerDto.
   */

  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
      fertilizer.getId(),
      fertilizer.getName(),
      fertilizer.getBrand(),
      fertilizer.getComposition()
    );
  }
}
