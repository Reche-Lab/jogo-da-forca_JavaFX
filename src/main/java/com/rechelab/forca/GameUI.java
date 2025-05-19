package com.rechelab.forca;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import static com.rechelab.forca.ForcaAsciiArt.DESENHOS;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.File;
import java.util.Random;

public class GameUI {
    private StackPane root;
    private TextArea forcaText;
    private GameController controller;
    private Label wordLabel;
    private Label statusLabel;
    private Label triesLabel;
    private TextField inputField;
    private Button guessButton;
    private Button restartButton;
    private Label wrongGuessesLabel;
    private ImageView lossImageView;

    public void start(Stage primaryStage) {
        controller = new GameController();

        forcaText = new TextArea();
        forcaText.setEditable(false);
        forcaText.setPrefColumnCount(8);
        forcaText.setPrefRowCount(8);
        forcaText.getStyleClass().add("forca-personalizada");

        wordLabel = new Label(controller.getMaskedWord());
        wordLabel.getStyleClass().add("masked-style");

        statusLabel = new Label("Digite uma letra:");
        triesLabel = new Label("Tentativas restantes: " + controller.getRemainingTries());

        inputField = new TextField();
        inputField.setPrefWidth(50);
        inputField.setOnAction(e -> handleGuess());

        guessButton = new Button("Tentar");
        guessButton.setOnAction(e -> handleGuess());

        restartButton = new Button("Reiniciar Jogo");
        restartButton.setVisible(false);
        restartButton.setOnAction(e -> restartGame());

        wrongGuessesLabel = new Label("Erros: ");

        HBox inputBox = new HBox(10, new Label("Letra:"), inputField, guessButton);
        inputBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(15, forcaText, wordLabel, statusLabel, triesLabel, inputBox, wrongGuessesLabel, restartButton);
        layout.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(layout);
        this.root = root;

        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setTitle("Jogo da Forca");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleGuess() {
        String input = inputField.getText().trim().toUpperCase();
        if (input.length() != 1 || !input.matches("[A-Z]")) {
            statusLabel.setText("Por favor, digite apenas uma letra.");
            return;
        }

        char letter = input.charAt(0);
        inputField.clear();

        boolean correct = controller.guessLetter(letter);
        wordLabel.setText(controller.getMaskedWord());
        triesLabel.setText("Tentativas restantes: " + controller.getRemainingTries());
        wrongGuessesLabel.setText("Erros: " + controller.getWrongGuessesAsString());
        wrongGuessesLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        atualizarForca();

        if (controller.isWon()) {
            statusLabel.setText("Parabéns! Você venceu!");
            statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            guessButton.setDisable(true);
            inputField.setDisable(true);
            restartButton.setVisible(true);
        } else if (controller.isLost()) {
            statusLabel.setText("Você perdeu! A palavra era: " + controller.getWordToGuess());
            wordLabel.setText(controller.getWordToGuess().replaceAll(".", "$0 "));
            statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            guessButton.setDisable(true);
            inputField.setDisable(true);
            restartButton.setVisible(true);
            showRandomLossImage();
        } else {
            if (correct) {
                statusLabel.setText("Acertou!");
                statusLabel.setStyle("-fx-text-fill: green;");
            } else {
                statusLabel.setText("Errou!");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        }
    }

    private void atualizarForca() {
        int erros = controller.getErrors();
        erros = Math.min(erros, DESENHOS.length - 1);
        forcaText.setText(DESENHOS[erros]);
    }

    private void restartGame() {
        controller = new GameController();
        wordLabel.setText(controller.getMaskedWord());
        triesLabel.setText("Tentativas restantes: " + controller.getRemainingTries());
        statusLabel.setText("Digite uma letra:");
        atualizarForca();

        inputField.clear();
        inputField.setDisable(false);
        guessButton.setDisable(false);
        wrongGuessesLabel.setText("");
        restartButton.setVisible(false);
    }

    private void showRandomLossImage() {
        File folder = new File("src/main/resources/images/lost");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"));

        if (files == null || files.length == 0) return;

        File selected = files[new Random().nextInt(files.length)];
        Image image = new Image(selected.toURI().toString());

        if (lossImageView == null) {
            lossImageView = new ImageView();
            lossImageView.fitWidthProperty().bind(root.widthProperty());
            lossImageView.fitHeightProperty().bind(root.heightProperty());
            lossImageView.setPreserveRatio(false);
        }

        lossImageView.setImage(image);
        root.getChildren().add(lossImageView); // root deve ser um atributo da classe para estar acessível

        FadeTransition blink = new FadeTransition(Duration.seconds(0.5), lossImageView);
        blink.setFromValue(1.0);
        blink.setToValue(0.0);
        blink.setCycleCount(7);
        blink.setAutoReverse(true);
        blink.setOnFinished(e -> {
            root.getChildren().remove(lossImageView);
        });
        blink.play();
    }
}
