package teamproject.auctionassignment.Controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import teamproject.auctionassignment.ADT.LinkedList;
import teamproject.auctionassignment.Driver;
import teamproject.auctionassignment.Models.Bid;
import teamproject.auctionassignment.Models.Bidder;
import teamproject.auctionassignment.Models.Lot;
import teamproject.auctionassignment.sort;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;



/*
====================================================================================
This is where we will keep ALL of our backend methods

It is organised in order of
Lot
Bidder
Bid
........



 */


public class MainController {

    public MainController() {

       // this.lots = new LinkedList<Lot>();
//
//        this.bids = new LinkedList<Bid>();


    }


    public void initialize() {

    }

    /**
     * This is where Lot methods will be held
     */

//    public LinkedList<Lot> lots;

    @FXML
    private TextField lotName;
    @FXML
    private TextField description;
    @FXML
    private TextField type;
    @FXML
    private TextField yearsOld;
    @FXML
    private TextField askingPrice;
    @FXML
    private ListView<String> lotListView;
    @FXML
    private Label lotListNo;


    public void addLot(Lot lot) {
        Main.lotsList.addElement(lot);
    }


    public void addLotDetails(ActionEvent event) {
        Lot lot = new Lot(lotName.getText(), description.getText(), type.getText(), Integer.parseInt(yearsOld.getText()), Integer.parseInt(askingPrice.getText()));
        //gui
        lotListView.getItems().add(lot.toString());
        lotListView2.getItems().add(lot); // this shows up in the auction house tab
        //backend
        addLot(lot);
        lotListNo.setText("There are " + numberOfLots() + " Lots");
    }

    public int numberOfLots() {
        int x = Main.lotsList.size();
        return x;
    }


    public void deleteLot(ActionEvent event) {//TODO
        if (Main.lotsList.size() != 0) { // stops nullPointerException
            for (int i = 0; i < Main.lotsList.size(); i++) {

                if (lotListView.getSelectionModel().getSelectedIndex() == i) {
                    Main.lotsList.delete(i);
                    lotListView.getItems().remove(i);
                    lotListView2.getItems().remove(i);
                    System.out.println(Main.lotsList.printList());
                    lotListNo.setText("There are " + numberOfLots() + " Lots");
                }
            }
        }
    }

    public void removeAllLots() {  // for reset facility
        Main.lotsList.deleteList();
    }


    /**
     * Bidder methods
     */


    @FXML
    private TextField bidderName;
    @FXML
    private TextField bidderPhone;
    @FXML
    private TextField bidderEmail;
    @FXML
    private TextField bidderAddress;
    @FXML
    private ListView<Bidder> biddersListView;
    @FXML
    private ChoiceBox bidderChoiceBox;
    @FXML
    private Label bidderListNo;


    public void addBidder(Bidder bidder) {
        Main.biddersList.addElement(bidder);
        showBidder();
    }

    public void addBidderDetails(ActionEvent event) {

        Bidder bidder = new Bidder(bidderName.getText(), bidderAddress.getText(), bidderPhone.getText(), bidderEmail.getText());
        biddersListView.getItems().add(bidder);

        bidderChoiceBox.getItems().add(bidder.getBidderName());

        addBidder(bidder);
        bidderListNo.setText("There are " + numberOfBidders() + " Bidders");

    }

    public void showBidder(){
        biddersListView.getItems().clear();
        bidderChoiceBox.getItems().clear();
        LinkedNode currentNode = Main.biddersList.head;
        for(int i = 0 ; i < Main.biddersList.size(); i++){
            Bidder current = (Bidder) currentNode.getContents();
            biddersListView.getItems().add(current);
            bidderChoiceBox.getItems().add(current);
            currentNode = currentNode.next;

        }


    }


    public void removeAllBidders() {

        biddersListView.getItems().clear();
        Main.biddersList.deleteList();
    }

    public int numberOfBidders() {
        int x = Main.biddersList.size();
        return x;
    }

