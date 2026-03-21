package de.dhbw.ase.valueObjects;

import java.util.Objects;

public class MistakeCount {
    private int count = 0;

    public MistakeCount() {
    }

    public void addMistake() {
        count++;
    }

    public void removeMistake() {
        if (count > 0) {
            count--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MistakeCount that)) return false;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(count);
    }

    public int getValue() {
        return this.count;
    }
}
