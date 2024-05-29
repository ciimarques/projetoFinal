package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * DTO for creating a farm.
 */

public record FarmCreationDto(String name, Double size) {
  public Farm toEntity() {
    return new Farm(name, size);
  }
}
