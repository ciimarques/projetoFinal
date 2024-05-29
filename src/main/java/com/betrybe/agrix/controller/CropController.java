package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Crop entity.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {
  private final CropService cropService;

  private final FertilizerService fertilizerService;

  public CropController(CropService cropService, FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Returns all crops.
   *
   * @return A list of all crops.
   */

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CropDto> getAllCrops() {
    return cropService.findAll().stream()
        .map(CropDto::fromEntity)
        .toList();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CropDto getCropById(@PathVariable Long id) {
    return CropDto.fromEntity(cropService.findById(id));
  }

  /**
   * Returns all crops from a farm.
   *
   * @return A list of all crops from the farm.
   */

  @GetMapping(value = "/search")
  @ResponseStatus(HttpStatus.OK)
  public List<CropDto> getCropsSearch(@RequestParam LocalDate start, @RequestParam LocalDate end) {
    return cropService.search(start, end).stream()
        .map(CropDto::fromEntity)
        .toList();
  }

  @PostMapping(value = "/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String addFertilizer(@PathVariable Long cropId, @PathVariable Long fertilizerId) {
    cropService.addFertilizer(cropId, fertilizerId);
    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Returns all fertilizers from a crop.
   *
   * @return A list of all fertilizers from the crop.
   */

  @GetMapping(value = "/{cropId}/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> getAllFertilizers(@PathVariable Long cropId) {
    return fertilizerService.findAll(cropId).stream()
        .map(FertilizerDto::fromEntity)
        .toList();
  }
}
