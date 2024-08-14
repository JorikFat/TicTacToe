package dev.jorik.tictactoe;

import dev.jorik.tictactoe.field.models.OccupiedException;
import dev.jorik.tictactoe.game.GameController;
import dev.jorik.tictactoe.field.models.FieldState;
import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.models.Result;
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
        assertEquals(Player.CROSS, controller.getCurrentPlayer());
        assertFalse(controller.isOver());
        assertNull(controller.getResult());
        assertEquals("   \n   \n   \n", controller.getField().fieldLine);
    }

    @Test
    public void filledCreate(){
        Game model = new Game(Result.DRAW, Player.CIRCLE);
        GameController controller = new GameController(model, new FieldState());
        assertEquals(Player.CIRCLE, controller.getCurrentPlayer());
        assertTrue(controller.isOver());
        assertEquals(Result.DRAW, controller.getResult());
        assertEquals("   \n   \n   \n", controller.getField().fieldLine);
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
        assertFalse(controller.isOver());
        controller.markCell("11");
        controller.markCell("12");
        controller.markCell("13");
        controller.markCell("21");
        controller.markCell("22");
        controller.markCell("23");
        controller.markCell("31");
        controller.markCell("32");
        controller.markCell("33");
        assertTrue(controller.isOver());
    }

    @Test
    public void swapPlayers() throws  OccupiedException {
        GameController controller = new GameController();
        assertEquals(Player.CROSS, controller.getCurrentPlayer());
        controller.markCell("11");
        assertEquals(Player.CIRCLE, controller.getCurrentPlayer());
        controller.markCell("12");
        assertEquals(Player.CROSS, controller.getCurrentPlayer());
        controller.markCell("13");
        assertEquals(Player.CIRCLE, controller.getCurrentPlayer());
    }
}
