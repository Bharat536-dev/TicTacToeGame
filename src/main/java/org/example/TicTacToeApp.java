package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.Game;

public class TicTacToeApp extends Application {

    private Label xWinsLabel = new Label("X Wins: 0");
    private Label oWinsLabel = new Label("O Wins: 0");
    private Label drawsLabel = new Label("Draws: 0");
    private Game game = new Game();
    private Button[][] buttons = new Button[3][3];

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button(" ");
                btn.setPrefSize(100, 100);
                final int row = i;
                final int col = j;

                btn.setOnAction(e -> {
                    if (game.makeMove(row, col)) {
                        btn.setText(String.valueOf(game.getCurrentPlayer()));
                        if (game.checkWin()) {
                            showAlert("Player " + game.getCurrentPlayer() + " wins!");
                            game.setGameOver(true);
                        } else if (game.isDraw()) {
                            showAlert("It's a draw!");
                            game.setGameOver(true);
                        } else {
                            game.switchPlayer();
                        }
                    }
                });

                buttons[i][j] = btn;
                grid.add(btn, j, i);
            }
        }

        Scene scene = new Scene(grid, 300, 300);
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.setTitle("Game Over");
        alert.setOnHidden(e -> resetGame());
        alert.show();
    }

    private void resetGame() {
        game = new Game();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText(" ");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
