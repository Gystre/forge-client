package console;

public interface ConsoleWriter {
    default void write(String msg) {
        ConsoleIO.write(msg);
    }

    default void incrementIndent() {
        ConsoleIO.incrementIndent();
    }

    default void decrementIndent() {
        ConsoleIO.decrementIndent();
    }
}
