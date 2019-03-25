package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Applic extends Application {

    private Stage primaryStage;
    private BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Jeu");
        initRoot();
    }

    public void initRoot(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(application.Applic.class.getResource("Root.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
           Root controller = loader.getController();
           controller.setApplic(this);
           showProd();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void showProd() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(application.Applic.class.getResource("Menu.fxml"));
            AnchorPane Addproduit = (AnchorPane) loader.load();
            root.setCenter(Addproduit);
            Menu controller = loader.getController();
            controller.setApplic(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showMorpion() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(application.Applic.class.getResource("Morpion.fxml"));
            AnchorPane Morp = (AnchorPane) loader.load();
            root.setCenter(Morp);
            Morpion controller = loader.getController();
            controller.setApplic(this);
            controller.initMorp();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
