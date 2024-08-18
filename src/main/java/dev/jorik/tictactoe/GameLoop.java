package dev.jorik.tictactoe;

import dev.jorik.tictactoe.console.ConsolePresenter;
import dev.jorik.tictactoe.field.FieldController;
import dev.jorik.tictactoe.field.models.OccupiedException;
import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.GameController;
import dev.jorik.tictactoe.game.models.Coords;

import java.util.Scanner;

public class GameLoop {
    private final GameController game;
    private final FieldController field;
    private final ConsolePresenter console;
    private final Scanner scanner;

    public GameLoop(
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
            final String input = scanner.nextLine();
            try {
                Coords coords = game.parseCoords(input);
                field.markCell(coords.x, coords.y, game.getCurrentPlayer());
                if(checkOver()) break;
                console.show(field.getField());
                game.changeTurn();
            } catch (OccupiedException e) {
                console.show("ячейка занята");
            } catch (IndexOutOfBoundsException | IllegalArgumentException runException){
                console.show("координаты не распознаны");
            }

        } while (!game.isOver());
        console.show(game.getResult());
    }

    private boolean checkOver(){
        Player winner = field.getWinner();
        if(winner != null) {
            game.setWinner(winner);
            return true;
        }
        if(field.isFull()) {
            game.setFull();
            return true;
        }
        return false;
    }
}
