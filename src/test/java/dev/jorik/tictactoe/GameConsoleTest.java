package dev.jorik.tictactoe;

import dev.jorik.tictactoe.field.models.FieldDto;
import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.GameConsole;
import dev.jorik.tictactoe.game.models.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)

public class GameConsoleTest {
    ByteArrayOutputStream outStream;
    PrintStream printStream;

    @Before
    public void setup() {
        outStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outStream);
    }

    @After
    public void teardown() {
    }

    @Test
    public void printMessage(){
        GameConsole console = new GameConsole(printStream);

        console.printMessage("message");

        assertEquals("message"+System.lineSeparator(), outStream.toString());
    }

    @Test
    public void printTurnCross(){
        GameConsole console = new GameConsole(printStream);

        console.printTurn(Player.CROSS);

        assertEquals("(Крестики) введите координаты ячейки: ", outStream.toString());
    }

    @Test
    public void printTurnCircle(){
        GameConsole console = new GameConsole(printStream);

        console.printTurn(Player.CIRCLE);

        assertEquals("(Нолики) введите координаты ячейки: ", outStream.toString());
    }

    @Test
    public void printFieldEmpty(){
        GameConsole console = new GameConsole(printStream);

        console.printField(new FieldDto("   \n   \n   \n"));

        assertEquals("   \n   \n   \n", outStream.toString());
    }

    @Test
    public void printFieldCross(){
        GameConsole console = new GameConsole(printStream);

        console.printField(new FieldDto("XXX\nXXX\nXXX\n"));

        assertEquals("XXX\nXXX\nXXX\n", outStream.toString());
    }

    @Test
    public void printFieldCircle(){
        GameConsole console = new GameConsole(printStream);

        console.printField(new FieldDto("OOO\nOOO\nOOO\n"));

        assertEquals("OOO\nOOO\nOOO\n", outStream.toString());
    }

    @Test
    public void printResultDraw(){
        GameConsole console = new GameConsole(printStream);

        console.printResult(Result.DRAW);

        assertEquals("Игра закончена. Победитель: Ничья", outStream.toString());
    }

    @Test
    public void printResultCross(){
        GameConsole console = new GameConsole(printStream);

        console.printResult(Result.CROSS);

        assertEquals("Игра закончена. Победитель: Крестики", outStream.toString());
    }

    @Test
    public void printResultCircle(){
        GameConsole console = new GameConsole(printStream);

        console.printResult(Result.CIRCLE);

        assertEquals("Игра закончена. Победитель: Нолики", outStream.toString());
    }
}