    public void removeBidder(ActionEvent event) {
        if (Main.biddersList.size() != 0) { // stops nullPointerException
            for (int i = 0; i < Main.biddersList.size(); i++) {

                if (biddersListView.getSelectionModel().getSelectedIndex() == i) {
                    Main.biddersList.delete(i);
                    biddersListView.getItems().remove(i);
                    System.out.println(Main.biddersList.printList());
                    bidderChoiceBox.getItems().remove(i); //removes bidders from ChoiceBox in auction house
                    bidderListNo.setText("There are " + numberOfBidders() + " Bidders");
                }
            }
        }
    }

    /**
     * Bidder Info helper methods
     */

    @FXML
    public TextField setBidderName;
    @FXML
    public TextField setBidderAddress;
    @FXML
    public TextField setBidderPhoneNumber;
    @FXML
    public TextField setBidderEmail;

    private static Bidder bidder;


    public void bidderInfo(MouseEvent event) throws IOException {

        if (event.getClickCount() == 2) {
            // for (int i = 0; i < bidders.size(); i++) {

            //  if (biddersListView.getSelectionModel().getSelectedIndex() == i) {

            bidder = biddersListView.getItems().get(biddersListView.getSelectionModel().getSelectedIndex());


            setBidderName.setText(String.valueOf(bidder.getBidderName())); //displays bidder info when page is brought up
            setBidderAddress.setText(bidder.getAddress());
            setBidderEmail.setText(bidder.getEmail());
            setBidderPhoneNumber.setText(bidder.getPhone());


            Driver.stage.setScene(Driver.bidderInfo);


        }
        //  }
        // }
    }

    public void exitBidderInfo(ActionEvent event) throws IOException, ClassNotFoundException {

        Driver.stage.setScene(Driver.mainScene);

    }

    public void editBidder(ActionEvent event) {


        bidderName.setText(setBidderName.getText());
        bidderAddress.setText(setBidderAddress.getText());
        bidderPhone.setText(setBidderPhoneNumber.getText());
        bidderEmail.setText(setBidderEmail.getText());


        if (Main.biddersList.size() != 0) { // stops nullPointerException
            for (int i = 0; i < Main.biddersList.size(); i++) {

                if (biddersListView.getSelectionModel().getSelectedIndex() == i) {
                    //changes bidders info once edit button is hit
                    Main.biddersList.get(i).setBidderName(setBidderName.getText());
                    Main.biddersList.get(i).setAddress(setBidderAddress.getText());
                    Main.biddersList.get(i).setEmail(setBidderEmail.getText());
                    Main.biddersList.get(i).setPhone(setBidderPhoneNumber.getText());

                    System.out.println(Main.biddersList.printList());

                    // removes bidder and adds back the new bidderDetails

                    //works well but not with multiple bidders
                    biddersListView.getItems().remove(bidder);  // make it, so it takes the selected bidder and edits them
                    biddersListView.getItems().add(bidder);

                    //make a method to delete the bidder that has been edited
                    bidderChoiceBox.getItems().remove(bidder.getBidderName());

                    bidderChoiceBox.getItems().add(bidder.getBidderName());

                }
            }
        }
    }


    /**
     * Bid Methods
     */
    @FXML
    private TextField bidAmount;
    @FXML
    private ListView<Lot> lotListView2;  // this is used in addLotDetails the reason its here is because its in the bid class
    @FXML
    private ListView bidListView;
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();


    public LinkedList<Bid> bids;

    public void addBids(Bid bid) {
        bids.addElement(bid);

    }

