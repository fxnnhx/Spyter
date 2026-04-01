package de.dhbw.ase.tui;

import de.dhbw.ase.Action;
import de.dhbw.ase.RunningExerciseUI;
import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;

import java.io.IOException;
import java.util.Optional;

public class RunningExerciseTUI implements RunningExerciseUI, AutoCloseable {
    private final CharacterDomain domain;
    private final TUI tui;
    private int currentPosition = 0;
    private boolean cursorBehindChar = false;

    public RunningExerciseTUI(CharacterDomain domain) throws IOException {
        this.domain = domain;
        this.tui = new TUI();
    }

    @Override
    public Action getNextAction() {
        try {
            while (true) {
                TUI.TerminalChar nextChar = tui.readChar();

                if (nextChar.isCtrlC()) {
                    return new Action.Exit();
                }

                if (nextChar.isBackspace()) {
                    return new Action.RemovedChar();
                }

                Optional<SpyterCharacter> c = SpyterCharacter.tryFrom(domain, nextChar.character());
                if (c.isPresent()) {
                    return new Action.TypedChar(c.get());
                }
            }
        } catch (IOException e) {
            return new Action.Exit();
        }
    }

    @Override
    public void hold_incorrect(SpyterCharacter character) {
        tui.setForegroundColor(ANSICommands.Color.RED);
        tui.print(String.valueOf(character.getValue()));
        tui.resetColors();
        // Overwrite with next action
        ANSICommands.moveLeft(1);
        this.cursorBehindChar = true;
        tui.flush();
    }

    @Override
    public void appendCorrectCharacter(SpyterCharacter character) {
        tui.setForegroundColor(ANSICommands.Color.GREEN);
        tui.print(String.valueOf(character.getValue()));
        tui.resetColors();
        this.cursorBehindChar = false;
        currentPosition++;
    }

    @Override
    public void appendIncorrectCharacter(SpyterCharacter character) {
        tui.setForegroundColor(ANSICommands.Color.RED);
        ANSICommands.underline();
        tui.print(String.valueOf(character.getValue()));
        tui.resetColors();
        this.cursorBehindChar = false;
        currentPosition++;
    }

    @Override
    public void removeChar() {
        if (currentPosition > 0) {
            int nbOfCharsToDelete = (this.cursorBehindChar)? 2: 1;
            ANSICommands.moveLeft(1);
            tui.print(" ".repeat(nbOfCharsToDelete));
            ANSICommands.moveLeft(nbOfCharsToDelete);
            tui.flush();
            currentPosition--;
        }
    }

    @Override
    public void showText(SpyterText text) {
        currentPosition = 0;
        tui.clearScreen();
        tui.moveCursor(1, 1);

        tui.setForegroundColor(ANSICommands.Color.WHITE);
        tui.print(text.toString());
        tui.resetColors();

        tui.moveCursor(2, 1);
        tui.showCursor();
        tui.flush();
    }

    @Override
    public void close() throws IOException {
            tui.close();
    }
}