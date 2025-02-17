package dev.jorik.tictactoe.cli.console;

public class Coords {
    public final int x;
    public final int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coords)) return false;

        Coords coords = (Coords) o;

        if (x != coords.x) return false;
        return y == coords.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Coords(x=%d, y=%d)", x, y);
    }
}
