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

    private char symbol(Player player){
        if(player == null) return ' ';
        switch (player){
            case CROSS: return 'X';
            case CIRCLE: return 'O';
            default: throw new IllegalStateException();
        }
    }
}
