package dev.jorik.tictactoe;

import dev.jorik.tictactoe.field.FieldController;
import dev.jorik.tictactoe.field.models.OccupiedException;
import dev.jorik.tictactoe.game.GameConsole;
import dev.jorik.tictactoe.game.GameController;

import java.util.Scanner;

public class Mediator {
    private final GameController game;
    private final FieldController field;
    private final GameConsole console;
    private final Scanner scanner;

    public Mediator(GameController game, FieldController field, GameConsole console, Scanner scanner) {
        this.game = game;
        this.field = field;
        this.console = console;
        this.scanner = scanner;
    }

    public void start(){
        do {
            console.printTurn(game.getCurrentPlayer());
            try {
                final String input = scanner.nextLine();
                game.markCell(input);
                console.printField(game.getField());
            } catch (OccupiedException e) {
                console.printMessage("ячейка занята");
            } catch (IndexOutOfBoundsException | IllegalArgumentException runException){
                console.printMessage("координаты не распознаны");
            }

        } while (!game.isOver());
        console.printResult(game.getResult());
    }
}
