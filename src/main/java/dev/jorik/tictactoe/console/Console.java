package dev.jorik.tictactoe.console;

import java.io.PrintStream;

public class Console implements ConsoleView {
    private final PrintStream print;

    public Console(PrintStream print) {
        this.print = print;
    }

    @Override
    public void show(String message) {
        print.print(message);
    }
}
