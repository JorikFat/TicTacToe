package dev.jorik.tictactoe;

import dev.jorik.tictactoe.field.models.Player;
import dev.jorik.tictactoe.game.GameController;
import dev.jorik.tictactoe.game.models.Game;
import dev.jorik.tictactoe.game.models.Result;
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
//        assertEquals("   \n   \n   \n", controller.getField().fieldLine);
    }

    @Test
    public void creteCompleted(){
        Game model = new Game(Result.DRAW, Player.CIRCLE);
        GameController controller = new GameController(model);
        assertEquals(Player.CIRCLE, controller.getCurrentPlayer());
        assertTrue(controller.isOver());
        assertEquals(Result.DRAW, controller.getResult());
//        assertEquals("   \n   \n   \n", controller.getField().fieldLine);
    }

    //todo: catch IllegalArgumtnException
    @Test(expected = IndexOutOfBoundsException.class)
    public void unrecognizeInput() {
        GameController controller = new GameController();
        controller.parseCoords("aa");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void wrongCoordsInput() {
        GameController controller = new GameController();
        controller.parseCoords("00");
    }

//    @Test
//    public void gameOver() throws  OccupiedException {
//        GameController controller = new GameController();
//        sequenceInput(controller, "11", "12", "13", "21", "22", "23", "31", "32", "33");
//        assertTrue(controller.isOver());
//    }

    @Test
    public void swapPlayers() {
        GameController controller = new GameController();
        assertEquals(Player.CROSS, controller.getCurrentPlayer());
        controller.changeTurn();
        assertEquals(Player.CIRCLE, controller.getCurrentPlayer());
        controller.changeTurn();
        assertEquals(Player.CROSS, controller.getCurrentPlayer());
        controller.changeTurn();
        assertEquals(Player.CIRCLE, controller.getCurrentPlayer());
    }
}
