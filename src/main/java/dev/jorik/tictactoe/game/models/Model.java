package dev.jorik.tictactoe.game.models;

public class Model {
    private Result result;
    private Player player;
    private final Field field;

    public Model(){
        this.result = null;
        this.player = Player.CROSS;
        this.field = new Field();
    }

    public Model(Result result, Player player, Field field){
        this.result = result;
        this.player = player;
        this.field = field;
    }

    public Result getResult(){
        return result;
    }

    public void setResult(Result result){
        this.result = result;
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Field getField(){
        return field;
    }

    public Player getCell(int x, int y){
        return field.getCell(x, y);
    }

    public void setCell(int x, int y, Player player){
        field.setCell(x, y, player);
    }
}
