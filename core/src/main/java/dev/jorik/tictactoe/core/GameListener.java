package dev.jorik.tictactoe.core;

import dev.jorik.tictactoe.core.field.FieldDto;
import dev.jorik.tictactoe.core.game.Result;

public interface GameListener {
    void showPlayer(Player player);
    void updateField(FieldDto field);
    void onException(Exception exception);
    void onResult(Result result);
    void requestNext();
}