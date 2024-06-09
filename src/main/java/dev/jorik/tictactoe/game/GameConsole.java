package dev.jorik.tictactoe.game;

import dev.jorik.tictactoe.game.models.Field;
import dev.jorik.tictactoe.game.models.OccupiedException;
import dev.jorik.tictactoe.game.models.Player;
import dev.jorik.tictactoe.game.models.Result;

import java.io.PrintStream;
import java.util.Scanner;

public class GameConsole {
    private final GameController controller;
    private final PrintStream print;
    private final Scanner scanner;

    public GameConsole(PrintStream print, Scanner scanner, GameController controller) {
        this.print = print;
        this.scanner = scanner;
        this.controller = controller;
    }

    public void start(){
        while (!controller.isGameOver()){
            print.printf("(%s) введите координаты ячейки: ", format(controller.getPlayer()));
            try {
                controller.markCell(scanner.nextLine());
                print.print(format(controller.getField()));
            } catch (OccupiedException occupied){
                print.println("ячейка занята");
            } catch (IndexOutOfBoundsException | IllegalArgumentException runException){
                print.println("координаты не распознаны");
            }
        }
        print.printf("Игра закончена. Победитель: %s", format(controller.getResult()));
    }

    private String format(Player player){
        switch (player){
            case CROSS: return "Крестики";
            case CIRCLE: return "Нолики";
            default: throw new IllegalStateException();
        }
    }

    private String format(Result result){
        switch (result){
            case CROSS: return "Крестики";
            case CIRCLE: return "Нолики";
            case DRAW: return "Ничья";
        }
        throw new NullPointerException();
    }

    private String format(Field field){
        StringBuilder builder = new StringBuilder();
        for (Player[] row : field.getCells()){
            for(Player cell : row){
                builder.append(symbol(cell));
            }
            builder.append('\n');
        }
        return builder.toString();
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
