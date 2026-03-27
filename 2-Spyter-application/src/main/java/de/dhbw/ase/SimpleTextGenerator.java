package de.dhbw.ase;

import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.TextGenerator;

public class SimpleTextGenerator implements TextGenerator {

    @Override
    public SpyterText generate(SpyterText text) {
        return text;
    }
}
