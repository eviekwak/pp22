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
      Outsourced testOutPart = new Outsourced(Model.getInstance().returnPartID(), "Outsourced", 65.00, 4, 1, 7, "dirkadirka");
      Product testProduct = new Product(78, "pro", 68.89, 4, 1, 7);
        Outsourced testOutPart2 = new Outsourced(Model.getInstance().returnPartID(), "Scrow", 65.00, 4, 1, 7, "dirkadirka");

        Inventory inventory = Model.getInstance().getInventory();
        inventory.addProduct(testProduct);
        inventory.addPart(testInHousePart);
        inventory.addPart(test2InHousePart);
        inventory.addPart(testOutPart);
        inventory.addPart(testOutPart2);
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