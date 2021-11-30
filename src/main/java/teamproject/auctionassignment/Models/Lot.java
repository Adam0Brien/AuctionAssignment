package teamproject.auctionassignment.Models;

import teamproject.auctionassignment.ADT.LinkedList;

public class Lot {

    private String lotName;
    private String description;
    private String type;
    private int yearsOld;
    private float askingPrice;
    private LinkedList<Bidder> bidder;


    public Lot(String lotName, String description, String type, int yearsOld, float askingPrice) {
        this.lotName = lotName;

        this.description = description;

        this.type = type;

        this.yearsOld = yearsOld;

        this.askingPrice = askingPrice;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public float getAskingPrice() {
        return askingPrice;
    }

    public Object setAskingPrice(float askingPrice) {
        this.askingPrice = askingPrice;
        return null;
    }

    public LinkedList<Bidder> getBidder() {
        return bidder;
    }

    public void setBidder(LinkedList<Bidder> bidder) {
        this.bidder = bidder;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "Lot Name='" + lotName + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String listViewToString() {
        return lotName + "\n" + description + "\n" + askingPrice;
    }


}
