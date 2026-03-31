package de.dhbw.ase;

import de.dhbw.ase.tui.TerminalIO;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SimpleTerminalIO implements TerminalIO {

    PrintStream output;
    Scanner input;

    public SimpleTerminalIO(OutputStream output, InputStream input) {
        this.output = new PrintStream(output);
        this.input = new Scanner(input);
    }

    public SimpleTerminalIO() {
        new SimpleTerminalIO(System.out, System.in);
    }

    @Override
    public void writeLine(String text) {
        output.println(text);
    }

    @Override
    public String read() {
        return input.nextLine();
    }

    @Override
    public String question(String question) {
        return "";
    }

    @Override
    public void clear() {

    }
}
