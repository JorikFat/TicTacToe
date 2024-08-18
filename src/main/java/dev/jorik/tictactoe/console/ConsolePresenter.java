package dev.jorik.tictactoe.console;

import dev.jorik.tictactoe.field.models.FieldDto;
import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.models.Result;
import dev.jorik.tictactoe.models.coords.LineException;

public class ConsolePresenter {
    private final ConsoleView console;

    public ConsolePresenter(ConsoleView console) {
        this.console = console;
    }

    public void show(FieldDto field){
        String fieldLine = String.valueOf(symbol(field.tl)) + symbol(field.tc) + symbol(field.tr) + '\n' +
                symbol(field.cl) + symbol(field.cc) + symbol(field.cr) + '\n' +
                symbol(field.bl) + symbol(field.bc) + symbol(field.br);

        console.showLine(fieldLine);
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

    public void show(LineException exception){
        console.showLine("Ввод не распознан");
    }

    private char symbol(Player player){
        if(player == null) return ' ';
        switch (player){
            case CROSS: return 'X';
            case CIRCLE: return 'O';
            default: throw new IllegalStateException();
        }
    }
}
