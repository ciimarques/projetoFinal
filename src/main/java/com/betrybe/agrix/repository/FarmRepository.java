package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the Farm entity.
 */

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
