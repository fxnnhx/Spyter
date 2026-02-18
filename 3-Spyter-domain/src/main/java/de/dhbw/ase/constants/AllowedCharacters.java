package de.dhbw.ase.constants;
import java.util.HashSet;
import java.util.Set;

public final class AllowedCharacters {

    public static final Set<Character> DOMAIN_CHARACTERS = build();

    private static Set<Character> build() {
        Set<Character> set = new HashSet<>();

        // a-z
        for (char c = 'a'; c <= 'z'; c++) set.add(c);

        // A-Z
        for (char c = 'A'; c <= 'Z'; c++) set.add(c);

        // digits (usually expected in domains/text)
        for (char c = '0'; c <= '9'; c++) set.add(c);

        // punctuation / symbols
        char[] symbols = {
                ',', '?', '!', '.', ':', ';',
                '(', ')', '[', ']', '{', '}',
                '<', '>',
                '@', '#', '$', '%', '&', '*', '+', '-', '=', '_',
                '/', '\\', '|', '"', '\'', '`', '~', '^'
        };

        for (char c : symbols) set.add(c);

        // whitespace
        set.add(' ');
        set.add('\t');

        // currency / special unicode
        set.add('€');

        return Set.copyOf(set); // immutable
    }
}

