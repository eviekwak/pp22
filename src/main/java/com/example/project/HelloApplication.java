package com.example.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
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
//        Inventory inventory = new Inventory();
   InHouse testInHousePart = new InHouse(Model.getInstance().returnPartID(), "Screw", 5.00, 2, 1,5, 100);
      InHouse test2InHousePart = new InHouse(Model.getInstance().returnPartID(), "yep", 5.00, 2, 1,5, 100);
        Inventory inventory = Model.getInstance().getInventory();
        inventory.addPart(testInHousePart);
        inventory.addPart(test2InHousePart);
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader);
        //stage.setTitle("Hello!");

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {



        launch();
    }
}