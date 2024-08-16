package dev.jorik.tictactoe;

import dev.jorik.tictactoe.console.ConsolePresenter;
import dev.jorik.tictactoe.field.FieldController;
import dev.jorik.tictactoe.field.models.OccupiedException;
import dev.jorik.tictactoe.game.GameController;

import java.util.Scanner;

public class Mediator {
    private final GameController game;
    private final FieldController field;
    private final ConsolePresenter console;
    private final Scanner scanner;

    public Mediator(
            GameController game,
            FieldController field,
            ConsolePresenter console,
            Scanner scanner
    ) {
        this.game = game;
        this.field = field;
        this.console = console;
        this.scanner = scanner;
    }

    public void start(){
        do {
            console.show(game.getCurrentPlayer());
            try {
                final String input = scanner.nextLine();
                game.markCell(input);
                console.show(game.getField());
            } catch (OccupiedException e) {
                console.show("ячейка занята");
            } catch (IndexOutOfBoundsException | IllegalArgumentException runException){
                console.show("координаты не распознаны");
            }

        } while (!game.isOver());
        console.show(game.getResult());
    }
}
