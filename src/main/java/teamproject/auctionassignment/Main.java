package teamproject.auctionassignment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import teamproject.auctionassignment.ADT.LinkedList;
import teamproject.auctionassignment.Driver;
import teamproject.auctionassignment.Models.Bidder;
import teamproject.auctionassignment.Models.Lot;

public class Main {

    public static LinkedList<Lot> lotsList = new LinkedList<>();

    public static LinkedList<Bidder> biddersList = new LinkedList<>();



    public static void main(String[] args) {
        Driver.main(args);
    }

}
