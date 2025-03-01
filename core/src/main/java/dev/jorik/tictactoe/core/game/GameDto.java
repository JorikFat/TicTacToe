package dev.jorik.tictactoe.core.game;

import dev.jorik.tictactoe.core.Player;

public class GameDto {
    public final Result result;
    public final Player player;

    public GameDto(Result result, Player player) {
        this.result = result;
        this.player = player;
    }
}
