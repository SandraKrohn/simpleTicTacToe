package MyApp.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TicTacToeController {
    @FXML
    public Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    public Label labelWinnerText;

    public boolean round = false;
    public boolean win = false;
    public int counter = 0;

    public void onButtonTurnClick(ActionEvent actionEvent) {
        // .getSource() bezieht sich immer auf den Button, der grad gedrueckt wurde
        Button button = (Button) actionEvent.getSource();

        if (round == false) {
            button.setText("O");
        } else {
            button.setText("X");
        }
        button.setDisable(true);
        round = !round;
        counter++;

        checkWin();

    }

    void checkWin() {
        // row check
        if (button1.getText() == button2.getText() && button2.getText() == button3.getText() && button1.isDisabled()) {
            win = true;
            setStyle(button1, button2, button3);
        }
        if (button4.getText() == button5.getText() && button5.getText() == button6.getText() && button4.isDisabled()) {
            win = true;
            setStyle(button4, button5, button6);
        }
        if (button7.getText() == button8.getText() && button8.getText() == button9.getText() && button7.isDisabled()) {
            win = true;
            setStyle(button7, button8, button9);
        }

        // col check
        if (button1.getText() == button4.getText() && button4.getText() == button7.getText() && button1.isDisabled()) {
            win = true;
            setStyle(button1, button4, button7);
        }
        if (button2.getText() == button5.getText() && button5.getText() == button8.getText() && button2.isDisabled()) {
            win = true;
            setStyle(button2, button5, button8);
        }
        if (button3.getText() == button6.getText() && button6.getText() == button9.getText() && button3.isDisabled()) {
            win = true;
            setStyle(button3, button6, button9);
        }

        // diagonal check
        if (button1.getText() == button5.getText() && button5.getText() == button9.getText() && button1.isDisabled()) {
            win = true;
            setStyle(button1, button5, button9);
        }
        if (button7.getText() == button5.getText() && button5.getText() == button3.getText() && button7.isDisabled()) {
            win = true;
            setStyle(button7, button5, button3);
        }

        // win / draw display
        if (win == true) {
            if (round == false) {
                labelWinnerText.setText("X wins");
            } else {
                labelWinnerText.setText("O wins");
            }
            // disabling all the other buttons
            Button[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
            for (Button b: buttons) {
                b.setDisable(true);
            }
        }
        else {
            if (counter == 9) {
                labelWinnerText.setText("Draw");
            }
        }
    }

    // ... = optionale Argumente
    public void setStyle(Button... buttons) {
        for (Button b: buttons) {
            b.setStyle("-fx-background-color: #80ffa0");
        }
    }

    public void onButtonResetClick() {
        round = false;
        counter = 0;
        win = false;
        Button[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (Button b: buttons) {
            b.setDisable(false);
            b.setText("");
            b.setStyle(null);
        }
    }
}
