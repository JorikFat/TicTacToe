package dev.jorik.tictactoe.cli;


import dev.jorik.tictactoe.cli.console.Console;
import dev.jorik.tictactoe.cli.console.ConsolePresenter;
import dev.jorik.tictactoe.cli.console.input.ConsoleInput;
import dev.jorik.tictactoe.core.Player;
import dev.jorik.tictactoe.core.field.FieldController;
import dev.jorik.tictactoe.core.game.GameController;
import dev.jorik.tictactoe.cli.console.Coords;

import java.util.Scanner;

public interface MainCli {
    GameController game = new GameController();
    FieldController field = new FieldController();
    ConsolePresenter console = new ConsolePresenter(new Console(System.out));
    ConsoleInput input = new ConsoleInput(new Scanner(System.in));

    static void main(String[] args) {
        do {
            console.show(game.getCurrentPlayer());
            try {
                Coords coords = input.listen();
                validate(coords);
                field.markCell(coords.x, coords.y, game.getCurrentPlayer());
                if (checkOver()) break;
                console.show(field.getField());
                game.changeTurn();
            } catch (Exception e) {
                console.show(e);
            }
        } while (!game.isOver());
        console.show(game.getResult());
        //todo: print last field state
        //todo: make last step automatically
    }


    static boolean checkOver() {
        Player winner = field.getWinner();
        if (winner != null) {
            game.setWinner(winner);
            return true;
        }
        if (field.isFull()) {
            game.setFull();
            return true;
        }
        return false;
    }

    static void validate(Coords coords) throws OutOfFieldException {
        final int size = field.getFieldSize() - 1;
        if(coords.x < 0 || coords.y < 0 || coords.x > size || coords.y > size)
            throw new OutOfFieldException(coords.x, coords.y);
    }
}
