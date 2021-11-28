package teamproject.auctionassignment.ADT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTest {

    private LinkedList validList;

    @BeforeEach
    void setUp() {
        validList = new LinkedList();
    }

    @Test
    void addElement() {
        validList.addElement(2);
        validList.addElement(7);
        assertEquals(2,validList.size());

    }



    @Test
    void deleteList() {
        validList.addElement(1);
        validList.addElement(2);
        validList.addElement(3);
        validList.addElement(4);
        assertEquals(4,validList.size());
        validList.deleteList();

        assertEquals(0,validList.size());
    }

    @Test
    void delete() {
        validList.addElement(1);
        validList.addElement(2);
        validList.addElement(3);
        validList.addElement(4);
        validList.delete(0);
        assertEquals(3,validList.size());
    }



    @Test
    void listLength() {
        validList.addElement(1);
        assertEquals(1,validList.size());
    }
}