package de.dhbw.ase.valueObjects;

import de.dhbw.ase.constants.AllowedCharacters;
import java.util.Optional;

public class SpyterCharacter {
  private final char value;

  private SpyterCharacter(char value) {
    this.value = value;
  }

  public boolean isDelimiter() {
    throw new UnsupportedOperationException();
  }

  public boolean getKeyStrokeCount(){
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object obj)  {
    if (!(obj instanceof SpyterCharacter other))
      return false;
    return this.value == other.value;
  }

  public static Optional<SpyterCharacter> tryFrom(char value) {
    if (AllowedCharacters.DOMAIN_CHARACTERS.contains(value)) {
      return Optional.of(new SpyterCharacter(value));
    } else {
      return Optional.empty();
    }
  }
}
