package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * Data Transfer Object for Farm entity.
 */

public record FarmDto(Long id, String name, Double size) {
  /**
   * Converts a Farm entity to a FarmDto.
   *
   * @param farm Farm entity to be converted.
   * @return FarmDto object.
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
      farm.getId(),
      farm.getName(),
      farm.getSize()
    );
  }
}
