package teamproject.auctionassignment.ADT;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {
    public LinkedNode<E> head=null;

    /**
     *
     * Adding and deleting head(most recently added) elements works fine
     *
     * -Work on
     *      size()
     *      clear()
     *      search()
     *      removeRange()
     *      edit()
     *
     *      once that's done LinkedLists are basically finished
     */

    public E get(int e){
        LinkedNode<E> node = head;
        for (int i = 0; i < e; i++){
            node = node.next;
        }
        return node.getContents();
    }



    public void addElement(E e) { //Add element to top of list
        LinkedNode<E> node=new LinkedNode<>(); //DONE
        node.setContents(e);
        node.next=head;
        head=node;
    }
    public void deleteList() {
        head = null;  // if the head of the list is null it falls apart
    }

    public void delete(int index){
        LinkedNode<E> currentNode = head;
        if (index == 0 ){  // represents the head element as an int
            head = head.next;
        }
        else{
            int counter = 0;  // counts
            while(currentNode.next != null && counter < index -1){  //while there is still a node left until the last node(only works until the second last node)
                currentNode = currentNode.next;
                counter++;  //counts until there is no currentNodes left
            }
            currentNode.next = currentNode.next.next;
        }
    }

    public LinkedList<E> getList(){
        return this;
    }

    public LinkedList<E> setList(){
        return this;
    }


    public String printList(){
        String theList = "";
        LinkedNode<E> currentNode = head;

        while(currentNode != null){  // while there is still a node after the current node
            theList = theList + currentNode.getContents() + "\n";  // get the contents of it and add it to theList
            currentNode = currentNode.next;
        }
        return theList;  // return the string list
    }

//    public void setList(LinkedList x){
//
//    }


    public int size()
    {
        LinkedNode temp = head; // make a copy of the head
        int counter = 0;
        while (temp != null)  //while there is a node after the head
        {
            counter++; //the counter will start counting
            temp = temp.next; //then when the head
        }
        return counter;  // when there is nothing in front of the head it returns how many nodes were counted
    }


    public LinkedNode<E> getHead() {
        return head;
    }

    public void setHead(LinkedNode<E> head) {
        this.head = head;
    }




    public void searchElement() { //TODO   maybe works
//searches for elements by name

        LinkedNode temp=head;
        while(temp!=null && temp.getContents()!= getHead().getContents())
            temp=temp.next;
        if(temp!=null){
        }


        // printList();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private LinkedNode<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;  // if the current head does not have an element to go to return true
            }

            @Override
            public E next() {
                LinkedNode<E> placeholder = current; // makes a copy of current
                current = current.next; //makes the current go to the next element
                return placeholder.getContents();  // prints the placeholder
            }
        };

    }


//
//    @Override
//    public String toString() {
//
//        String listMyList = "List:\n["+ "\n"  + head.getContents();
//        for(LinkedNode temp = head; temp!=null; temp = temp.next ){
//            listMyList = listMyList + temp.getContents() + "\n";
//        }
//        return listMyList+"]";
//    }


}








