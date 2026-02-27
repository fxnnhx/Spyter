package de.dhbw.ase.valueObjects;

import de.dhbw.ase.constants.CharacterDomain;
import java.util.Optional;

public class SpyterCharacter {

  CharacterDomain domain;
  private final char value;

  private SpyterCharacter(CharacterDomain domain, char value) {
    this.domain = domain;
    this.value = value;
  }

  public boolean isDelimiter() {
    return domain.isDelimiter(this.value);
  }

  public KeyStrokeCount getKeyStrokeCount(){
    return domain.keyStrokeOfCharacter(this);
  }

  @Override
  public boolean equals(Object obj)  {
    if (!(obj instanceof SpyterCharacter other))
      return false;
    return this.value == other.value;
  }

  public static Optional<SpyterCharacter> tryFrom(CharacterDomain domain, char value) {
    if (domain.isDomainCharacter(value)) {
      return Optional.of(new SpyterCharacter(domain, value));
    } else {
      return Optional.empty();
    }
  }

  public char getValue() {
    return value;
  }
}
