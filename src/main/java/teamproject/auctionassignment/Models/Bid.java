package teamproject.auctionassignment.Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Bid{

    public int bidAmount;
    public LocalTime time;


    public Bid(int bidAmount){

        this.bidAmount = bidAmount;

        this.time = time;

    }

    public int getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return
                "Bid" + bidAmount +"\n"+ time;

    }
}
