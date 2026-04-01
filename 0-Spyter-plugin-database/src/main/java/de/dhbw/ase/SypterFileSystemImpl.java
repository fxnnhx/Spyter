package de.dhbw.ase;

import de.dhbw.ase.tui.SypterFileSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class SypterFileSystemImpl implements SypterFileSystem {

    char linebreak;

    public SypterFileSystemImpl(char linebreak) {
        this.linebreak = linebreak;
    }

    @Override
    public Optional<String> read(String filename) {
        List<String> content;
        try {
            content = Files.readAllLines(Path.of(filename));
        } catch (IOException e) {
            return Optional.empty();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String line : content) {
            stringBuilder.append(line).append(linebreak);
        }

        return Optional.of(stringBuilder.toString());
    }
}
