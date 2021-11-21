package teamproject.auctionassignment.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import teamproject.auctionassignment.ADT.LinkedList;
import teamproject.auctionassignment.Models.Bid;
import teamproject.auctionassignment.Models.Bidder;
import teamproject.auctionassignment.Models.Lot;


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

                System.out.println(lots.printList());
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



            public void addBidders(Bidder bidder){
                bidders.addElement(bidder);
            }

            public void addBidderDetails(ActionEvent event){

                biddersListView.getItems().addAll(bidderName.getText() + " " + bidderAddress.getText() +" " + bidderPhone.getText() +" "+ bidderEmail.getText());

                addBidders(new Bidder(bidderName.getText(),bidderAddress.getText(),bidderPhone.getText(),bidderEmail.getText()));

                System.out.println(bidders.printList());
            }


    /**
     * Bid Methods
      */
            @FXML private TextField bidAmount;



            public LinkedList<Bid> bids;
            public void addBids(Bid bid){
                bids.addElement(bid);
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
            public void addBidsToBidders(){


                addBids(new Bid(bidAmount.getAnchor()));

                System.out.println(bids.printList());
            }



//abcdefg hahahahahha



}

