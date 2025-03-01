package dev.jorik.tictactoe.cli;

import dev.jorik.tictactoe.cli.console.Console;
import dev.jorik.tictactoe.cli.console.ConsolePresenter;
import dev.jorik.tictactoe.cli.console.Coords;
import dev.jorik.tictactoe.cli.console.input.ConsoleInput;
import dev.jorik.tictactoe.core.GameListener;
import dev.jorik.tictactoe.core.Interactor;
import dev.jorik.tictactoe.core.Player;
import dev.jorik.tictactoe.core.field.FieldDto;
import dev.jorik.tictactoe.core.game.Result;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public interface MainCli3 {
    ConsolePresenter console = new ConsolePresenter(new Console(System.out));
    ConsoleInput input = new ConsoleInput(new Scanner(System.in));
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Interactor interactor = new Interactor(new GameListener() {
        @Override
        public void showPlayer(Player player) {
            console.show(player);
        }

        @Override
        public void onResult(Result result) {
            console.show(result);
            executor.shutdown();
        }

        @Override
        public void onException(Exception exception) {
            console.show(exception);
        }

        @Override
        public void updateField(FieldDto field) {
            console.show(field);
        }

        @Override
        public void requestNext() {
            nextMove();
        }
    });


    static void main(String[] args) {
        interactor.start();
        nextMove();
    }

    static void nextMove(){
        try {
            Coords coords = input.listen();
            validate(coords);
            interactor.loop(coords.x, coords.y);
        } catch (Exception exception){
            console.show(exception);
            nextMove();
        }
    }

    static void validate(Coords coords) throws OutOfFieldException {
        final int size = interactor.getFieldSize() - 1;
        if(coords.x < 0 || coords.y < 0 || coords.x > size || coords.y > size)
            throw new OutOfFieldException(coords.x, coords.y);
    }
}
