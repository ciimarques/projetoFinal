package com.betrybe.agrix.service.exception;

/**
 * Exception for when an entity is not found.
 */

public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }
}
