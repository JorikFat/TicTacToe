package dev.jorik.tictactoe.core;

import dev.jorik.tictactoe.core.field.FieldController;
import dev.jorik.tictactoe.core.field.OccupiedCellException;
import dev.jorik.tictactoe.core.game.GameController;

public class Interactor {
    private final GameController game = new GameController();
    private final FieldController field = new FieldController();
    private final GameListener listener;

    public Interactor(GameListener listener) {
        this.listener = listener;
    }

    public Player getCurrentPlayer(){
        return game.getCurrentPlayer();
    }

    public int getFieldSize(){
        return field.getFieldSize();
    }

    public void start(){
        listener.showPlayer(game.getCurrentPlayer());
    }

    public void loop(int x, int y) {
        try {
            field.markCell(x, y, game.getCurrentPlayer());
            checkOver();
            listener.updateField(field.getField());
            //todo: make last step automatically
            if(game.isOver()){
                listener.onResult(game.getResult());
            } else {
                game.changeTurn();
                listener.showPlayer(game.getCurrentPlayer());
                listener.requestNext();
            }
        } catch (OccupiedCellException e) {
            listener.onException(e);
            listener.requestNext();
        }
    }

    private void checkOver() {
        Player winner = field.getWinner();
        if (winner != null) {
            game.setWinner(winner);
        }
        if (field.isFull()) {
            game.setFull();
        }
    }
}
