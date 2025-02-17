package dev.jorik.tictactoe.features.field;

import dev.jorik.tictactoe.features.Player;

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
