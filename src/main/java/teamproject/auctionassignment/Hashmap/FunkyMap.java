package teamproject.auctionassignment.Hashmap;

public class FunkyMap<K,V> {
    private FunkyMapEntry head=null;
    public void put(K key, V value) {
        if(get(key)!=null) remove(key); //No duplicates!
        FunkyMapEntry fme=new FunkyMapEntry(key,value);
        fme.next=head;
        head=fme;
    }
    public V get(K key) {
        FunkyMapEntry temp=head;
        while(temp!=null && !temp.key.equals(key))
            temp=temp.next;
        return temp==null ? null : temp.value;
    }
    public V remove(K key) {
        FunkyMapEntry temp=head,prev=null;
        while(temp!=null && !temp.key.equals(key)){
            prev=temp;
            temp=temp.next;
        }
        if(temp!=null){
            if(prev==null) head=temp.next;
            else prev.next=temp.next;
            return temp.value;
        }
        return null;
    }
    public void clear() {
        head=null;
    }
    public boolean isEmpty() {
        return head==null;
    }
    public int size() {
        FunkyMapEntry temp=head;
        int count=0;
        while(temp!=null) {
            temp=temp.next;
            count++;
        }
        return count;
    }
//    public FunkySet<K> getKeySet() {
////We'll reuse FunkySet from earlier:)
//        FunkySet<K> ks=new FunkySet<>();
//        FunkyMapEntry temp=head;
//        while(temp!=null) {
//            ks.add(temp.key);
//            temp=temp.next;
//        }
//        return ks;
//    }
    //-------------inner class-----------------------------
    private class FunkyMapEntry{
        public K key;
        public V value;
        FunkyMapEntry next=null;
        public FunkyMapEntry(K key,V value){
            this.key=key;
            this.value=value;
        }
    }
} //End of FunkyMap class
