module teamproject.auctionassignment {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens teamproject.auctionassignment to javafx.fxml;
    opens teamproject.auctionassignment.Controllers to javafx.fxml;


    exports  teamproject.auctionassignment.Controllers;
    exports teamproject.auctionassignment;

}