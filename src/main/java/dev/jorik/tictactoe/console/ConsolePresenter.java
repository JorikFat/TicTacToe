package dev.jorik.tictactoe.console;

import dev.jorik.tictactoe.field.models.FieldDto;
import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.models.Result;

public class ConsolePresenter {
    private final ConsoleView console;

    public ConsolePresenter(ConsoleView console) {
        this.console = console;
    }

    public void show(FieldDto field){
        console.show(field.fieldLine);
    }

    public void show(Result result) {
        final String resultName = result == Result.DRAW
                ? "Ничья"
                : result == Result.CROSS
                    ? "Крестики"
                    : "Нолики";
        console.show(String.format("Игра закончена. Победитель: %s", resultName));
    }

    public void show(Player player){
        String playerName = player == Player.CROSS ? "Крестики" : "Нолики";
        console.show(String.format("(%s) введите координаты ячейки: ", playerName));
    }

    public void show(String message){
        console.show(message + "\n");
    }
}
