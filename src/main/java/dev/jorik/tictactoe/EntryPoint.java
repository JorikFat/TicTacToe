package dev.jorik.tictactoe;

import dev.jorik.tictactoe.field.FieldController;
import dev.jorik.tictactoe.game.GameConsole;
import dev.jorik.tictactoe.game.GameController;

import java.util.Scanner;

public interface EntryPoint {
    static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController();
        GameConsole console = new GameConsole(System.out);
        new Mediator(controller, new FieldController(), console, scanner).start();
    }
}
