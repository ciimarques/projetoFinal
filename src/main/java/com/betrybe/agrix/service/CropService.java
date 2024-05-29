package com.betrybe.agrix.service;


import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for the Crop entity.
 */


@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FarmService farmService;
  private final FertilizerRepository fertilizerRepository;

  /**
   * Constructor.
   *
   * @param cropRepository The crop repository.
   * @param farmService The farm service.
   * @param fertilizerRepository The fertilizer repository.
   */

  @Autowired
  public CropService(CropRepository cropRepository, FarmService farmService,
                     FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
    this.fertilizerRepository = fertilizerRepository;
  }

  public Crop findById(Long id)  {
    return cropRepository.findById(id)
        .orElseThrow(CropNotFoundException::new);
  }

  public List<Crop> findAll() {
    return cropRepository.findAll();
  }

  public List<Crop> findAll(Long farmId) {
    farmService.findById(farmId);
    return cropRepository.findByFarmId(farmId);
  }

  /**
   create Crop.
   */

  public Crop create(Crop crop) {
    farmService.findById(crop.getFarm().getId());
    return cropRepository.save(crop);

  }
  /**
   * Updates a farm.
   *
   * @param id The farm id.
   * @param crop The farm to be updated.
   * @return The updated farm.
   * @throws CropNotFoundException If the farm is not found.
   */

  public Crop update(Long id, Crop crop) {
    Crop cropFromDb = findById(id);

    cropFromDb.setName(crop.getName());
    cropFromDb.setPlantedArea(crop.getPlantedArea());
    cropFromDb.setPlantedDate(crop.getPlantedDate());
    cropFromDb.setharvestDate(crop.getharvestDate());

    return cropRepository.save(cropFromDb);
  }

  public List<Crop> search(LocalDate start, LocalDate end) {
    return cropRepository.findAllByHarvestDateBetween(start, end);
  }

  /**
   * Add a fertilizer to a crop.
   *
   * @param cropId The crop id.
   * @param fertilizerId The fertilizer id.
   * @throws FertilizerNotFoundException If the fertilizer is not found.
   */

  public void addFertilizer(Long cropId, Long fertilizerId) {
    Crop crop = findById(cropId);
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);
    crop.getFertilizers().add(fertilizer);

    cropRepository.save(crop);
  }
}
