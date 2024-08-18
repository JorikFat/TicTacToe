package dev.jorik.tictactoe;

import dev.jorik.tictactoe.field.FieldController;
import dev.jorik.tictactoe.field.models.FieldState;
import dev.jorik.tictactoe.field.models.OccupiedException;
import dev.jorik.tictactoe.field.models.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FieldControllerTest {

    @Test
    public void createEmpty() {
        FieldController controller = new FieldController();
        assertEquals("   \n   \n   \n", controller.getField().fieldLine);
    }

    //todo: implement
    @Test
    public void createFilled() {
//        FieldState field = new FieldState(new Player[][]{
//                new Player[] {
//                        Player.CIRCLE, Player.CIRCLE, Player.CIRCLE,
//                        Player.CIRCLE, Player.CIRCLE, Player.CIRCLE,
//                        Player.CIRCLE, Player.CIRCLE, Player.CIRCLE,
//                }
//        });
//        FieldController controller = new FieldController(field);
        //controller not return model
//        assertEquals("OOO\nOOO\nOOO\n", controller.getField().fieldLine);
            /*
        public FieldState(){
        this.cells = new Player[][]{
                new Player[3],
                new Player[3],
                new Player[3]
        };
    }
     */
    }

    @Test
    public void markCells() throws OccupiedException {
        FieldState field = new FieldState();
        FieldController controller = new FieldController(field);

        controller.markCell(0, 0, Player.CROSS);
        assertEquals(Player.CROSS, field.cells[0][0]);

        controller.markCell(0, 1, Player.CIRCLE);
        assertEquals(Player.CIRCLE, field.cells[0][1]);
        //todo: fill full
    }

    @Test(expected = OccupiedException.class)
    public void occupiedInput() throws OccupiedException {
        FieldController controller = new FieldController();
        controller.markCell(1, 1, Player.CROSS);
        controller.markCell(1, 1, Player.CIRCLE);
    }

    @Test
    public void isFull() throws OccupiedException {
        FieldController controller = new FieldController();
        assertFalse(controller.isFull());
        controller.markCell(0, 0, Player.CROSS);
        controller.markCell(0, 1, Player.CROSS);
        controller.markCell(0, 2, Player.CROSS);
        assertFalse(controller.isFull());
        controller.markCell(1, 0, Player.CROSS);
        controller.markCell(1, 1, Player.CROSS);
        controller.markCell(1, 2, Player.CROSS);
        assertFalse(controller.isFull());
        controller.markCell(2, 0, Player.CROSS);
        controller.markCell(2, 1, Player.CROSS);
        controller.markCell(2, 2, Player.CROSS);
        assertTrue(controller.isFull());
    }

    @Test
    public void noWinner() throws OccupiedException {
        FieldController controller = new FieldController();

        controller.markCell(0, 0, Player.CROSS);

        assertNull(controller.getWinner());
    }

    @Test
    public void winRow1() throws OccupiedException {
        FieldController controller = new FieldController();
        controller.markCell(0,0, Player.CROSS);
        controller.markCell(1,0, Player.CROSS);
        controller.markCell(2,0, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winRow2() throws OccupiedException {
        FieldController controller = new FieldController();
        controller.markCell(0,1, Player.CROSS);
        controller.markCell(1,1, Player.CROSS);
        controller.markCell(2,1, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winRow3() throws OccupiedException {
        FieldController controller = new FieldController();
        controller.markCell(0,2, Player.CROSS);
        controller.markCell(1,2, Player.CROSS);
        controller.markCell(2,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winCol1() throws OccupiedException {
        FieldController controller = new FieldController();
        controller.markCell(0,0, Player.CROSS);
        controller.markCell(0,1, Player.CROSS);
        controller.markCell(0,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winCol2() throws OccupiedException {
        FieldController controller = new FieldController();
        controller.markCell(1,0, Player.CROSS);
        controller.markCell(1,1, Player.CROSS);
        controller.markCell(1,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winCol3() throws OccupiedException {
        FieldController controller = new FieldController();
        controller.markCell(2,0, Player.CROSS);
        controller.markCell(2,1, Player.CROSS);
        controller.markCell(2,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winDiag1() throws OccupiedException {
        FieldController controller = new FieldController();
        controller.markCell(0,0, Player.CROSS);
        controller.markCell(1,1, Player.CROSS);
        controller.markCell(2,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winDiag2() throws OccupiedException {
        FieldController controller = new FieldController();
        controller.markCell(2,0, Player.CROSS);
        controller.markCell(1,1, Player.CROSS);
        controller.markCell(0,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }
}
