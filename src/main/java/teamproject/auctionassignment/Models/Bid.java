package teamproject.auctionassignment.Models;

import java.time.LocalDate;

public class Bid{

    public int bidAmount;
    public String date;
    public String time;


    public Bid(int bidAmount, String date, String time){

        this.bidAmount = bidAmount;

        this.date = date;

        this.time = time;



    }

    public int getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }




    @Override
    public String toString() {
        return
                "Bid: " + "€"+bidAmount +"\n"+ date +"\n"+ time;

    }


}
