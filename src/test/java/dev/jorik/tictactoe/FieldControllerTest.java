package dev.jorik.tictactoe;

import dev.jorik.tictactoe.features.field.FieldController;
import dev.jorik.tictactoe.features.field.Field;
import dev.jorik.tictactoe.features.field.FieldDto;
import dev.jorik.tictactoe.models.field.OccupiedCellException;
import dev.jorik.tictactoe.features.Player;
import dev.jorik.tictactoe.models.field.OutOfFieldException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FieldControllerTest {

    @Test
    public void createEmpty() {
        FieldController controller = new FieldController();
        assertEqualsFields(new Player[3][3], controller.getField());
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
    public void markCells() throws OccupiedCellException, OutOfFieldException {
        Field field = new Field();
        FieldController controller = new FieldController(field);

        controller.markCell(0, 0, Player.CROSS);
        assertEquals(Player.CROSS, field.cells[0][0]);

        controller.markCell(0, 1, Player.CIRCLE);
        assertEquals(Player.CIRCLE, field.cells[0][1]);
        //todo: fill full
    }

    @Test(expected = OccupiedCellException.class)
    public void occupiedInput() throws OccupiedCellException, OutOfFieldException {
        FieldController controller = new FieldController();
        controller.markCell(1, 1, Player.CROSS);
        controller.markCell(1, 1, Player.CIRCLE);
    }

    @Test
    public void isFull() throws OccupiedCellException, OutOfFieldException {
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
    public void noWinner() throws OccupiedCellException, OutOfFieldException {
        FieldController controller = new FieldController();

        controller.markCell(0, 0, Player.CROSS);

        assertNull(controller.getWinner());
    }

    @Test
    public void winRow1() throws OccupiedCellException, OutOfFieldException {
        FieldController controller = new FieldController();
        controller.markCell(0,0, Player.CROSS);
        controller.markCell(1,0, Player.CROSS);
        controller.markCell(2,0, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winRow2() throws OccupiedCellException, OutOfFieldException {
        FieldController controller = new FieldController();
        controller.markCell(0,1, Player.CROSS);
        controller.markCell(1,1, Player.CROSS);
        controller.markCell(2,1, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winRow3() throws OccupiedCellException, OutOfFieldException {
        FieldController controller = new FieldController();
        controller.markCell(0,2, Player.CROSS);
        controller.markCell(1,2, Player.CROSS);
        controller.markCell(2,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winCol1() throws OccupiedCellException, OutOfFieldException {
        FieldController controller = new FieldController();
        controller.markCell(0,0, Player.CROSS);
        controller.markCell(0,1, Player.CROSS);
        controller.markCell(0,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winCol2() throws OccupiedCellException, OutOfFieldException {
        FieldController controller = new FieldController();
        controller.markCell(1,0, Player.CROSS);
        controller.markCell(1,1, Player.CROSS);
        controller.markCell(1,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winCol3() throws OccupiedCellException, OutOfFieldException {
        FieldController controller = new FieldController();
        controller.markCell(2,0, Player.CROSS);
        controller.markCell(2,1, Player.CROSS);
        controller.markCell(2,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winDiag1() throws OccupiedCellException, OutOfFieldException {
        FieldController controller = new FieldController();
        controller.markCell(0,0, Player.CROSS);
        controller.markCell(1,1, Player.CROSS);
        controller.markCell(2,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    @Test
    public void winDiag2() throws OccupiedCellException, OutOfFieldException {
        FieldController controller = new FieldController();
        controller.markCell(2,0, Player.CROSS);
        controller.markCell(1,1, Player.CROSS);
        controller.markCell(0,2, Player.CROSS);
        assertEquals(Player.CROSS, controller.getWinner());
    }

    private void assertEqualsFields(Player[][] expected, FieldDto actual){
        assertEquals("top left", expected[0][0], actual.tl);
        assertEquals("top center", expected[1][0], actual.tc);
        assertEquals("top right", expected[2][0], actual.tr);
        assertEquals("left center", expected[0][1], actual.cl);
        assertEquals("center center", expected[1][1], actual.cc);
        assertEquals("center right", expected[2][1], actual.cr);
        assertEquals("bottom left", expected[0][2], actual.bl);
        assertEquals("bottom center", expected[1][2], actual.bc);
        assertEquals("bottom right", expected[2][2], actual.br);
    }
}
