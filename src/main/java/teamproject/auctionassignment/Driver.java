package teamproject.auctionassignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import teamproject.auctionassignment.Controllers.MainController;

import java.io.IOException;

public class Driver extends Application {

    public static Stage stage;

    public static Scene mainScene;
    public static Scene bidderInfo;
    private MainController MainController;

    @Override
    public void start(Stage mainStage) throws IOException {
        try{
            MainController = new MainController();
            stage = mainStage;

            FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("main.fxml"));
            fxmlLoader.setController(MainController);

            mainScene = new Scene(fxmlLoader.load(), 950, 600);


            FXMLLoader fxmlLoader1 = new FXMLLoader(Driver.class.getResource("bidderInfo.fxml"));
            fxmlLoader1.setController(MainController);

            bidderInfo = new Scene(fxmlLoader1.load(),950,600);

            //fxmlLoader1.getController();
            stage.setTitle("Auction House");
            stage.setScene(mainScene);
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