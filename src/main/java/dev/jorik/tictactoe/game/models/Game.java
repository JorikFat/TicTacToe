package dev.jorik.tictactoe.game.models;

import dev.jorik.tictactoe.models.player.Player;

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
