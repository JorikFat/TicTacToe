package dev.jorik.tictactoe.console;

import dev.jorik.tictactoe.features.field.FieldDto;
import dev.jorik.tictactoe.features.Player;
import dev.jorik.tictactoe.features.game.Result;
import dev.jorik.tictactoe.models.coords.LineException;
import dev.jorik.tictactoe.models.field.OccupiedCellException;
import dev.jorik.tictactoe.models.field.OutOfFieldException;

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

    public void show(Exception exception){
        String message;
        if(exception instanceof OccupiedCellException){
            message = "Ячейка занята";
        } else if (exception instanceof OutOfFieldException || exception instanceof LineException){
            message = "Ввод не распознан";
        } else {
            message = "Неизвестная ошибка";
        }
        console.showLine(message);
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
