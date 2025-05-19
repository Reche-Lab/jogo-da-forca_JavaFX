package com.rechelab.forca;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameUI game = new GameUI();
        game.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}