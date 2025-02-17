package dev.jorik.tictactoe.core.game;

import dev.jorik.tictactoe.core.Player;

import static dev.jorik.tictactoe.core.Player.CIRCLE;

public class GameController {
    private final Game state;

    public GameController() {
        this.state = new Game();
    }

    public GameController(Game game) {
        this.state = game;
    }

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
}
