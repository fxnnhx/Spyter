package de.dhbw.ase;

import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;

import java.io.IOException;

public interface RunningExerciseUI {

    void start() throws IOException;

    void end() throws IOException;

    Action getNextAction();

    void hold_incorrect(SpyterCharacter character);

    void appendCorrectCharacter(SpyterCharacter character);

    void appendIncorrectCharacter(SpyterCharacter character);

    void removeChar();

    void setText(SpyterText text);
}
