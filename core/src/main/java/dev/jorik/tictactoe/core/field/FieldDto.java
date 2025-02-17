package dev.jorik.tictactoe.core.field;

import dev.jorik.tictactoe.core.Player;

public class FieldDto {
    //top row
    public final Player tl; //top left
    public final Player tc; //top center
    public final Player tr; //top right
    //center row
    public final Player cl; //center left
    public final Player cc; //center center
    public final Player cr; //center right
    //bottom row
    public final Player bl; //bottom left
    public final Player bc; //bottom center
    public final Player br; //bottom right

    public FieldDto(Player[][] matrix) {
        this.tl = matrix[0][0];
        this.tc = matrix[1][0];
        this.tr = matrix[2][0];
        this.cl = matrix[0][1];
        this.cc = matrix[1][1];
        this.cr = matrix[2][1];
        this.bl = matrix[0][2];
        this.bc = matrix[1][2];
        this.br = matrix[2][2];
    }
}
