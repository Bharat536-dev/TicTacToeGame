package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {

    private Label xWinsLabel = new Label("X Wins: 0");
    private Label oWinsLabel = new Label("O Wins: 0");
    private Label drawsLabel = new Label("Draws: 0");
    private Game game = new Game();
    private Button[][] buttons = new Button[3][3];

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

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
                            game.updateStats(String.valueOf(game.getCurrentPlayer()));
                            game.setGameOver(true);
                            updateStatsLabels();
                        } else if (game.isDraw()) {
                            showAlert("It's a draw!");
                            game.updateStats("Draw");
                            game.setGameOver(true);
                            updateStatsLabels();
                        } else {
                            game.switchPlayer();
                        }
                    }
                });

                buttons[i][j] = btn;
                grid.add(btn, j, i);
            }
        }

        HBox stats = new HBox(15, xWinsLabel, oWinsLabel, drawsLabel);

        Button resetButton = new Button("Play Again");
        resetButton.setOnAction(e -> resetBoard());

        Button resetStats = new Button("Reset Stats");
        resetStats.setOnAction(e -> {
            game.resetStats();
            updateStatsLabels();
        });

        HBox controls = new HBox(10, resetButton, resetStats);

        root.getChildren().addAll(grid, stats, controls);

        Scene scene = new Scene(root);
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }

    private void resetBoard() {
        game.resetBoard();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setText(" ");
    }

    private void updateStatsLabels() {
        xWinsLabel.setText("X Wins: " + game.getXWins());
        oWinsLabel.setText("O Wins: " + game.getOWins());
        drawsLabel.setText("Draws: " + game.getDraws());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.setTitle("Game Over");
        alert.setOnHidden(e -> resetBoard());
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
