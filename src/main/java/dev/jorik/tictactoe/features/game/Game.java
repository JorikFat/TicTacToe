package dev.jorik.tictactoe.features.game;

import dev.jorik.tictactoe.features.Player;

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
