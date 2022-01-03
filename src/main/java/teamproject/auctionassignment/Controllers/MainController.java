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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import teamproject.auctionassignment.ADT.LinkedList;
import teamproject.auctionassignment.ADT.LinkedNode;
import teamproject.auctionassignment.Driver;
import teamproject.auctionassignment.Main;
import teamproject.auctionassignment.Models.Bid;
import teamproject.auctionassignment.Models.Bidder;
import teamproject.auctionassignment.Models.CompletedBids;
import teamproject.auctionassignment.Models.Lot;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;



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




	@FXML
	ListView completedBidsListView;

	public void initialize() {

			}
		}
		return null;
	}

	/**
	 * This is where Lot methods will be held
	 */

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


	public void loadMain(ActionEvent event) {
		Driver.stage.setScene(Driver.mainScene);
	}

	public void exitProgram(ActionEvent event) {
		System.exit(1);
	}

	public void loadMainFromPreviousSave(ActionEvent event) throws Exception {
		load(event);
		loadMain(event);

	}


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
	private String date;
	private String time;


	public void addBids() {
		try {
			for (int i = 0; i < Main.biddersList.size(); i++) {

				if (lotListView2.getSelectionModel().getSelectedIndex() == i) {

					Bid b = new Bid(Integer.parseInt(bidAmount.getText()), date = LocalDate.now().toString(), time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString());


							Bidder bidderSelected = (Bidder) bidderChoiceBox.getSelectionModel().getSelectedItem();
							bidderSelected.addBid(b);

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

	public void showBid(Bidder bidr) {
		bidListView.getItems().clear();
		LinkedNode currentNode = bidr.bidList.head;

		for (int i = 0; i < bidr.bidList.size(); i++) {
			Bid currentBid = (Bid) currentNode.getContents();

			bidListView.getItems().add(currentBid);
			currentNode = currentNode.next;
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


	private static Bid bid;

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

	public static Lot getLotByName(String name) {
		for (int i = 0; i < Main.lotsList.size(); i++) {
			if (Main.lotsList.get(i).getLotName().equalsIgnoreCase(name))
				return Main.lotsList.get(i);
		}
		return null;
	}


	public void completeBid() {
		CompletedBids CB = new CompletedBids(lotListView2.getSelectionModel().getSelectedItem().getLotName(), (Integer.parseInt(bidAmount.getText())), date = LocalDate.now().toString(), time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString());
		int bidSelected = bidListView.getSelectionModel().getSelectedIndex(); // getting the selected appointment from the sleected booth index
		Lot lotSelected = (Lot) lotListView2.getSelectionModel().getSelectedItem();

		bidListView.getItems().remove(bidSelected);// deleting the appointment from the selected booth
		lotListView2.getItems().remove(lotSelected); // removing the appointment form the view list

		getLotByName(lotName.getText()); // creating a vaccination record from the appointment that has just been removed

		completedBidsListView.getItems().add(CB); // adding that appointment to a vaccination record view list
		System.out.println(CB + "\n" + "==================================================================");


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
			bidderChoiceBox.getItems().add((Bidder) currentNode.getContents());//populates choicebox
			currentNode = currentNode.next;
		}


	}

	public void loadLotsListView() {
		lotListView.getItems().clear();
		lotListView2.getItems().clear();
		LinkedNode currentNode = Main.lotsList.head;

		while (currentNode != null) {
			lotListView.getItems().add(String.valueOf((Lot) currentNode.getContents()));//populates both listviews
			lotListView2.getItems().add((Lot) currentNode.getContents());
			currentNode = currentNode.next;
		}
	}

	public void loadCBListView() {
		completedBidsListView.getItems().clear();

		LinkedNode currentNode = Main.completedBids.head;

		while (currentNode != null) {
			completedBidsListView.getItems().add(String.valueOf((Lot) currentNode.getContents()));//populates both listviews
			currentNode = currentNode.next;
		}
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

		for (int i = 0; i < Main.lotsList.size(); i++) { // running over Patients with a for loop
			Lot selectedLot = Main.lotsList.get(i); // Selecting a Patient for every Patient
			if (selectedLot.completedBids != null) { // Checking if the selected Patient's record isn't null (Has a record)
				for (int j = 0; j < selectedLot.completedBids.size(); j++) { // Running over all selected patients records
					CompletedBids selected = selectedLot.completedBids.get(j); // Getting the vaccination record for that patient
					if (soldSearchBar.getText().equals(selected.getLotName())) {   //|| recordPPSN.getText().equals(selected.getPPSN()) // Checking if the vaccination record match the batch of what was typed in the box
						soldBidsSearch.getItems().add(selected.toString()); // If so return this

						System.out.println(selectedLot.toString());


					}
				}


			}


		}


	}



}

