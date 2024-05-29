package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * Data Transfer Object for Person entity.
 */

public record PersonDto(Long id, String username, Role role) {

  /**
   * Converts a Person entity to a PersonDto.
   *
   * @param person The Person entity.
   * @return The PersonDto.
   */

  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
      person.getId(),
      person.getUsername(),
      person.getRole()
    );
  }

}