    //we need to add to this method
    //we need to make it so when you select the lotListView2 it updates the askingPrice.
    //we also need to make it so you HAVE to select and item from the listview
    // if this isn't possible we can just use a choiceBox (might be a lot easier but wont look as nice)
//    public void addBiddersBid(ActionEvent event) {
//        Bid bid = new Bid(Integer.parseInt(bidAmount.getText()), date.toString(), time.toString());
//
//        for (int i = 0; i < Main.biddersList.size(); i++) {
//            if (lotListView2.getSelectionModel().getSelectedIndex() == i) {
//                bidderChoiceBox.getSelectionModel().getSelectedIndex();
//
//                bidListView.getItems().add(bidderChoiceBox.getSelectionModel().getSelectedItem() +
//                        " is bidding on " + lotListView2.getSelectionModel().getSelectedItem().getLotName() + "\n" + (bid.toString()));
//            }
//        }
//
//
//        if (bids.size() != 0) {
//            lotListView2.getSelectionModel().getSelectedItem();
//            bid.setBidAmount(Integer.parseInt(bidAmount.getText()));  //sets the bidAmount to the one written in the TextField
//        }
//    }


//    public void removeAllBids() {
//        bidList.deleteList();
//    }

    private static Bid bid;

    public void deleteBid() { //TODO

        int selectedList = bidListView.getSelectionModel().getSelectedIndex(); // make a selectedList object
        Bidder bidderSelected = (Bidder) bidderChoiceBox.getSelectionModel().getSelectedItem(); // Cast in booth to to get the a booth from the booth list

        bidListView.getItems().remove(selectedList); // remove the selected appoint from the appointment list
        bidderSelected.bidList.delete(selectedList);
    }



        //does not delete
//        try {
//            for (int i = 0; i < bidder.bidList.size(); i++) {
//
//                if (bidListView.getSelectionModel().getSelectedIndex() == i) {
//                    bidder.bidList.delete(bidListView.getSelectionModel().getSelectedIndex());
//                    bidListView.getItems().remove(biddersListView.getSelectionModel().getSelectedItem());
//
//                    System.out.println(bidder.bidList.printList());

//        try{
//            if (bidder.bidList.size() != 0) { // stops nullPointerException
//                for (int i = 0; i < bidder.bidList.size(); i++) {
//                    if (bidListView.getSelectionModel().getSelectedIndex() == i) {
//
//                        Main.biddersList.delete(i);
//                        bidListView.getItems().remove(i);
//                    }
//                }
//                //}
//            }
//
//        }catch (Exception e){
//            System.out.println("Error : " + e);
//        }

                // }
                // }






    /**
     * Searching for unsold bids
     */


    /**
     * Save and loading
     */

    @SuppressWarnings("unchecked")
    public void loadBidder(ActionEvent event) throws Exception {
        try {
            System.out.println(Main.biddersList.printList());
            //biddersListView.getItems().  ASK PETER HOW TO UPDATE THE LISTVIEW AFTER LOADING......
            XStream xstream = new XStream(new DomDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("bidders.xml"));
            Main.biddersList = (LinkedList<Bidder>) is.readObject();
            is.close();


        } catch (Exception e) {
            System.out.println("Error in reading this file : " + e);
        }
    }

    public void saveBidder(ActionEvent event) throws Exception {
        try {
            XStream xstream = new XStream(new DomDriver());

            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("bidders.xml"));

            out.writeObject(Main.biddersList);
            out.close();
        } catch (Exception e) {
            System.out.println("Error writing this file : " + e);
        }
    }

    @FXML
    private TextField bidderSearch;
    @FXML
    private ListView searchBidderList;

//    public void searchBidder(ActionEvent event) {
//
//        for (int i = 0; i < Main.lotsList.size(); i++) { // running over Patients with a for loop
//            Lot selectedLot = Main.lotsList .get(i); // Selecting a Patient for every Patient
//            if (selectedLot.bidder != null) { // Checking if the selected Patient's record isn't null (Has a record)
//                for (int j = 0; j < selectedLot.bidder.size(); j++) { // Running over all selected patients records
//                    Bidder selected = selectedLot.bidder.get(j); // Getting the vaccination record for that patient
//                    if (bidderSearch.getText().equals(selected.getBidderName())) {// Checking if the vaccination record match the batch of what was typed in the box
//                        searchBidderList.getItems().add(selected.toString()); // If so return this
//
//                        System.out.println(selectedLot.toString());
//
//
//                    }
//                }
//            }
//        }
//    }
}

