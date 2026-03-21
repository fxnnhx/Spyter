package de.dhbw.ase.entities;

import de.dhbw.ase.valueObjects.AdvanceType;
import de.dhbw.ase.valueObjects.MistakeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;

public interface Corrector {
    AdvanceType AdvanceBehavior(SpyterCharacter expected, SpyterCharacter actual);

    MistakeCount getMistakeCount();
}
