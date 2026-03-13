package de.dhbw.ase;

import de.dhbw.ase.valueObjects.SpyterCharacter;

public interface UIHandle {
    Action getNextAction();

    void hold(SpyterCharacter character);

    void typeCorrectCharacter(SpyterCharacter character);

    void typeIncorrectCharacter(SpyterCharacter character);

    void removeChar();
}
