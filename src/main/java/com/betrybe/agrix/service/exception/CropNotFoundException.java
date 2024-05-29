package com.betrybe.agrix.service.exception;

/**
 * Exception for when a crop is not found.
 */

public class CropNotFoundException extends NotFoundException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
