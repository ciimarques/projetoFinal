package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service for Fertilizer.
 */

@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;
  private final CropService cropService;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository, CropService cropService) {
    this.fertilizerRepository = fertilizerRepository;
    this.cropService = cropService;
  }

  public Fertilizer findById(Long id)  {
    return fertilizerRepository.findById(id)
        .orElseThrow(FertilizerNotFoundException::new);
  }

  /**
   * Create a Fertilizer.
   */

  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Updates a Fertilizer.
   *
   * @param id The Fertilizer id.
   * @param fertilizer The Fertilizer to be updated.
   * @return The updated Fertilizer.
   * @throws FertilizerNotFoundException If the Fertilizer is not found.
   */

  public Fertilizer update(Long id, Fertilizer fertilizer) {
    findById(id);
    fertilizer.setId(id);
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> findAll(Long cropId) {
    Crop crop = cropService.findById(cropId);
    return crop.getFertilizers();
  }

  public List<Fertilizer> findAll() {
    return fertilizerRepository.findAll();
  }
}
