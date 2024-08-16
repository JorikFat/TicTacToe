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
        sequenceInput(controller, "11", "12", "13", "21", "22", "23", "31", "32", "33");
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

    @Test
    public void win1Row() throws  OccupiedException {
        GameController controller = new GameController();

        sequenceInput(controller, "11", "22", "21", "33", "31");

        assertEquals(Result.CROSS, controller.getResult());
        assertTrue(controller.isOver());
    }

    @Test
    public void win2Row() throws  OccupiedException {
        GameController controller = new GameController();

        sequenceInput(controller, "12", "11", "22", "23", "32");

        assertEquals(Result.CROSS, controller.getResult());
        assertTrue(controller.isOver());
    }

    @Test
    public void win3Row() throws  OccupiedException {
        GameController controller = new GameController();

        sequenceInput(controller, "13", "11", "23", "22", "33");

        assertEquals(Result.CROSS, controller.getResult());
        assertTrue(controller.isOver());
    }

    @Test
    public void win1Col() throws  OccupiedException {
        GameController controller = new GameController();

        sequenceInput(controller, "11", "21", "12", "22", "13");

        assertEquals(Result.CROSS, controller.getResult());
        assertTrue(controller.isOver());
    }

    @Test
    public void win2Col() throws  OccupiedException {
        GameController controller = new GameController();

        sequenceInput(controller, "21", "11", "22", "12", "23");

        assertEquals(Result.CROSS, controller.getResult());
        assertTrue(controller.isOver());
    }

    @Test
    public void win3Col() throws  OccupiedException {
        GameController controller = new GameController();

        sequenceInput(controller, "31", "11", "32", "12", "33");

        assertEquals(Result.CROSS, controller.getResult());
        assertTrue(controller.isOver());
    }

    @Test
    public void win1Diag() throws  OccupiedException {
        GameController controller = new GameController();

        sequenceInput(controller, "11", "12", "22", "13", "33");

        assertEquals(Result.CROSS, controller.getResult());
        assertTrue(controller.isOver());
    }

    @Test
    public void win2Diag() throws  OccupiedException {
        GameController controller = new GameController();

        sequenceInput(controller, "13", "11", "22", "12", "31");

        assertEquals(Result.CROSS, controller.getResult());
        assertTrue(controller.isOver());
    }

    private void sequenceInput(GameController controller, String... coords) throws OccupiedException {
        for(String coord : coords){
            controller.markCell(coord);
        }
    }
}
