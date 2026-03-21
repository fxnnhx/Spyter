package de.dhbw.ase;

import de.dhbw.ase.entities.Corrector;
import de.dhbw.ase.valueObjects.AdvanceType;
import de.dhbw.ase.valueObjects.MistakeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;

public class BlockingCorrector implements Corrector {

    MistakeCount mistakeCount = new MistakeCount();

    @Override
    public AdvanceType AdvanceBehavior(SpyterCharacter expected, SpyterCharacter actual) {
        if (expected.equals(actual)) {
            return AdvanceType.ADVANCE_CORRECT;
        } else {
            mistakeCount.addMistake();
            return AdvanceType.HOLD;
        }
    }

    @Override
    public MistakeCount getMistakeCount() {
        return this.mistakeCount;
    }
}
