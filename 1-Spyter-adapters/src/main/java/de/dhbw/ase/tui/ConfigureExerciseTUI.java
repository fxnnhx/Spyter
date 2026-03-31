package de.dhbw.ase.tui;

import de.dhbw.ase.*;
import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.entities.Corrector;
import de.dhbw.ase.valueObjects.CharacterCorrectionType;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.TextGenerator;

import java.io.File;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

public class ConfigureExerciseTUI implements ConfigUIHandle {

    TerminalIO ioHandle;
    CharacterDomain domain;
    FileSystem fileSystemHandle;

    public ConfigureExerciseTUI(TerminalIO ioHandle, CharacterDomain domain, FileSystem fileSystemHandle) {
        this.ioHandle = ioHandle;
        this.domain = domain;
        this.fileSystemHandle = fileSystemHandle;
    }

    @Override
    public SpyterText getBaseText() {
        String textBaseFilname = ioHandle.question("Filename for Text Base: ");
        String textBaseStr = fileSystemHandle.read(textBaseFilname);
        return new SpyterText(domain, textBaseStr);
    }

    @Override
    public TextGenerator getGenerator() {
        String answere = "";
        do {
            answere = ioHandle.question("Should the Text in your exercise be randomly generated? (y/n)");
        } while (!answere.equals("y") && !answere.equals("n"));

        return switch (answere) {
            case "y" -> new RandomTextGenerator();
            case "n" -> new SimpleTextGenerator();
            default -> throw new IllegalStateException("Unexpected value: " + answere);
        };
    }

    @Override
    public Corrector getCorrector() {
        ioHandle.writeLine("Which corrector do you want to use?");
        ioHandle.writeLine("1: Blocking: Prevents further input after a wrong character is typed.");
        ioHandle.writeLine("2: Not-Blocking: allows further input even after a wrong character is typed.");
        String answere = "";
        do {
            answere = ioHandle.question("(1/2)");
        } while (!answere.equals("1") && !answere.equals("2"));
        return switch (answere) {
            case "1" -> new BlockingCorrector();
            case "2" -> new NonBlockingCorrector();
            default -> throw new IllegalStateException("Unexpected value: " + answere);
        };
    }
}
