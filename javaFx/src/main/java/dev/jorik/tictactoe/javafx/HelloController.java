package dev.jorik.tictactoe.javafx;

import dev.jorik.tictactoe.core.GameListener;
import dev.jorik.tictactoe.core.Interactor;
import dev.jorik.tictactoe.core.Player;
import dev.jorik.tictactoe.core.field.FieldDto;
import dev.jorik.tictactoe.core.game.Result;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label label;
    @FXML
    private Button tl, tc, tr;
    @FXML
    private Button cl, cc, cr;
    @FXML
    private Button bl, bc, br;
    private Interactor interactor = createInteractor();

    @FXML
    public void initialize(){
        interactor.start();
    }

    @FXML
    private void onTopLeftClick(){
        onButtonClick(0, 0);
    }

    @FXML
    private void onTopCenterClick(){
        onButtonClick(1, 0);
    }

    @FXML
    private void onTopRightClick(){
        onButtonClick(2, 0);
    }

    @FXML
    private void onCenterLeftClick(){
        onButtonClick(0, 1);
    }

    @FXML
    private void onCenterCenterClick(){
        onButtonClick(1, 1);
    }

    @FXML
    private void onCenterRightClick(){
        onButtonClick(2, 1);
    }

    @FXML
    private void onBottomLeftClick(){
        onButtonClick(0, 2);
    }

    @FXML
    private void onBottomCenterClick(){
        onButtonClick(1, 2);
    }

    @FXML
    private void onBottomRightClick(){
        onButtonClick(2, 2);
    }

    private void onButtonClick(int x, int y){
        interactor.loop(x, y);
    }

    private void showGameOver(String winner){
        ButtonType repeat = new ButtonType("Заново");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Победитель " + winner, repeat);
        alert.showAndWait();

        if (alert.getResult() == repeat) {
            interactor = createInteractor();
            interactor.start();
            updateField(new FieldDto());
            showPlayer(Player.CROSS);
        }
    }

    private String getSign(Player player){
        if(player == null) return "";
        return switch (player){
            case CIRCLE -> "O";
            case CROSS -> "X";
        };
    }

    private Interactor createInteractor(){
        return new Interactor(new GameListener() {
            @Override
            public void showPlayer(Player player) {
                HelloController.this.showPlayer(player);
            }

            @Override
            public void updateField(FieldDto field) {
                HelloController.this.updateField(field);
            }

            @Override
            public void onException(Exception exception) {
                //ignore
            }

            @Override
            public void onResult(Result result) {
                String resultName = switch (result){
                    case CROSS -> "Крестики";
                    case DRAW -> "Ничья";
                    case CIRCLE -> "Нолики";
                };
                showGameOver(resultName);
            }

            @Override
            public void requestNext() {
                //ignore
            }
        });
    }

    private void updateField(FieldDto field){
        //top
        tl.setText(getSign(field.tl));
        tc.setText(getSign(field.tc));
        tr.setText(getSign(field.tr));
        //center
        cl.setText(getSign(field.cl));
        cc.setText(getSign(field.cc));
        cr.setText(getSign(field.cr));
        //bottom
        bl.setText(getSign(field.bl));
        bc.setText(getSign(field.bc));
        br.setText(getSign(field.br));
    }

    private void showPlayer(Player player){
        String playerName = switch(player) {
            case CROSS -> "Крестики";
            case CIRCLE -> "Нолики";
        };
        label.setText(String.format("Ход игрока: %s", playerName));
    }
}