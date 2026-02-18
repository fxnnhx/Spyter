package de.dhbw.ase.constants;
import de.dhbw.ase.valueObjects.KeyStrokeCount;
import de.dhbw.ase.valueObjects.SpyterCharacter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class CharacterDomain {
    private static final Map<Character, KeyStrokeCount> characterToKeyStrokeCount = buildCharacterToKeyStrokeCount();
    private static final Set<Character> delimiters = Set.of(' ', '\n');

    private static Map<Character, KeyStrokeCount> buildCharacterToKeyStrokeCount() {
        Map<Character, KeyStrokeCount> map = new HashMap<>();
        final KeyStrokeCount ONE_KEY_STROKE = new KeyStrokeCount(1);
        final KeyStrokeCount TWO_KEY_STROKE = new KeyStrokeCount(2);

        for (char c = 'a'; c <= 'z'; c++) map.put(c, ONE_KEY_STROKE);

        for (char c = 'A'; c <= 'Z'; c++) map.put(c, TWO_KEY_STROKE);

        for (char c = '0'; c <= '9'; c++) map.put(c, ONE_KEY_STROKE);

        map.putAll(Map.of(
                ',', ONE_KEY_STROKE,
                '?', TWO_KEY_STROKE,
                '!', TWO_KEY_STROKE,
                '.', ONE_KEY_STROKE,
                ':', TWO_KEY_STROKE,
                ';', TWO_KEY_STROKE,
                ' ', ONE_KEY_STROKE,
                '\n', ONE_KEY_STROKE
        ));
        return map;
    }

    public static boolean isDelimiter(char character) {
        return delimiters.contains(character);
    }

    public static boolean isDomainCharacter(char character) {
        return characterToKeyStrokeCount.containsKey(character);
    }

    public static KeyStrokeCount keyStrokeOfCharacter(SpyterCharacter character) {
            return characterToKeyStrokeCount.get(character.getValue());
    }
}

