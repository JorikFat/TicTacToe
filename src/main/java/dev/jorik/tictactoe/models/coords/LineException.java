package dev.jorik.tictactoe.models.coords;

import java.io.IOException;

public class LineException extends IOException {
    LineException(String message){
        super(message);
    }

    public static class Empty extends LineException {
        public Empty() {
            super("Input is Empty. It must be length = 2");
        }
    }

    public static class TooSmall extends LineException {
        final String line;

        public TooSmall(String line) {
            super(String.format("\"%s\" is too small. It must be length = 2", line));
            this.line = line;
        }
    }

    public static class TooLarge extends LineException {
        final String line;

        public TooLarge(String line) {
            super(String.format("\"%s\" is too large. It must be length = 2", line));
            this.line = line;
        }
    }
}
