package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service for the Farm entity.
 */

@Service
public class FarmService {
  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public Farm findById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);
  }

  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  public Farm create(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Updates a farm.
   *
   * @param id The farm id.
   * @param farm The farm to be updated.
   * @return The updated farm.
   * @throws FarmNotFoundException If the farm is not found.
   */

  public Farm update(Long id, Farm farm) throws FarmNotFoundException {
    Farm farmFromDb = findById(id);

    farmFromDb.setName(farm.getName());
    farmFromDb.setSize(farm.getSize());

    return farmRepository.save(farmFromDb);
  }
}
