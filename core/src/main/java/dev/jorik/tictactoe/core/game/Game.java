package dev.jorik.tictactoe.core.game;

import dev.jorik.tictactoe.core.Player;

public class Game {
    public Result result;
    public Player player;

    public Game() {
        this.player = Player.CROSS;
    }

    public Game(Result result, Player player) {
        this.result = result;
        this.player = player;
    }
}
