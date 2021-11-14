module teamproject.auctionassignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens teamproject.auctionassignment to javafx.fxml;
    exports teamproject.auctionassignment;
}