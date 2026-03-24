package de.dhbw.ase.tui;

public class ANSICommands {
    private static final String ESC = "\033[";
    private static final String CSI = ESC;
    private static final String OSC = "\033]";
    private static final String BEL = "\007";

    public enum Color {
        BLACK(30), RED(31), GREEN(32), YELLOW(33),
        BLUE(34), MAGENTA(35), CYAN(36), WHITE(37),
        GRAY(90), BRIGHT_RED(91), BRIGHT_GREEN(92),
        BRIGHT_YELLOW(93), BRIGHT_BLUE(94), BRIGHT_MAGENTA(95),
        BRIGHT_CYAN(96), BRIGHT_WHITE(97);

        private final int code;

        Color(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public enum BackgroundColor {
        BLACK(40), RED(41), GREEN(42), YELLOW(43),
        BLUE(44), MAGENTA(45), CYAN(46), WHITE(47),
        BRIGHT_BLACK(100), BRIGHT_RED(101), BRIGHT_GREEN(102),
        BRIGHT_YELLOW(103), BRIGHT_BLUE(104), BRIGHT_MAGENTA(105),
        BRIGHT_CYAN(106), BRIGHT_WHITE(107);

        private final int code;

        BackgroundColor(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public static void moveUp(int n) {
        System.out.print(CSI + n + "A");
    }

    public static void moveDown(int n) {
        System.out.print(CSI + n + "B");
    }

    public static void moveRight(int n) {
        System.out.print(CSI + n + "C");
    }

    public static void moveLeft(int n) {
        System.out.print(CSI + n + "D");
    }

    public static void moveCursorTo(int row, int col) {
        System.out.print(CSI + row + ";" + col + "H");
    }

    public static void moveToColumn(int col) {
        System.out.print(CSI + col + "G");
    }

    public static void saveCursor() {
        System.out.print(CSI + "s");
    }

    public static void restoreCursor() {
        System.out.print(CSI + "u");
    }

    public static void hideCursor() {
        System.out.print(CSI + "?25l");
    }

    public static void showCursor() {
        System.out.print(CSI + "?25h");
    }

    public static void clearScreen() {
        System.out.print(CSI + "2J");
        System.out.print(CSI + "H");
    }

    public static void clearScreenFromCursor() {
        System.out.print(CSI + "0J");
    }

    public static void clearScreenToCursor() {
        System.out.print(CSI + "1J");
    }

    public static void clearLine() {
        System.out.print(CSI + "2K\r");
    }

    public static void clearLineFromCursor() {
        System.out.print(CSI + "0K");
    }

    public static void clearLineToCursor() {
        System.out.print(CSI + "1K");
    }

    public static void scrollUp(int n) {
        System.out.print(CSI + n + "S");
    }

    public static void scrollDown(int n) {
        System.out.print(CSI + n + "T");
    }

    public static void reset() {
        System.out.print(CSI + "0m");
    }

    public static void bold() {
        System.out.print(CSI + "1m");
    }

    public static void dim() {
        System.out.print(CSI + "2m");
    }

    public static void italic() {
        System.out.print(CSI + "3m");
    }

    public static void underline() {
        System.out.print(CSI + "4m");
    }

    public static void blink() {
        System.out.print(CSI + "5m");
    }

    public static void reverse() {
        System.out.print(CSI + "7m");
    }

    public static void hidden() {
        System.out.print(CSI + "8m");
    }

    public static void strikethrough() {
        System.out.print(CSI + "9m");
    }

    public static void setForegroundColor(Color color) {
        System.out.print(CSI + color.getCode() + "m");
    }

    public static void setBackgroundColor(BackgroundColor color) {
        System.out.print(CSI + color.getCode() + "m");
    }

    public static void setRGBForeground(int r, int g, int b) {
        System.out.print(CSI + "38;2;" + r + ";" + g + ";" + b + "m");
    }

    public static void setRGBBackground(int r, int g, int b) {
        System.out.print(CSI + "48;2;" + r + ";" + g + ";" + b + "m");
    }

    public static String colorText(String text, Color color) {
        return CSI + color.getCode() + "m" + text + CSI + "0m";
    }

    public static String colorText(String text, Color foreground, BackgroundColor background) {
        return CSI + foreground.getCode() + ";" + background.getCode() + "m" + text + CSI + "0m";
    }

    public static String boldText(String text) {
        return CSI + "1m" + text + CSI + "0m";
    }

    public static String underlineText(String text) {
        return CSI + "4m" + text + CSI + "0m";
    }

    public static String italicText(String text) {
        return CSI + "3m" + text + CSI + "0m";
    }

    public static void enableAlternateScreen() {
        System.out.print(CSI + "?1049h");
    }

    public static void disableAlternateScreen() {
        System.out.print(CSI + "?1049l");
    }

    public static void enableMouseTracking() {
        System.out.print(CSI + "?1000h");
    }

    public static void disableMouseTracking() {
        System.out.print(CSI + "?1000l");
    }

    public static void setTitle(String title) {
        System.out.print(OSC + "0;" + title + BEL);
    }

    public static void print(String text) {
        System.out.print(text);
    }

    public static void println(String text) {
        System.out.println(text);
    }

    public static void flush() {
        System.out.flush();
    }
}