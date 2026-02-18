package de.dhbw.ase.valueObjects;

import de.dhbw.ase.constants.CharacterDomain;
import java.util.Optional;

public class SpyterCharacter {
  public char getValue() {
    return value;
  }

  private final char value;

  private SpyterCharacter(char value) {
    this.value = value;
  }

  public boolean isDelimiter() {
    return CharacterDomain.isDelimiter(this.value);
  }

  public KeyStrokeCount getKeyStrokeCount(){
    return CharacterDomain.keyStrokeOfCharacter(this);
  }

  @Override
  public boolean equals(Object obj)  {
    if (!(obj instanceof SpyterCharacter other))
      return false;
    return this.value == other.value;
  }

  public static Optional<SpyterCharacter> tryFrom(char value) {
    if (CharacterDomain.isDomainCharacter(value)) {
      return Optional.of(new SpyterCharacter(value));
    } else {
      return Optional.empty();
    }
  }
}
