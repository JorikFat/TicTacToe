package dev.jorik.tictactoe.game.models;

public class OccupiedException extends Exception {
    public final int x;
    public final int y;
    public final Player player;

    public OccupiedException(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }
}
