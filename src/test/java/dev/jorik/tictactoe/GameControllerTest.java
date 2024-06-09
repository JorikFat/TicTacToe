package dev.jorik.tictactoe;

import dev.jorik.tictactoe.game.GameController;
import dev.jorik.tictactoe.game.models.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GameControllerTest {

    @Test
    public void createEmpty(){
        GameController controller = new GameController();
        assertEquals(Player.CROSS, controller.getPlayer());
        assertFalse(controller.isGameOver());
        assertNull(controller.getResult());
        assertArrayEquals(controller.getField().getCells(), new Player[3][3]);
    }

    @Test
    public void filledCreate(){
        Model model = new Model(Result.DRAW, Player.CIRCLE, new Field());
        GameController controller = new GameController(model);
        assertEquals(Player.CIRCLE, controller.getPlayer());
        assertTrue(controller.isGameOver());
        assertEquals(Result.DRAW, controller.getResult());
        assertArrayEquals(controller.getField().getCells(), new Player[3][3]);
    }

    //todo: catch IllegalArgumtnException
    @Test(expected = IndexOutOfBoundsException.class)
    public void unrecognizeInput() throws OccupiedException {
        GameController controller = new GameController();
        controller.markCell("aa");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void wrongCoordsInput() throws OccupiedException {
        GameController controller = new GameController();
        controller.markCell("00");
    }
    @Test(expected = OccupiedException.class)
    public void occupiedInput() throws OccupiedException {
        GameController controller = new GameController();
        controller.markCell("11");
        controller.markCell("11");
    }

    @Test
    public void gameOver() throws  OccupiedException {
        GameController controller = new GameController();
        assertFalse(controller.isGameOver());
        controller.markCell("11");
        controller.markCell("12");
        controller.markCell("13");
        controller.markCell("21");
        controller.markCell("22");
        controller.markCell("23");
        controller.markCell("31");
        controller.markCell("32");
        controller.markCell("33");
        assertTrue(controller.isGameOver());
    }

    @Test
    public void swapPlayers() throws  OccupiedException {
        GameController controller = new GameController();
        assertEquals(Player.CROSS, controller.getPlayer());
        controller.markCell("11");
        assertEquals(Player.CIRCLE, controller.getPlayer());
        controller.markCell("12");
        assertEquals(Player.CROSS, controller.getPlayer());
        controller.markCell("13");
        assertEquals(Player.CIRCLE, controller.getPlayer());
    }
}
