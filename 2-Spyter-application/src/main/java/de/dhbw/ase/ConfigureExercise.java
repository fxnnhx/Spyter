package de.dhbw.ase;

import de.dhbw.ase.entities.Corrector;
import de.dhbw.ase.entities.Exercise;
import de.dhbw.ase.entities.TextProgress;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.TextGenerator;

import java.util.Optional;

public class ConfigureExercise {

    public Exercise configure(ConfigUIHandle uiHandle){
        Exercise.Builder builder = new Exercise.Builder();

        SpyterText text = uiHandle.getBaseText();
        builder.setText(text);

        Optional<TextGenerator> generator = uiHandle.getGenerator();
        if (generator.isPresent()){
            builder.setGenerator(generator.get());
        }

        Corrector corrector = uiHandle.getCorrector();
        builder.setCorrector(corrector);

        return builder.build();
    }

}
