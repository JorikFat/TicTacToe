package dev.jorik.tictactoe.game.models;

public class Field {
    private final Player[][] cells;

    public Field(){
        this.cells = new Player[][]{
                new Player[3],
                new Player[3],
                new Player[3]
        };
    }

    public Field(Player[][] cells){
        this.cells = cells;
    }

    public Player getCell(int x, int y){
        return cells[x][y];
    }

    public void setCell(int x, int y, Player player){
        cells[x][y] = player;
    }

    public Player[][] getCells(){
        return cells;
    }
}
