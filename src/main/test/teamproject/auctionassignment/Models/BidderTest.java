package teamproject.auctionassignment.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BidderTest {
    static class BoothTest {
        private Bidder validBidder;
        private Bidder invalidBidder;


        @BeforeEach
        public void setUp() {
            validBidder = new Bidder("John Smith", "91 Patrick Street", "0858274512", "JSmith@gmail.com");
            invalidBidder = new Bidder("Johnathan Smithy", "The moon", "0", "JSmith@");


        }

        @Test
        public void testConstructor() {
            //test on valid data
            assertEquals("John Smith", validBidder.getBidderName());
            assertEquals("91 Patrick Street", validBidder.getAddress());


            //test on invalid data

            assertEquals("Johnathan Smithy", invalidBidder.getBidderName());
            assertEquals("The moon", invalidBidder.getAddress());

        }

        @Test
        void getBidderName() {
            assertEquals("John Smith", validBidder.getBidderName());
        }


        @Test
        void setBidderName() {
            validBidder.setBidderName("Harry");
            assertEquals("Harry", validBidder.getBidderName());
        }

        @Test
        void getAddress() {
        }

        @Test
        void setAddress() {
            validBidder.setAddress("90 Williams Street");
            assertEquals("90 Williams Street",validBidder.getAddress());
        }

//        @Test
//        void getPhone() {
//        }
//
//        @Test
//        void setPhone() {
//        }
//
//        @Test
//        void getEmail() {
//        }
//
//        @Test
//        void setEmail() {
//        }
//
//        @Test
//        void getBids() {
//        }
//
//        @Test
//        void setBids() {
//        }
//
//        @Test
//        void testToString() {
//        }


    }
}