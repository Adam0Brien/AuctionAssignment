package teamproject.auctionassignment.Controllers;

//save/load

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.security.AnyTypePermission;

//javaFX
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

//importing  other classes
import teamproject.auctionassignment.ADT.LinkedList;
import teamproject.auctionassignment.ADT.LinkedNode;
import teamproject.auctionassignment.Driver;
import teamproject.auctionassignment.Main;
import teamproject.auctionassignment.Models.Bid;
import teamproject.auctionassignment.Models.Bidder;
import teamproject.auctionassignment.Models.CompletedBids;
import teamproject.auctionassignment.Models.Lot;

//used for processing bid times
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Authors: Adam O'Brien, Eric butler, Kyle Kennedy
 */
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

    private static Bidder bidder;
    private static Lot lot;

    @FXML
    public TextField setBidderName;
    @FXML
    public TextField setBidderAddress;
    @FXML
    public TextField setBidderPhoneNumber;
    @FXML
    public TextField setBidderEmail;
    public TextField setLotName;
    public TextField setLotDescription;
    public TextField setLotType;
    public TextField setLotYear;
    public TextField setLotPrice;
    @FXML
    ListView completedBidsListView;
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
    private ListView<Lot> lotListView;
    @FXML
    private Label lotListNo;
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
    @FXML
    private TextField bidAmount;
    @FXML
    private ListView<Lot> lotListView2;  // this is used in addLotDetails the reason its here is because its in the bid class
    @FXML
    private ListView soldBidsSearch;
    @FXML
    private ListView bidListView;
    private String date;
    private String time;
    @FXML
    private TextField soldSearchBar;

    public LinkedList<Lot> initialize() {

        for (int i = 0; i < Main.lotsList.size(); i++) {
            for (int j = 0; j < Main.lotsList.get(i).completedBids.size(); i++) {

                completedBidsListView.getItems().add(Main.lotsList.get(i).completedBids.get(j));

            }
        }
        return null;
    }

    public void exitProgram(ActionEvent event) {
        System.exit(1);
    }

    public void loadMainFromPreviousSave(ActionEvent event) throws Exception {
        load(event);
        loadMain(event);
    }

    /**
     * Save and loading
     */

    @SuppressWarnings("unchecked")
    public void load(ActionEvent event) throws Exception {
        try {
            System.out.println(Main.biddersList.printList());
            XStream xstream = new XStream(new DomDriver());
            xstream.addPermission(AnyTypePermission.ANY);

            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Bidders.xml"));

            Main.biddersList = (LinkedList<Bidder>) is.readObject();

            is = xstream.createObjectInputStream(new FileReader("Lots.xml"));

            Main.lotsList = (LinkedList<Lot>) is.readObject();

//			is = xstream.createObjectInputStream(new FileReader("CompletedBids.xml"));
//
//			Main.completedBids = (LinkedList<CompletedBids>) is.readObject();

            is.close();

            loadBiddersListView();//loads the listviews
            loadLotsListView();
            loadCBListView();
            lotListNo.setText("There are " + numberOfLots() + " Lots");
            bidderListNo.setText("There are " + numberOfBidders() + " Bidders");
            //loadCBListView();

        } catch (Exception e) {
            System.out.println("Error in reading this file : " + e);
        }
    }

    public void loadBiddersListView() {
        biddersListView.getItems().clear();//
        bidderChoiceBox.getItems().clear();//
        LinkedNode currentNode = Main.biddersList.head;

        while (currentNode != null) {
            biddersListView.getItems().add((Bidder) currentNode.getContents());//populates listview
            bidderChoiceBox.getItems().add(currentNode.getContents());//populates choicebox
            currentNode = currentNode.next;
        }

    }

    public void loadLotsListView() {
        lotListView.getItems().clear();
        lotListView2.getItems().clear();
        LinkedNode currentNode = Main.lotsList.head;

        while (currentNode != null) {
            lotListView.getItems().add((Lot) currentNode.getContents());//populates both listviews
            lotListView2.getItems().add((Lot) currentNode.getContents());
            currentNode = currentNode.next;
        }
    }

    public void loadCBListView() {
        completedBidsListView.getItems().clear();

        LinkedNode currentNode = Main.completedBids.head;

        while (currentNode != null) {
            completedBidsListView.getItems().add(String.valueOf(currentNode.getContents()));//populates both listviews
            currentNode = currentNode.next;
        }
    }

    public void loadMain(ActionEvent event) {
        Driver.stage.setScene(Driver.mainScene);
    }

    public void editLot(ActionEvent event) {
        try {

            lotName.setText(setLotName.getText());
            description.setText(setLotDescription.getText());
            type.setText(setLotType.getText());
            yearsOld.setText(setLotYear.getText());
            askingPrice.setText(setLotPrice.getText());

            if (Main.lotsList.size() != 0) { // stops nullPointerException
                for (int i = 0; i < Main.lotsList.size(); i++) {

                    if (lotListView.getSelectionModel().getSelectedIndex() == i) {
                        //changes lot info once edit button is hit
                        Main.lotsList.get(i).setLotName(setLotName.getText());
                        Main.lotsList.get(i).setDescription(setLotDescription.getText());
                        Main.lotsList.get(i).setType(setLotType.getText());
                        Main.lotsList.get(i).setYearsOld(Integer.parseInt(setLotYear.getText()));
                        Main.lotsList.get(i).setAskingPrice(Float.parseFloat(setLotPrice.getText()));

                        System.out.println(Main.lotsList.printList());

                        // removes lot and adds back the new lotDetails

                        //works well but not with multiple lots
                        lotListView.getItems().remove(lot);  // make it, so it takes the selected lot and edits it
                        lotListView.getItems().add(lot);

                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error on Edit Lot: " + e);
        }
    }

    public void lotInfo(MouseEvent event) throws IOException {
        try {
            if (event.getClickCount() == 2) {

                lot = lotListView.getItems().get(lotListView.getSelectionModel().getSelectedIndex());

                setLotName.setText(lot.getLotName());
                setLotDescription.setText(lot.getDescription());
                setLotType.setText(lot.getType());
                setLotYear.setText(lot.getLotName());
                setLotPrice.setText(String.valueOf(lot.getAskingPrice()));

                Driver.stage.setScene(Driver.lotInfo);

            }

        } catch (Exception e) {
            System.out.println("Error on Lot Info: " + e);
        }
    }

    public void addLotDetails(ActionEvent event) {
        Lot lot = new Lot(lotName.getText(), description.getText(), type.getText(), Integer.parseInt(yearsOld.getText()), Integer.parseInt(askingPrice.getText()));
        //gui
        lotListView.getItems().add(lot);
        lotListView2.getItems().add(lot); // this shows up in the auction house tab
        //backend
        addLot(lot);
        lotListNo.setText("There are " + numberOfLots() + " Lots");
    }

    public void addLot(Lot lot) {
        Main.lotsList.addElement(lot);
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

    public int numberOfLots() {
        int x = Main.lotsList.size();
        return x;
    }

    public void removeAllLots() {  // for reset facility
        Main.lotsList.deleteList();
    }

    public void addBidderDetails(ActionEvent event) {

        Bidder bidder = new Bidder(bidderName.getText(), bidderAddress.getText(), bidderPhone.getText(), bidderEmail.getText());
        biddersListView.getItems().add(bidder);

        bidderChoiceBox.getItems().add(bidder.getBidderName());

        addBidder(bidder);
        bidderListNo.setText("There are " + numberOfBidders() + " Bidders");

    }

    public void addBidder(Bidder bidder) {
        Main.biddersList.addElement(bidder);
        showBidder();
    }

    public void showBidder() {
        biddersListView.getItems().clear();
        bidderChoiceBox.getItems().clear();
        LinkedNode currentNode = Main.biddersList.head;
        for (int i = 0; i < Main.biddersList.size(); i++) {
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

    public int numberOfBidders() {
        int x = Main.biddersList.size();
        return x;
    }

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

    public void addBids() {
        try {
            for (int i = 0; i < Main.biddersList.size(); i++) {

                if (lotListView2.getSelectionModel().getSelectedIndex() == i) {

                    Bid b = new Bid(Integer.parseInt(bidAmount.getText()), date = LocalDate.now().toString(), time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString());

                    Bidder bidderSelected = (Bidder) bidderChoiceBox.getSelectionModel().getSelectedItem();
                    bidderSelected.addBid(b);
                    Lot lotSelected = lotListView2.getSelectionModel().getSelectedItem();

                    System.out.println(lotListView2.getSelectionModel().getSelectedItem().toString());
                    System.out.println(bidderSelected.getBidderName());

                    bidListView.getItems().clear();
                    //bidListView.getItems().add(b);

                    showLot(lotSelected);
                    showBid(bidderSelected);

                }
            }

        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public void showLot(Lot lot) {
        bidListView.getItems().clear();
        LinkedNode currentNode = Main.lotsList.head;

        for (int i = 0; i < Main.lotsList.size(); i++) {
            Lot currentLot = (Lot) currentNode.getContents();
            bidListView.getItems().add(currentLot);
            currentNode = currentNode.next;
        }
    }

    public void showBid(Bidder bidr) {
        bidListView.getItems().clear();
        LinkedNode currentNode = bidr.bidList.head;

        for (int i = 0; i < bidr.bidList.size(); i++) {
            Bid currentBid = (Bid) currentNode.getContents();

            bidListView.getItems().add(currentBid);
            currentNode = currentNode.next;
        }
    }

    public void deleteBid() { //TODO

        int selectedList = bidListView.getSelectionModel().getSelectedIndex(); // make a selectedList object
        Bidder bidderSelected = (Bidder) bidderChoiceBox.getSelectionModel().getSelectedItem(); // Cast in booth to to get the a booth from the booth list

        bidListView.getItems().remove(selectedList); // remove the selected appoint from the appointment list
        bidderSelected.bidList.delete(selectedList);
    }

    /**
     * Searching for unsold bids
     */
    public void searchUnSoldBids() {
        for (int i = 0; i < Main.lotsList.size(); i++) {
            Lot selectedLot = Main.lotsList.get(i);

        }

    }

    public void completeBid() {
        CompletedBids CB = new CompletedBids(lotListView2.getSelectionModel().getSelectedItem().getLotName(), (Integer.parseInt(bidAmount.getText())), date = LocalDate.now().toString(), time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString());
        int bidSelected = bidListView.getSelectionModel().getSelectedIndex(); // getting the selected appointment from the sleected booth index
        Lot lotSelected = lotListView2.getSelectionModel().getSelectedItem();

        bidListView.getItems().remove(bidSelected);// deleting the appointment from the selected booth
        lotListView2.getItems().remove(lotSelected); // removing the appointment form the view list

        getLotByName(lotName.getText()); // creating a vaccination record from the appointment that has just been removed

        completedBidsListView.getItems().add(CB); // adding that appointment to a vaccination record view list
        System.out.println(CB + "\n" + "==================================================================");

    }

    public static Lot getLotByName(String name) {
        for (int i = 0; i < Main.lotsList.size(); i++) {
            if (Main.lotsList.get(i).getLotName().equalsIgnoreCase(name))
                return Main.lotsList.get(i);
        }
        return null;
    }

    public void save(ActionEvent event) throws Exception {
        try {
            XStream xstream = new XStream(new DomDriver());
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("Bidders.xml"));
            out.writeObject(Main.biddersList);
            out.close();

            ObjectOutputStream out2 = xstream.createObjectOutputStream(new FileWriter("Lots.xml"));
            out2.writeObject(Main.lotsList);

            out2.close();

            ObjectOutputStream out3 = xstream.createObjectOutputStream(new FileWriter("CompletedBids.xml"));
            out3.writeObject(Main.completedBids);
            out3.close();

        } catch (Exception e) {
            System.out.println("Error writing this file : " + e);
        }
    }

    public void SearchSoldBids() {

        for (int i = 0; i < Main.lotsList.size(); i++) { // running over Lots with a for loop
            Lot selectedLot = Main.lotsList.get(i); // Selecting a Lot for every Lot
            if (selectedLot.completedBids != null) { // Checking if the selected lot has been completed
                for (int j = 0; j < selectedLot.completedBids.size(); j++) { // Running over all completed bids
                    CompletedBids selected = selectedLot.completedBids.get(j); // Getting the selected completed bid
                    if (soldSearchBar.getText().equals(selected.getLotName())) {   // Checking if the Name of the lot matches the name of what was typed in the box
                        soldBidsSearch.getItems().add(selected.toString()); // if so display it in the search box

                        System.out.println(selectedLot.toString());

                    }
                }

            }
        }
    }

}

