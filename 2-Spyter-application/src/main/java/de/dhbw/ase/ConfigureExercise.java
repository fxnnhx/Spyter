package de.dhbw.ase;

import de.dhbw.ase.entities.Corrector;
import de.dhbw.ase.entities.Exercise;
import de.dhbw.ase.valueObjects.SpyterText;
import de.dhbw.ase.valueObjects.TextGenerator;

import java.util.Optional;

public class ConfigureExercise {

    public Exercise configure(ConfigUIHandle uiHandle){
        Exercise.Builder exerciseBuilder = new Exercise.Builder();

        SpyterText text = uiHandle.getBaseText();
        exerciseBuilder.setText(text);

        Optional<TextGenerator> generator = uiHandle.getGenerator();
        generator.ifPresent(exerciseBuilder::setGenerator);

        Corrector corrector = uiHandle.getCorrector();
        exerciseBuilder.setCorrector(corrector);

        return exerciseBuilder.build();
    }

}
