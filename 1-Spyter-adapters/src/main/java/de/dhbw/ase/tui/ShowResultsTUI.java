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
        io.writeLine(String.format("Overall Time in Seconds: %d", result.durationInSeconds()));
        io.writeLine(String.format("Mistakes: %d", result.mistakes()));
        io.writeLine(String.format("Characters Per Second (cps): %.2f", result.charactersPerSecond()));
        io.writeLine(String.format("Words Per Second (wps): %.2f", result.wordsPerSecond()));
    }

    @Override
    public void displayInterrupt() {
        io.writeLine("The exercise was interrupted.");
    }
}
