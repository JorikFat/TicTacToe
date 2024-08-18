package dev.jorik.tictactoe;

import dev.jorik.tictactoe.console.ConsolePresenter;
import dev.jorik.tictactoe.console.input.ConsoleInput;
import dev.jorik.tictactoe.field.FieldController;
import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.GameController;
import dev.jorik.tictactoe.models.coords.Coords;

public class GameLoop {
    private final GameController game;
    private final FieldController field;
    private final ConsolePresenter console;
    private final ConsoleInput input;

    public GameLoop(
            GameController game,
            FieldController field,
            ConsolePresenter console,
            ConsoleInput input
    ) {
        this.game = game;
        this.field = field;
        this.console = console;
        this.input = input;
    }

    public void start(){
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
