package de.dhbw.ase;

import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;

public interface RunningExerciseUI {
    Action getNextAction();

    void hold_incorrect(SpyterCharacter character);

    void appendCorrectCharacter(SpyterCharacter character);

    void appendIncorrectCharacter(SpyterCharacter character);

    void removeChar();

    void showText(SpyterText text);
}
