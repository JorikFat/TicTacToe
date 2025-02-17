package dev.jorik.tictactoe;

import dev.jorik.tictactoe.game.features.Player;
import dev.jorik.tictactoe.game.features.game.GameController;
import dev.jorik.tictactoe.game.features.game.Game;
import dev.jorik.tictactoe.game.features.game.Result;
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
    }

    @Test
    public void creteCompleted(){
        GameController controller = new GameController(new Game(Result.DRAW, Player.CIRCLE));

        assertEquals(Player.CIRCLE, controller.getCurrentPlayer());
        assertTrue(controller.isOver());
        assertEquals(Result.DRAW, controller.getResult());
    }

    //todo: catch IllegalArgumtnException
    @Test//(expected = IndexOutOfBoundsException.class)
    public void unrecognizeInput() {
        //todo: implement
//        GameController controller = new GameController();
//        controller.parseCoords("aa");
    }

    @Test//(expected = IndexOutOfBoundsException.class)
    public void wrongCoordsInput() {
        //todo: implement
//        GameController controller = new GameController();
//        controller.parseCoords("00");
    }

    @Test
    public void changeTurn() {
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
