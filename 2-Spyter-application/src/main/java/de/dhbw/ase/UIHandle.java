package de.dhbw.ase;

import de.dhbw.ase.valueObjects.SpyterCharacter;

public interface UIHandle {
    Action getNextAction();

    void hold_incorrect(SpyterCharacter character);

    void appendCorrectCharacter(SpyterCharacter character);

    void appendIncorrectCharacter(SpyterCharacter character);

    void removeChar();
}
