package de.dhbw.ase;

import de.dhbw.ase.valueObjects.SpyterCharacter;

public sealed interface Action {
    record Exit() implements Action {}

    record TypedChar(SpyterCharacter c) implements Action {}

    record RemovedChar() implements Action {}
}
