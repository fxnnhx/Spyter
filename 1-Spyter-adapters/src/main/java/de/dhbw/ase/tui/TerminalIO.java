package de.dhbw.ase.tui;

import de.dhbw.ase.valueObjects.SpyterText;

public interface TerminalIO {
    void writeLine(String text);
    String read();
    String question(String question);
    void clear();
}
