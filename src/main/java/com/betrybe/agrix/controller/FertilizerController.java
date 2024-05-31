package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.service.FertilizerService;
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
 * Controller for the Fertilizer entity.
 */

@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Returns a Fertilizer by its id.
   *
   * @return The Fertilizer.
   */

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public List<FertilizerDto> getAllFertilizers() {
    return fertilizerService.findAll().stream()
        .map(FertilizerDto::fromEntity)
        .toList();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FertilizerDto getFertilizerById(@PathVariable Long id) {
    return FertilizerDto.fromEntity(fertilizerService.findById(id));
  }

  /**
   * Creates a Fertilizer.
   *
   * @param fertilizerCreationDto The Fertilizer to be created.
   * @return The created Fertilizer.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFertilizer(@RequestBody FertilizerCreationDto fertilizerCreationDto) {
    return FertilizerDto.fromEntity(fertilizerService.create(fertilizerCreationDto.toEntity()));
  }
}
