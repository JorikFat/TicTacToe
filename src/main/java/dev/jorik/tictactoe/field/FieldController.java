package dev.jorik.tictactoe.field;

import dev.jorik.tictactoe.field.models.FieldDto;
import dev.jorik.tictactoe.field.models.FieldState;
import dev.jorik.tictactoe.field.models.OccupiedException;
import dev.jorik.tictactoe.field.models.Player;

public class FieldController {
    private final FieldState state;

    public FieldController(){
        this.state = new FieldState();
    }

    public FieldController(FieldState state){
        this.state = state;
    }

    public void markCell(int x, int y, Player player) throws OccupiedException {
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
        StringBuilder builder = new StringBuilder();
        for (Player[] row : state.cells){
            for(Player cell : row){
                builder.append(symbol(cell));
            }
            builder.append('\n');
        }
        return new FieldDto(builder.toString());
    }

    //-------------private

    private void checkCell(int x, int y) throws OccupiedException {
        Player player = state.cells[x][y];
        if(player != null) throw new OccupiedException(x, y, player);
    }

    //todo: move to presenter
    private char symbol(Player player){
        if(player == null) return ' ';
        switch (player){
            case CROSS: return 'X';
            case CIRCLE: return 'O';
            default: throw new IllegalStateException();
        }
    }

    private Player checkWin(Player[] cells){
        final Player first = cells[0];
        for(Player player :cells){
            if(player != first) return null;
        }
        return first;
    }
}
