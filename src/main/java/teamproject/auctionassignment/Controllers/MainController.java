package teamproject.auctionassignment.Controllers;

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

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;



/*
====================================================================================
This is where we will keep ALL of our backend methods

It is organised in order of
Lot
Bidder
Bid
........



 */


public class MainController implements Serializable {

    public MainController() {

        this.lots = new LinkedList<Lot>();
        this.bidders = new LinkedList<Bidder>();
        this.bids = new LinkedList<Bid>();


    }


    public void initialize() {

    }

    /**
     * This is where Lot methods will be held
     */

    public LinkedList<Lot> lots;

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
        lots.addElement(lot);
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
        int x = lots.size();
        return x;
    }


    public void deleteLot(ActionEvent event) {//TODO
        if (lots.size() != 0) { // stops nullPointerException
            for (int i = 0; i < lots.size(); i++) {

                if (lotListView.getSelectionModel().getSelectedIndex() == i) {
                    lots.delete(i);
                    lotListView.getItems().remove(i);
                    lotListView2.getItems().remove(i);
                    System.out.println(lots.printList());
                    lotListNo.setText("There are " + numberOfLots() + " Lots");
                }
            }
        }
    }

    public void removeAllLots() {  // for reset facility
        lots.deleteList();
    }


    /**
     * Bidder methods
     */

    public LinkedList<Bidder> bidders;
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
    private ChoiceBox<Object> bidderChoiceBox;
    @FXML
    private Label bidderListNo;


    public void addBidder(Bidder bidder) {
        bidders.addElement(bidder);
    }

    public void addBidderDetails(ActionEvent event) {

        Bidder bidder = new Bidder(bidderName.getText(), bidderAddress.getText(), bidderPhone.getText(), bidderEmail.getText());
        biddersListView.getItems().add(bidder);

        bidderChoiceBox.getItems().add(bidder.getBidderName());

        addBidder(bidder);
        bidderListNo.setText("There are " + numberOfBidders() + " Bidders");

    }


    public void removeAllBidders() {

        biddersListView.getItems().clear();
        bidders.deleteList();
    }

    public int numberOfBidders() {
        int x = bidders.size();
        return x;
    }

    public void removeBidder(ActionEvent event) {
        if (bidders.size() != 0) { // stops nullPointerException
            for (int i = 0; i < bidders.size(); i++) {

                if (biddersListView.getSelectionModel().getSelectedIndex() == i) {
                    bidders.delete(i);
                    biddersListView.getItems().remove(i);
                    System.out.println(bidders.printList());
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


            setBidderName.setText(bidder.getBidderName()); //displays bidder info when page is brought up
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


        if (bidders.size() != 0) { // stops nullPointerException
            for (int i = 0; i < bidders.size(); i++) {

                if (biddersListView.getSelectionModel().getSelectedIndex() == i) {
                    //changes bidders info once edit button is hit
                    bidders.get(i).setBidderName(setBidderName.getText());
                    bidders.get(i).setAddress(setBidderAddress.getText());
                    bidders.get(i).setEmail(setBidderEmail.getText());
                    bidders.get(i).setPhone(setBidderPhoneNumber.getText());

                    System.out.println(bidders.printList());

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
    public void addBiddersBid(ActionEvent event) {
        Bid bid = new Bid(Integer.parseInt(bidAmount.getText()), date.toString(), time.toString());

        for (int i = 0; i < bidders.size(); i++) {
            if (lotListView2.getSelectionModel().getSelectedIndex() == i) {
                bidderChoiceBox.getSelectionModel().getSelectedIndex();

                bidListView.getItems().add(bidderChoiceBox.getSelectionModel().getSelectedItem() +
                        " is bidding on " + lotListView2.getSelectionModel().getSelectedItem().getLotName() + "\n" + (bid.toString()));
            }
        }


//
//        if (bids.size() != 0) {
//            lotListView2.getSelectionModel().getSelectedItem();
//            bid.setBidAmount(Integer.parseInt(bidAmount.getText()));  //sets the bidAmount to the one written in the TextField
    }


    public void removeAllBids() {
        bids.deleteList();
    }

    private static Bid bid;

    public void deleteBid(ActionEvent event) { //TODO
        //does not delete
        //if (bids.size() != 0) { // stops nullPointerException
        // for (int i = 0; i < bids.size(); i++) {

        //if (bidListView.getSelectionModel().getSelectedIndex() == i) {
        bids.delete(bidListView.getSelectionModel().getSelectedIndex());
        bidListView.getItems().remove(biddersListView.getSelectionModel().getSelectedItem());

        System.out.println(bids.printList());

    }
    // }
    // }


    /**
     * Searching for unsold bids
     */

}



































/*
    public void searchforBid(ActionEvent ActionEvent){
    search.getItems().clear();
    /
    space for
    /
    LinkedNode<Bid> Bids =Bid.head;
    while(Bids != null){
        if(Bid.getContents().getBidAmount().contains(

*/