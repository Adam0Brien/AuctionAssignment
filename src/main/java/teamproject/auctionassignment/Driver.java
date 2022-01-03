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
    public static Scene start;
    public static Scene lotInfo;
    private MainController MainController;

    @Override
    public void start(Stage mainStage) throws IOException {
        try{



            MainController = new MainController();
            stage = mainStage;


            FXMLLoader startingScene = new FXMLLoader(Driver.class.getResource("start.fxml"));
            startingScene.setController(MainController);
            start = new Scene(startingScene.load(),600,375);



            FXMLLoader mainMenu = new FXMLLoader(Driver.class.getResource("main.fxml"));
            mainMenu.setController(MainController);
            mainScene = new Scene(mainMenu.load(), 950, 600);


            FXMLLoader bidderMenu = new FXMLLoader(Driver.class.getResource("bidderInfo.fxml"));
            bidderMenu.setController(MainController);
            bidderInfo = new Scene(bidderMenu.load(),470,320);

            FXMLLoader lotMenu = new FXMLLoader(Driver.class.getResource("lotInfo.fxml"));
            lotMenu.setController(MainController);
            lotInfo = new Scene(lotMenu.load(),640,320);

            //fxmlLoader1.getController();
            stage.setTitle("Auction House");
            stage.setScene(start);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Working!");
    }






    public static void main(String[] args) {
        launch();
    }


}