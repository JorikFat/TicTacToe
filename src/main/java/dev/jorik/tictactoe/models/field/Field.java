package dev.jorik.tictactoe.models.field;

import dev.jorik.tictactoe.field.models.Player;

public class Field {
    public final Player[][] cells;

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
}
