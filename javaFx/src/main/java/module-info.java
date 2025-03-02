module dev.jorik.tictactoe.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires dev.jorik.tictactoe.core;


    opens dev.jorik.tictactoe.javafx to javafx.fxml;
    exports dev.jorik.tictactoe.javafx;
}