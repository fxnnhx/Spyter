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
        List<String> content_list;
        try {
            content_list = Files.readAllLines(Path.of(filename));
        } catch (IOException e) {
            return Optional.empty();
        }

        Optional<String> content = content_list.stream().reduce((s1,s2) -> s1.concat(String.valueOf(linebreak)).concat(s2));
        return content;
    }
}
