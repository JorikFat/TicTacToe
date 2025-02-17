package dev.jorik.tictactoe;

import dev.jorik.tictactoe.cli.console.input.ConsoleInput;
import dev.jorik.tictactoe.cli.console.Coords;
import dev.jorik.tictactoe.cli.console.LineException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ConsoleInputTest {

    @Test
    public void input() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("11".getBytes(StandardCharsets.UTF_8));
        ConsoleInput input = new ConsoleInput(new Scanner(inputStream));
        assertEquals(new Coords(0, 0), input.listen());
    }

    @Test//(expected = LineException.Empty.class)
    public void inputEmpty() throws IOException {
        //todo: implement
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        ConsoleInput input = new ConsoleInput(new Scanner(inputStream));
        assertEquals(new Coords(0, 0), input.listen());
    }

    @Test(expected = LineException.TooLarge.class)
    public void inputLarge() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("111".getBytes(StandardCharsets.UTF_8));
        ConsoleInput input = new ConsoleInput(new Scanner(inputStream));
        assertEquals(new Coords(0, 0), input.listen());
    }

    @Test(expected = LineException.TooSmall.class)
    public void inputSmall() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("1".getBytes(StandardCharsets.UTF_8));
        ConsoleInput input = new ConsoleInput(new Scanner(inputStream));
        assertEquals(new Coords(0, 0), input.listen());
    }
}
