package dev.jorik.tictactoe;


import dev.jorik.tictactoe.console.Console;
import dev.jorik.tictactoe.console.ConsolePresenter;
import dev.jorik.tictactoe.console.input.ConsoleInput;
import dev.jorik.tictactoe.features.Player;
import dev.jorik.tictactoe.features.field.FieldController;
import dev.jorik.tictactoe.features.game.GameController;
import dev.jorik.tictactoe.models.coords.Coords;

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
}
