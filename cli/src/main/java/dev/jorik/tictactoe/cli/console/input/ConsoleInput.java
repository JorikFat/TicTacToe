package dev.jorik.tictactoe.cli.console.input;

import dev.jorik.tictactoe.cli.console.Coords;
import dev.jorik.tictactoe.cli.console.LineException;

import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public Coords listen() throws LineException {
        String line = scanner.nextLine();
        validate(line);
        int y = Character.getNumericValue(line.charAt(1)) - 1;
        int x = Character.getNumericValue(line.charAt(0)) - 1;
        return new Coords(x, y);
    }

    private void validate(String line) throws LineException {
        if(line.isEmpty()) throw new LineException.Empty();
        if(line.length() < 2) throw new LineException.TooSmall(line);
        if(line.length() > 2) throw new LineException.TooLarge(line);
    }
}
