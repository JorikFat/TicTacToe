package dev.jorik.tictactoe.game;

import dev.jorik.tictactoe.field.models.FieldDto;
import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.models.Result;

import java.io.PrintStream;

public class GameConsole {
    private final PrintStream print;

    public GameConsole(PrintStream print) {
        this.print = print;
    }

    public void printTurn(Player player) {
        String playerName = player == Player.CROSS ? "Крестики" : "Нолики";
        print.printf("(%s) введите координаты ячейки: ", playerName);
    }

    public void printField(FieldDto field) {
        print.print(field.fieldLine);
    }

    public void printResult(Result result) {
        final String resultName = result == Result.DRAW
                ? "Ничья"
                : result == Result.CROSS
                    ? "Крестики"
                    : "Нолики";
        print.printf("Игра закончена. Победитель: %s", resultName);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
