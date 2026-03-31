package de.dhbw.ase;

import de.dhbw.ase.tui.TerminalIO;

public class SimpleTerminalIO implements TerminalIO {


    @Override
    public void writeLine(String text) {

    }

    @Override
    public String read() {
        return "";
    }

    @Override
    public String question(String question) {
        return "";
    }

    @Override
    public void clear() {

    }
}
