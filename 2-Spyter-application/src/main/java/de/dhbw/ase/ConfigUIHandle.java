package de.dhbw.ase;

import de.dhbw.ase.entities.Corrector;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.TextGenerator;

import java.util.Optional;

public interface ConfigUIHandle {
    SpyterText getBaseText();

    Optional<TextGenerator> getGenerator();

    Corrector getCorrector();
}
