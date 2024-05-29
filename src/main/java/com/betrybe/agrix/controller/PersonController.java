package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.PersonCreationDto;
import com.betrybe.agrix.controller.dto.PersonDto;
import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Person entity.
 */
@RestController
@RequestMapping(value = "/persons")
public class PersonController {
  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Creates a person.
   *
   * @param personDto The person to be created.
   * @return The created person.
   */

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto create(@RequestBody PersonCreationDto personDto) {
    Person savedPerson = personService.create(
        personDto.toEntity()
    );

    return PersonDto.fromEntity(savedPerson);
  }

}
