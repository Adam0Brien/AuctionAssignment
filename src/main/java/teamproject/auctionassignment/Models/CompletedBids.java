package teamproject.auctionassignment.Models;

public class CompletedBids {

    public String lotName;
    public int bidAmount;
    public String date;
    public String time;


    public CompletedBids(String lotName, int bidAmount, String date, String time){


        this.lotName = lotName;

        this.bidAmount = bidAmount;

        this.date = date;

        this.time = time;



    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
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
        return "CompletedBids{" +
                "lotName='" + lotName + '\'' +
                ", bidAmount=" + bidAmount +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

}
