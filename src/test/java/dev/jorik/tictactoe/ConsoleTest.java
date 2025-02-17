package dev.jorik.tictactoe;

import dev.jorik.tictactoe.cli.console.Console;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ConsoleTest {
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
        Console console = new Console(printStream);

        console.show("message");

        assertEquals("message", outStream.toString());
    }

}
