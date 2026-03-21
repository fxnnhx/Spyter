package de.dhbw.ase;

import de.dhbw.ase.entities.Corrector;
import de.dhbw.ase.entities.Exercise;
import de.dhbw.ase.entities.TextProgress;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.TextGenerator;

public class ConfigureExercise {

    public Exercise configure(ConfigUIHandle uiHandle){
        SpyterText text = uiHandle.getBaseText();
        TextGenerator generator = uiHandle.getGenerator();
        Corrector corrector = uiHandle.getCorrector();
        return new Exercise(corrector, new TextProgress(generator.generate(text)));
    }

}
