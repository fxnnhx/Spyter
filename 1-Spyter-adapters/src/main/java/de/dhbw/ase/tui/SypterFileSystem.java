package de.dhbw.ase.tui;

import java.util.Optional;

public interface SypterFileSystem {
    Optional<String> read(String filename);
}
