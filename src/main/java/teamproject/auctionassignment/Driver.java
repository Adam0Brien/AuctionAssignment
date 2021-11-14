package teamproject.auctionassignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("main.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 950, 600);

            //fxmlLoader1.getController();
            stage.setTitle("Auction House");
            stage.setScene(scene);

            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Adam");
    }

    public static void main(String[] args) {
        launch();
    }

}