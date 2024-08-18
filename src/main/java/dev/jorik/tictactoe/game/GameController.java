package dev.jorik.tictactoe.game;

import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.models.Coords;
import dev.jorik.tictactoe.game.models.Game;
import dev.jorik.tictactoe.game.models.Result;

import static dev.jorik.tictactoe.field.models.Player.CIRCLE;

public class GameController {
    private final Game state;

    public GameController() {
        this.state = new Game();
    }

    public GameController(Game game) {
        this.state = game;
    }

    public Coords parseCoords(String coords) throws IllegalArgumentException {
        validateNumbers(coords);
        int y = Character.getNumericValue(coords.charAt(0)) - 1;
        int x = Character.getNumericValue(coords.charAt(1)) - 1;
        checkRange(x, y);//todo: get size of field
        return new Coords(x, y);
    }

//    public void validateInput(String coords) throws OccupiedException, IllegalArgumentException {
//        int y = Character.getNumericValue(coords.charAt(0)) - 1;
//        int x = Character.getNumericValue(coords.charAt(1)) - 1;
//        checkRange(x, y);//todo: get size of field
////        fieldController.markCell(x, y, state.player);
////        if (fieldController.isFull()) {
////            state.result = Result.DRAW;
////        } else {
////            Player winner = fieldController.getWinner();
////            if(winner != null){
////                state.result = winner == Player.CROSS ? Result.CROSS : Result.CIRCLE;
////            } else {
////                swapPlayers();
////            }
////        }
//    }

    public void changeTurn(){
        state.player = state.player == Player.CROSS
                ? CIRCLE
                : Player.CROSS;
    }

    public void setFull(){
        state.result = Result.DRAW;
    }

    public void setWinner(Player player){
        if(player == null) return;
        switch (player){
            case CROSS:
                state.result = Result.CROSS;
                break;
            case CIRCLE:
                state.result = Result.CIRCLE;
                break;
        }
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

    private void validateNumbers(String input) throws IllegalArgumentException {
        Character.getNumericValue(input.charAt(0));
        Character.getNumericValue(input.charAt(1));
    }

    private void checkRange(int x, int y) {
        if (x < 0 || 2 < x || y < 0 || 2 < y) throw new IndexOutOfBoundsException();
    }
}
