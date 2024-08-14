package dev.jorik.tictactoe.field.models;

public class FieldState {
    public final Player[][] cells;

    public FieldState(){
        this.cells = new Player[][]{
                new Player[3],
                new Player[3],
                new Player[3]
        };
    }
}
