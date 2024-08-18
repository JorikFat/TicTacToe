package dev.jorik.tictactoe.models.field;

public class OutOfFieldException extends Exception {
    public final int x;
    public final int y;

    public OutOfFieldException(int x, int y) {
        super(String.format("coordinates $d:$d is out of field 3x3 (1-3)", x, y));
        this.x = x;
        this.y = y;
    }
}
