package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropCreationDto;
import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for the Farm entity.
 */

@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  private final FarmService farmService;
  private final CropService cropService;

  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Returns all farms.
   *
   * @return A list of all farms.
   */

  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_USER') "
      +
      "or hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_ADMIN')")
  public List<FarmDto> getAllFarms() {
    return farmService.findAll().stream()
        .map(FarmDto::fromEntity)
        .toList();
  }

  /**
   * Returns a farm by its id.
   *
   * @param id The farm id.
   * @return The farm.
   * @throws FarmNotFoundException If the farm is not found.
   */

  @GetMapping("/{id}")
  public FarmDto getFarmById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(
        farmService.findById(id)
    );
  }

  /**
   * Creates a farm.
   *
   * @param farmCreationDto The farm to be created.
   * @return The created farm.
   */

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(
        farmService.create(farmCreationDto.toEntity())
    );
  }

  /**
   * Returns all crops from a farm.
   *
   * @param farmId The farm id.
   * @return A list of all crops from the farm.
   */

  @GetMapping(value = "/{farmId}/crops")
  @ResponseStatus(HttpStatus.OK)
  public List<CropDto> getAllCrops(@PathVariable Long farmId) {
    return cropService.findAll(farmId).stream()
        .map(CropDto::fromEntity)
        .toList();
  }

  /**
   * Creates a farm.
   *
   * @param cropCreationDto The crop to be created.
   * @return The created farm.
   */

  @PostMapping (value = "/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createCrop(@PathVariable Long farmId,
                            @RequestBody CropCreationDto cropCreationDto) {
    Crop crop = cropCreationDto.toEntity();
    Farm farm = new Farm();
    farm.setId(farmId);
    crop.setFarm(farm);
    Crop newCrop = cropService.create(crop);

    return CropDto.fromEntity(newCrop);
  }
}
