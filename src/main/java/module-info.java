module teamproject.auctionassignment {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires xstream;

    opens teamproject.auctionassignment to javafx.fxml, xstream;
    opens teamproject.auctionassignment.Controllers to javafx.fxml , xstream;
    opens teamproject.auctionassignment.ADT to xstream;
    opens teamproject.auctionassignment.Models to xstream;
    opens teamproject.auctionassignment.Hashmap to xstream;

    exports teamproject.auctionassignment.Controllers;
    exports teamproject.auctionassignment;





}