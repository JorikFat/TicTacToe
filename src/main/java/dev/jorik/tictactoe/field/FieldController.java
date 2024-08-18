package dev.jorik.tictactoe.field;

import dev.jorik.tictactoe.models.field.FieldDto;
import dev.jorik.tictactoe.models.field.Field;
import dev.jorik.tictactoe.models.field.OccupiedCellException;
import dev.jorik.tictactoe.models.player.Player;
import dev.jorik.tictactoe.models.field.OutOfFieldException;

public class FieldController {
    private final Field state;

    public FieldController(){
        this.state = new Field();
    }

    public FieldController(Field state){
        this.state = state;
    }

    //todo: get size of field
    public void markCell(int x, int y, Player player) throws OccupiedCellException, OutOfFieldException {
        checkCell(x, y);
        state.cells[x][y] = player;
    }

    public boolean isFull(){
        for(Player[] row : state.cells){
            for(Player cell :row){
                if(cell == null) return false;
            }
        }
        return true;
    }

    public Player getWinner() {
        //rows
        Player[] row1 = new Player[] { state.cells[0][0], state.cells[0][1], state.cells[0][2] };
        Player[] row2 = new Player[] { state.cells[1][0], state.cells[1][1], state.cells[1][2] };
        Player[] row3 = new Player[] { state.cells[2][0], state.cells[2][1], state.cells[2][2] };
        //columns
        Player[] col1 = new Player[] { state.cells[0][0], state.cells[1][0], state.cells[2][0] };
        Player[] col2 = new Player[] { state.cells[0][1], state.cells[1][1], state.cells[2][1] };
        Player[] col3 = new Player[] { state.cells[0][2], state.cells[1][2], state.cells[2][2] };
        //diagonals
        Player[] diag1 = new Player[] { state.cells[0][0], state.cells[1][1], state.cells[2][2] };
        Player[] diag2 = new Player[] { state.cells[2][0], state.cells[1][1], state.cells[0][2] };

        Player[][] wins = new Player[][]{ row1, row2, row3, col1, col2, col3, diag1, diag2 };
        for(Player[] line : wins){
            Player winner = checkWin(line);
            if(winner != null) return winner;
        }
        return null;
    }

    public FieldDto getField(){
        return new FieldDto(state.cells);
    }

    //-------------private

    private void checkCell(int x, int y) throws OccupiedCellException, OutOfFieldException {
        if (x < 0 || 2 < x || y < 0 || 2 < y) throw new OutOfFieldException(x, y);
        Player player = state.cells[x][y];
        if(player != null) throw new OccupiedCellException(x, y, player);
    }

    private Player checkWin(Player[] cells){
        final Player first = cells[0];
        for(Player player :cells){
            if(player != first) return null;
        }
        return first;
    }
}
