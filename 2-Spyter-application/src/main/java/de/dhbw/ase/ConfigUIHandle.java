package de.dhbw.ase;

import de.dhbw.ase.entities.Corrector;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.TextGenerator;

public interface ConfigUIHandle {
    SpyterText getBaseText();

    TextGenerator getGenerator();

    Corrector getCorrector();
}
