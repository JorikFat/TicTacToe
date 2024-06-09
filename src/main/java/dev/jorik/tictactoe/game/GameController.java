package dev.jorik.tictactoe.game;

import dev.jorik.tictactoe.game.models.*;

public class GameController {
    private final Model model;

    public GameController(){
        this.model = new Model();
    }

    public GameController(Model model){
        this.model = model;
    }

    public void markCell(String coords) throws OccupiedException, IllegalArgumentException{
        validateInput(coords);
        int y = Character.getNumericValue(coords.charAt(0)) - 1;
        int x = Character.getNumericValue(coords.charAt(1)) - 1;
        checkRange(x, y);
        checkCell(x, y);
        model.setCell(x, y, model.getPlayer());
        if (hasEmptyCells(model.getField())){
            swapPlayers();
        } else {
            model.setResult(Result.DRAW);
        }
    }

    public Field getField(){
        return model.getField();
    }

    public Player getPlayer(){
        return model.getPlayer();
    }

    public boolean isGameOver(){
        return model.getResult() != null;
    }

    public Result getResult(){
        return model.getResult();
    }

    private void swapPlayers(){
        model.setPlayer(model.getPlayer() == Player.CROSS ? Player.CIRCLE : Player.CROSS);
    }

    private void validateInput(String input) throws IllegalArgumentException {
        Character.getNumericValue(input.charAt(0));
        Character.getNumericValue(input.charAt(1));
    }

    private void checkCell(int x, int y) throws OccupiedException {
        Player player = model.getCell(x, y);
        if(player != null) throw new OccupiedException(x, y, player);
    }

    private void checkRange(int x, int y){
        if(x < 0 || 2 < x || y < 0 || 2 < y) throw new IndexOutOfBoundsException();
    }

    private boolean hasEmptyCells(Field field){
        for(Player[] row : field.getCells()){
            for(Player cell :row){
                if(cell == null) return true;
            }
        }
        return false;
    }
}
