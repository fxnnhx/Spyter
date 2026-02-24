package de.dhbw.ase.valueObjects;

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

    public int getValue() {
        return this.count;
    }
}
