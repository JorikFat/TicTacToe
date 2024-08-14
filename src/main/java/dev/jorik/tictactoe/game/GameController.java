package dev.jorik.tictactoe.game;

import dev.jorik.tictactoe.field.*;
import dev.jorik.tictactoe.field.models.FieldDto;
import dev.jorik.tictactoe.field.models.FieldState;
import dev.jorik.tictactoe.field.models.OccupiedException;
import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.models.*;

public class GameController {
    private final FieldController fieldController;
    private final Game state;

    public GameController() {
        this.state = new Game();
        this.fieldController = new FieldController();
    }

    public GameController(Game game, FieldState fieldState) {
        this.state = game;
        this.fieldController = new FieldController(fieldState);
    }

    public void markCell(String coords) throws OccupiedException, IllegalArgumentException {
        validateInput(coords);
        int y = Character.getNumericValue(coords.charAt(0)) - 1;
        int x = Character.getNumericValue(coords.charAt(1)) - 1;
        checkRange(x, y);//todo: get size of field
        fieldController.markCell(x, y, state.player);
        if (fieldController.isFull()) {
            state.result = Result.DRAW;
        } else {
            swapPlayers();
        }
    }

    public FieldDto getField(){
        return fieldController.getField();
    }

    public Player getCurrentPlayer(){
        return state.player;
    }

    public boolean isOver(){
        return state.result != null;
    }

    public Result getResult(){
        return state.result;
    }

    private void swapPlayers() {
        state.player = state.player == Player.CROSS
                ? Player.CIRCLE
                : Player.CROSS;
    }

    private void validateInput(String input) throws IllegalArgumentException {
        Character.getNumericValue(input.charAt(0));
        Character.getNumericValue(input.charAt(1));
    }

    private void checkRange(int x, int y) {
        if (x < 0 || 2 < x || y < 0 || 2 < y) throw new IndexOutOfBoundsException();
    }
}
