package teamproject.auctionassignment.ADT;


public class LinkedNode<N> {

    public LinkedNode<N> next=null;
    private N contents; //ADT reference!

    public N getContents() {
        return contents;
    }

    public void setContents(N c) {
        contents=c;
    }

    public void setNext(LinkedNode n)
    {
        next = n;
    }
}
