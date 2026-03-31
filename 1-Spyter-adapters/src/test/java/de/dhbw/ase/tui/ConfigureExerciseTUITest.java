package de.dhbw.ase.tui;

import de.dhbw.ase.BlockingCorrector;
import de.dhbw.ase.NonBlockingCorrector;
import de.dhbw.ase.RandomTextGenerator;
import de.dhbw.ase.SimpleTextGenerator;
import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class ConfigureExerciseTUITest {
    @Test
    void getRandomGenerator() {
        ConfigureExerciseTUI configureExerciseTUI = new ConfigureExerciseTUI(new FakeAnsweringIO("y"), new FakeDomain(), new FakeSypterFileSystem(""));
        Assertions.assertInstanceOf(RandomTextGenerator.class, configureExerciseTUI.getGenerator());
    }

    @Test
    void getSimpleGenerator() {
        ConfigureExerciseTUI configureExerciseTUI = new ConfigureExerciseTUI(new FakeAnsweringIO("n"), new FakeDomain(), new FakeSypterFileSystem(""));
        Assertions.assertInstanceOf(SimpleTextGenerator.class, configureExerciseTUI.getGenerator());
    }

    @Test
    void getBlockingCorrector() {
        ConfigureExerciseTUI configureExerciseTUI = new ConfigureExerciseTUI(new FakeAnsweringIO("1"), new FakeDomain(), new FakeSypterFileSystem(""));
        Assertions.assertInstanceOf(BlockingCorrector.class, configureExerciseTUI.getCorrector());
    }

    @Test
    void getNonBlockingCorrector() {
        ConfigureExerciseTUI configureExerciseTUI = new ConfigureExerciseTUI(new FakeAnsweringIO("2"), new FakeDomain(), new FakeSypterFileSystem(""));
        Assertions.assertInstanceOf(NonBlockingCorrector.class, configureExerciseTUI.getCorrector());
    }

    class FakeAnsweringIO implements TerminalIO {
        String answer;

        public FakeAnsweringIO(String answer) {
            this.answer = answer;
        }

        @Override
        public void writeLine(String text) {}

        @Override
        public String read() {
            return answer;
        }

        @Override
        public String question(String question) {
            return answer;
        }

        @Override
        public void clear() {}
    }

    class FakeSypterFileSystem implements SypterFileSystem {
        String content;
        public FakeSypterFileSystem(String content) {
            this.content = content;
        }

        @Override
        public Optional<String> read(String filename) {
            return Optional.of(content);
        }
    }

    class FakeDomain implements CharacterDomain {

        @Override
        public boolean isDelimiter(char character) {
            return false;
        }

        @Override
        public boolean isDomainCharacter(char character) {
            return true;
        }

        @Override
        public KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character) {
            return null;
        }
    }
}