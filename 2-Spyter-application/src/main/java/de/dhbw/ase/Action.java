package de.dhbw.ase;

import de.dhbw.ase.valueObjects.SpyterCharacter;

public sealed interface Action {
    public SpyterCharacter getCharacter();

    record Exit() implements Action {
        @Override
        public SpyterCharacter getCharacter() {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    record TypedChar(SpyterCharacter c) implements Action {
        @Override
        public SpyterCharacter getCharacter() {
            return c;
        }
    }

    record RemovedChar() implements Action {
        @Override
        public SpyterCharacter getCharacter() {
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}
