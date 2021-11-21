package teamproject.auctionassignment.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import teamproject.auctionassignment.ADT.LinkedList;
import teamproject.auctionassignment.Models.Lot;

public class MainController {

        public MainController() {

                this.lots = new LinkedList<>();
        }


        public LinkedList<Lot> lots;
            @FXML private TextField lotName;
            @FXML private TextField description;
            @FXML private TextField type;
            @FXML private TextField yearsOld;
            @FXML private TextField askingPrice;
            @FXML private Button addLot;
            @FXML private ListView<String> lotListView;
            @FXML private Label lotListNo;

            public void addLot(Lot lot){
                lots.addElement(lot);
            }
            public void numberOfLots(){
                lots.listLength();
            }

            public void addLotDetails(ActionEvent event) {
            //gui
            lotListView.getItems().addAll( " "+lotName.getText() +" "+ description.getText() + " " + type.getText() + " " + yearsOld.getText() + " " + askingPrice.getText());
            //backend
            addLot(new Lot(lotName.getText(),description.getText(),type.getText(),yearsOld.anchorProperty().getValue(), askingPrice.anchorProperty().getValue()));


            System.out.println(lotName.getText()+"\n"+description.getText()+"\n"+type.getText()+"\n"+yearsOld.getText()+"\n"+askingPrice.getText());
//
    }


//abcdefg hahahahahha
}

