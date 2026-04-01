package de.dhbw.ase;

import de.dhbw.ase.tui.TerminalIO;

import java.io.PrintStream;
import java.util.Scanner;

public class SimpleTerminalIO implements TerminalIO {

    PrintStream output = System.out;
    Scanner input = new Scanner(System.in);


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
        output.println(question);
        return input.nextLine();
    }

}
