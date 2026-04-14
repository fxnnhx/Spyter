package de.dhbw.ase;

import de.dhbw.ase.entities.Exercise;
import de.dhbw.ase.entities.ExerciseEvaluator;

import java.io.IOException;
import java.util.Optional;

public class MainWorkflow {
    ConfigUIHandle configUI;
    RunningExerciseUI runningExerciseUI;
    ResultUI resultUI;

    public MainWorkflow(ConfigUIHandle configUI, RunningExerciseUI runningExerciseUI, ResultUI resultUI) {
        this.configUI = configUI;
        this.runningExerciseUI = runningExerciseUI;
        this.resultUI = resultUI;
    }

    public void runSingleInstance() throws IOException {
        Exercise currentExercise = new ConfigureExercise().configure(configUI);
        Optional<ExerciseEvaluator> currentEvaluator = new RunExercise(currentExercise).run(runningExerciseUI);
        currentEvaluator.ifPresentOrElse(
                evaluator -> resultUI.displayResult(new GenerateExerciseResults().getResult(evaluator)),
                resultUI::displayInterrupt);
    }
}
