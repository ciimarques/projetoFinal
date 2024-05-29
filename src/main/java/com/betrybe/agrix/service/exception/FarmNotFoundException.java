package com.betrybe.agrix.service.exception;

/**
 * Exception for when a farm is not found.
 */
public class FarmNotFoundException extends NotFoundException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
