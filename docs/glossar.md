 # Glossar

| Fachbegriff | Englisch | Beschreibung |
| ------------ | -------- | ------------- |
| Wörter pro Minute/Sekunde | words per minute/second | durchschnittliche Anzahl der final korrekt getippten Wörter pro Minute/Sekunde. Abkürzung: wpm / wps |
| Wörter | words | Zeichenkette, abgetrennt durch Trennzeichen – wobei Trennzeichen nicht als Teil des Wortes betrachtet werden |
| Trennzeichen | delimiter | Leerzeichen und expliziter Zeilenumbruch (ein Tabulator wird in ein einzelnes Leerzeichen übersetzt) |
| Zeichen pro Minute/Sekunde | characters per minute/second | durchschnittliche Anzahl der final korrekt getippten Zeichen pro Minute/Sekunde. Abkürzung: cpm / cps |
| Zeichen | character | ein UTF-8-codiertes Zeichen |
| Tastenanschlag | keystroke | Betätigung einer Taste auf der Tastatur zur Eingabe eines Zeichens |
| Tastenanschläge pro Minute/Sekunde | keystrokes per minute/second | Anzahl der final korrekten Tastenanschläge pro Minute/Sekunde. Abkürzung: kpm / kps |
| korrektes Wort/Zeichen/Tastenanschlag | correct word/character/keystroke | alle getippten Zeichen, Wörter oder Tastenanschläge, die den vorgegebenen Zeichen, Wörtern oder Tastenanschlägen entsprechen |
| finales Wort/Zeichen/Tastenanschlag | final word/character/keystroke | jedes Wort, Zeichen oder jeder Tastenanschlag, das bzw. der nach Abschluss der Übung besteht |
| Übung | exercise | die Eingabe eines bestimmten (generierten) Textes mit anschließender Auswertung |
| Text | text | eine Folge von Zeichen |
| generierter Text | generated text | ein Text, der auf Basis einer Menge angegebener Zeichen generiert wird |
| Gastgeberperson | host | die Person, die eine Sitzung erstellt |
| Sitzung | session | mindestens zwei teilnehmende Personen (einschließlich der Gastgeberperson) führen dieselbe Übung durch und erhalten nach Abschluss aller Teilnehmenden die Statistiken aller |
| teilnehmende Person | participant | eine Person (ggf. die Gastgeberperson), die an einer Sitzung teilnimmt |

# Value Objects
- Zeichen
    - bool isDelimiter()
    - KeyStrokeCount keyStrokeCount()
- Wort (erst für Auswertung bestimmt)
    - CharCount charCount()
- Text
    - Array<Zeichen>
- CharCount
- KeyStrokeCount
- WordsPerMinute/WordsPerSecond
- CharactersPerMinute/CharactersPerSecond
- KeyStrokePerMinute/KeyStrokePerSecond
- CharProgressType
    - CORRECT
    - INCORRECT
- AdvanceType
    - HOLD
    - ADVANCE_CORRECT
    - ADVANCE_INCORRECT
- TextGeneratorConfig

# Entities/Aggregates
- InputText
    - attributes:
        - List<Zeichen> inputCharacters
    - methods:
        - void read(char)
        - Text toText()
- TextProgress
    - attributes:
        - Text expectedText
        - InputText inputText
    - methods:
        - ProgressType isNextChar(char)
        - void advance(char)
        - void delete_one_char()
        - bool isFinished()
- Corrector (Interface)
    - AdvanceType take(char)
    - void delete_one_char()
    - bool isFinished()
    - ExerciseEvaluator toExerciseEvaluator()
- BlockingCorrector impl Corrector
    ```java
    if isNextChar(x) {
        advance(H)
        return ADVANCE_CORRECT;
    } else {
        return HOLD;
    }
    ```
- NonBlockingCorrector impl Corrector
    ```java
    if isNextChar(x) {
        advance(H)
        return ADVANCE_CORRECT;
    } else {
        advance(H)
        return ADVANCE_INCORRECT;
    }
    ```
- Exercise
    - attributes
        - Corrector
        - ExerciseUI (Interface)
    - methods
        - void take(char)
        - void delete_one_char()
        - bool isFinished()
- ExerciseEvaluator
    - attributes:
        - TextProgress
    - methods:
        - fromTextProgress(TextProgress)
- ExerciseRecord (JavaRecord)
    - tbd. after ExerciseEvaluator implementation
- TextGenerator (Interface)
    - from_raw_string(String)
    - from_generator_config(TextGeneratorConfig)

