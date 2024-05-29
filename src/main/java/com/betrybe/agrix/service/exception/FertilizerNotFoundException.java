package com.betrybe.agrix.service.exception;

/**
 * Exception for when a farm is not found.
 */
public class FertilizerNotFoundException extends NotFoundException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
