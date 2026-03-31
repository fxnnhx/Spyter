package de.dhbw.ase.tui;

import de.dhbw.ase.ResultUI;
import de.dhbw.ase.valueObjects.ExerciseResult;

public class ShowResultsTUI implements ResultUI {

    TerminalIO io;
    public ShowResultsTUI(TerminalIO io) {
        this.io = io;
    }

    @Override
    public void displayResult(ExerciseResult result) {

    }

    @Override
    public void displayInterrupt() {

    }
}
