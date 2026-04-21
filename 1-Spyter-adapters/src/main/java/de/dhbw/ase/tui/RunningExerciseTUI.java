package de.dhbw.ase.tui;

import de.dhbw.ase.Action;
import de.dhbw.ase.RunningExerciseUI;
import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import de.dhbw.ase.valueObjects.SpyterText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RunningExerciseTUI implements RunningExerciseUI {
    private static final int MAX_CHARACTERS_PER_LINE = 50;
    private StringBuilder typedText;
    private String targetText;
    private List<Boolean> correctChars;

    private final CharacterDomain domain;
    private final TUI tui;
    private boolean lastWasHold = false;

    public RunningExerciseTUI(CharacterDomain domain) throws IOException {
        this.domain = domain;
        this.tui = new TUI();
    }

    @Override
    public void start() throws IOException {
        tui.start();
    }

    @Override
    public void end() throws IOException {
        tui.end();
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
        if (lastWasHold) {
            this.typedText.deleteCharAt(typedText.length() - 1);
            this.correctChars.removeLast();
        }

        this.typedText.append(character.getValue());
        this.correctChars.add(false);
        this.lastWasHold = true;

        drawTUI();
    }

    @Override
    public void appendCorrectCharacter(SpyterCharacter character) {
        if (lastWasHold) {
            this.typedText.deleteCharAt(typedText.length() - 1);
            this.correctChars.removeLast();
        }
        this.typedText.append(character.getValue());
        this.correctChars.add(true);
        this.lastWasHold = false;

        drawTUI();
    }

    @Override
    public void appendIncorrectCharacter(SpyterCharacter character) {
        if (lastWasHold) {
            this.typedText.deleteCharAt(typedText.length() - 1);
            this.correctChars.removeLast();
        }
        this.typedText.append(character.getValue());
        this.correctChars.add(false);
        this.lastWasHold = false;

        drawTUI();
    }

    @Override
    public void removeChar() {
        if (!typedText.isEmpty()) {
            this.typedText.deleteCharAt(typedText.length() - 1);
            this.correctChars.removeLast();
            this.lastWasHold = false;
        }

        drawTUI();
    }

    @Override
    public void setText(SpyterText text) {
        this.targetText = text.toString();
        this.typedText = new StringBuilder();
        this.correctChars = new ArrayList<>();
        this.lastWasHold = false;

        drawTUI();
    }

    private void drawTUI() {
        tui.clearScreen();
        tui.moveCursor(1, 1);

        tui.setForegroundColor(ANSICommands.Color.WHITE);
        int chunkStartIndex = (this.typedText.length() / MAX_CHARACTERS_PER_LINE) * MAX_CHARACTERS_PER_LINE;
        int chunkEndIndex = Math.min(chunkStartIndex + MAX_CHARACTERS_PER_LINE, this.targetText.length());
        String text = this.targetText.substring(chunkStartIndex, chunkEndIndex);
        tui.print(text);
        tui.resetColors();

        tui.moveCursor(2, 1);

        for (int i = chunkStartIndex; i < typedText.length(); i++) {
            char c = typedText.charAt(i);
            boolean isCorrect = correctChars.get(i);

            if (isCorrect) {
                tui.setForegroundColor(ANSICommands.Color.GREEN);
                tui.print(String.valueOf(c));
                tui.resetColors();
            } else {
                tui.setForegroundColor(ANSICommands.Color.RED);
                if (!lastWasHold || i < typedText.length() - 1) {
                    ANSICommands.underline();
                }
                tui.print(String.valueOf(c));
                tui.resetColors();
            }
        }

        if (lastWasHold && !typedText.isEmpty()) {
            ANSICommands.moveLeft(1);
        }

        tui.showCursor();
        tui.flush();
    }
}
