package dev.jorik.tictactoe.core.field;

import dev.jorik.tictactoe.core.Player;

public class OccupiedCellException extends Exception {
    public final int x;
    public final int y;
    public final Player player;

    public OccupiedCellException(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }
}
