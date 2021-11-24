package teamproject.auctionassignment.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import teamproject.auctionassignment.ADT.LinkedList;
import teamproject.auctionassignment.Models.Bid;
import teamproject.auctionassignment.Models.Bidder;
import teamproject.auctionassignment.Models.Lot;

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


public class MainController {

        public MainController() {

                this.lots = new LinkedList<Lot>();
                this.bidders = new LinkedList<Bidder>();
                this.bids = new LinkedList<Bid>();
        }


    /**
     *
     * This is where Lot methods will be held
     *
     */

    public LinkedList<Lot> lots;

            @FXML private TextField lotName;
            @FXML private TextField description;
            @FXML private TextField type;
            @FXML private TextField yearsOld;
            @FXML private TextField askingPrice;
            @FXML private ListView<String> lotListView;
            @FXML private Button deleteButton;


            public void addLot(Lot lot){
                lots.addElement(lot);
            }


            public void addLotDetails(ActionEvent event) {


                Lot lot = new Lot(lotName.getText(),description.getText(),type.getText(),Integer.parseInt(yearsOld.getText()),Integer.parseInt(askingPrice.getText()));
                //gui
                lotListView.getItems().add(lot.toString());
                //backend
                addLot(lot);
                //lotListNo.setText("There are " + numberOfLots() + " lots");
            }

    public int numberOfLots() {
        int x = lots.size();
        return x;
    }


    public void deleteLot(ActionEvent event){//TODO
        if (lots.size() != 0)
        { // stops nullPointerException
            for (int i = 0; i < lots.size(); i++)

        if(lots.get(i).equals(lotListView.getItems().get(lotListView.getSelectionModel().getSelectedIndex()))){
            lots.delete(i);
                    lotListView.getItems().remove(i);
                    System.out.println(lots.printList());
                    //lotListNo.setText("There are " + numberOfBooths() + " Booths");

                }

         }
    }

    public void removeAllLots(){

         lots.deleteList();

    }


    /**
     *Bidder methods
     */

            public LinkedList<Bidder> bidders;
            @FXML private TextField bidderName;
            @FXML private TextField bidderPhone;
            @FXML private TextField bidderEmail;
            @FXML private TextField bidderAddress;
            @FXML private ListView biddersListView;
            @FXML private ChoiceBox<Object> bidderChoiceBox;



            public void addBidders(Bidder bidder){
                bidders.addElement(bidder);
            }

            public void addBidderDetails(ActionEvent event){

                Bidder bidder = new Bidder(bidderName.getText(), bidderAddress.getText() , bidderPhone.getText() , bidderEmail.getText());
                biddersListView.getItems().add(bidder.toString());

                bidderChoiceBox.getItems().add(bidder.getBidderName());

            }


            public void removeAllBidders(){

                biddersListView.getItems().clear();
                bidders.deleteList();
            }

    public void removeSelectedBidder(){
        if (bidders.size() != 0)
        { // stops nullPointerException
            for (int i = 0; i < bidders.size(); i++)

                if(bidders.get(i).toString().equals(biddersListView.getItems().get(biddersListView.getSelectionModel().getSelectedIndex())))
                {
                    bidders.delete(i);
                    biddersListView.getItems().remove(i);
                    System.out.println(bidders.printList());

                }

        }
    }


    /**
     * Bid Methods
      */
            @FXML private TextField bidAmount;

            @FXML private ListView bidListView;
                    private LocalDate date = LocalDate.now();
                    private LocalTime time = LocalTime.now();


            public LinkedList<Bid> bids;
            public void addBids(Bid bid){
                bids.addElement(bid);
            }
    public void addBiddersBid(ActionEvent event){


        Bid bid = new Bid(Integer.parseInt(bidAmount.getText()), date.toString(), time.toString());

        
        bidListView.getItems().add(bid.toString());
    }

//TODO
    /**
     *      Can't seem to get the bidAmount to print to console
     *      i don't want to change it to a string because we need to be ble to apply
     *      arithmetic to it later on. this is so we can calculate who has the
     *      highest bid to determine the winner
     *
     *      -Adam
     */
//            public void addBidsToBidders(){
//
//
//                addBids(new Bid(Integer.parseInt(bidAmount.getText())));
//
//                System.out.println(bids.printList());
//            }



            public void removeAllBids(){
                bids.deleteList();
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






}

