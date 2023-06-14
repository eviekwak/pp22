package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader);
        //stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Inventory MainInventory = new Inventory();
        InHouse testInHousePart = new InHouse(1,"Screw", 5.00, 2, 1,5, 100);
        MainInventory.addPart(testInHousePart);
        System.out.println(MainInventory.lookupPart());
        launch();
    }
}