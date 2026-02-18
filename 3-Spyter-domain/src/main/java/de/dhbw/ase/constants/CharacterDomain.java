package de.dhbw.ase.constants;
import java.util.HashSet;
import java.util.Set;

public final class CharacterDomain {

    public static final Set<Character> DOMAIN_CHARACTERS = buildDomainCharacters();
    public static final Set<Character> DELIMITER_CHARACTERS = buildDelimiterCharacters();

    private static Set<Character> buildDelimiterCharacters() {
        return Set.of(' ', '\n');
    }

    private static Set<Character> buildDomainCharacters() {
        Set<Character> set = new HashSet<>();

        // a-z
        for (char c = 'a'; c <= 'z'; c++) set.add(c);

        // A-Z
        for (char c = 'A'; c <= 'Z'; c++) set.add(c);

        // digits
        for (char c = '0'; c <= '9'; c++) set.add(c);

        // punctuation / symbols
        char[] symbols = {
                ',', '?', '!', '.', ':', ';', ' ', '\n'
        };

        for (char c : symbols) set.add(c);

        return set;
    }
}

