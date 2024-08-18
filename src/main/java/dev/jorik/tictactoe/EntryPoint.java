package dev.jorik.tictactoe;

import dev.jorik.tictactoe.console.ConsolePresenter;
import dev.jorik.tictactoe.field.FieldController;
import dev.jorik.tictactoe.console.Console;
import dev.jorik.tictactoe.game.GameController;

import java.util.Scanner;

public interface EntryPoint {
    static void main(String[] args){
        new GameLoop(
                new GameController(),
                new FieldController(),
                new ConsolePresenter(new Console(System.out)),
                new Scanner(System.in)
        ).start();
    }
}
