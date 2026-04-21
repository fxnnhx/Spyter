package de.dhbw.ase.tui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TUI {
    private String originalTerminalSettings;

    public record TerminalChar(char character) {
        public boolean isCtrlC() {
            return this.character == 3;
        }

        public boolean isBackspace() {
            return this.character == 8 || this.character == 127;
        }

    }

    public TUI() {}

    public void start() throws IOException {
        saveTerminalSettings();
        enterRawMode();
        setupTerminal();
    }

    private void saveTerminalSettings() throws IOException {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty -g < /dev/tty"});
            process.waitFor();
            originalTerminalSettings = new String(process.getInputStream().readAllBytes()).trim();
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void enterRawMode() throws IOException {
        try {
            Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty raw -echo < /dev/tty"}).waitFor();
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void setupTerminal() {
        ANSICommands.enableAlternateScreen();
        ANSICommands.clearScreen();
        ANSICommands.hideCursor();
        ANSICommands.flush();
    }

    private void resetTerminal() throws IOException {
        ANSICommands.showCursor();
        ANSICommands.disableAlternateScreen();
        ANSICommands.flush();
        restoreTerminalSettings();
    }

    private void restoreTerminalSettings() throws IOException {
        if (originalTerminalSettings != null) {
            try {
                Runtime.getRuntime().exec(new String[]{"sh", "-c", "stty " + originalTerminalSettings + " < /dev/tty"}).waitFor();
            } catch (InterruptedException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public TerminalChar readChar() throws IOException {
        int firstByte = System.in.read();
        if (firstByte == -1) {
            throw new IOException("End of input stream");
        }

        // ASCII-Case
        if ((firstByte & 0b10000000) == 0) {
            return new TerminalChar((char) firstByte);
        }

        // UTF-8
        int numBytes;
        if ((firstByte & 0b11100000) == 0b11000000) { // 2-byte
            numBytes = 2;
        } else if ((firstByte & 0b11110000) == 0b11100000) { // 3-byte
            numBytes = 3;
        } else if ((firstByte & 0b11111000) == 0b11110000) { // 4-byte (cannot fit in char)
            throw new IOException("Character too large for a single Java char");
        } else {
            throw new IOException("Invalid UTF-8 start byte: " + firstByte);
        }

        byte[] bytes = new byte[numBytes];
        bytes[0] = (byte) firstByte;
        for (int i = 1; i < numBytes; i++) {
            int next = System.in.read();
            if (next == -1) throw new IOException("Incomplete UTF-8 character");
            if ((next & 0b11000000) != 0b10000000) throw new IOException("Invalid continuation byte");
            bytes[i] = (byte) next;
        }
        String s = new String(bytes, StandardCharsets.UTF_8);
        char c = s.charAt(0);
        return new TerminalChar(c);
    }

    public void print(String text) {
        ANSICommands.print(text);
        ANSICommands.flush();
    }

    public void println(String text) {
        ANSICommands.println(text);
        ANSICommands.flush();
    }

    public void clearScreen() {
        ANSICommands.clearScreen();
        ANSICommands.flush();
    }

    public void clearLine() {
        ANSICommands.clearLine();
        ANSICommands.flush();
    }

    public void moveCursor(int row, int col) {
        ANSICommands.moveCursorTo(row, col);
        ANSICommands.flush();
    }

    public void saveCursor() {
        ANSICommands.saveCursor();
    }

    public void restoreCursor() {
        ANSICommands.restoreCursor();
    }

    public void showCursor() {
        ANSICommands.showCursor();
        ANSICommands.flush();
    }

    public void hideCursor() {
        ANSICommands.hideCursor();
        ANSICommands.flush();
    }

    public void setForegroundColor(ANSICommands.Color color) {
        ANSICommands.setForegroundColor(color);
    }

    public void setBackgroundColor(ANSICommands.BackgroundColor color) {
        ANSICommands.setBackgroundColor(color);
    }

    public void resetColors() {
        ANSICommands.reset();
    }

    public void flush() {
        ANSICommands.flush();
    }

    public void end() throws IOException {
        resetTerminal();
    }
}
