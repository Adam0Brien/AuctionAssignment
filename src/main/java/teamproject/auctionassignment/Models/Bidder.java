package teamproject.auctionassignment.Models;

import teamproject.auctionassignment.ADT.LinkedList;
import teamproject.auctionassignment.ADT.LinkedNode;

public class Bidder {

    private String bidderName;
    private String address;
    private String phone;
    private String email;
    private LinkedList bids;



    public Bidder(String bidderName, String address, String phone, String email, LinkedList bids){

        this.bidderName = bidderName;

        this.address = address;

        this.phone = phone;

        this.email = email;

        this.bids = bids;

    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Bidder{" +
                "Bidder Name='" + bidderName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
