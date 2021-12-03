//package teamproject.auctionassignment;
//
//import teamproject.auctionassignment.ADT.LinkedList;
//
////https://www.youtube.com/watch?v=8mJ-OhcfpYg
//public class sort {
//
//        // Insertion sort = after comparing elements to the left,
//        //				shift elements to the right to make room to insert a value
//
//        //				Quadratic time O(n^2)
//        //				small data set = decent
//        //				large data set = BAD
//
//        //				Less steps than Bubble sort
//        //				Best case is O(n) compared to Selection sort O(n^2)
//
//        public static void main(String[] args) {
//
//            LinkedList<Integer> numbers = new LinkedList();
//
//            numbers.addElement(9);
//            numbers.addElement(2);
//            numbers.addElement(78);
//            numbers.addElement(99);
//            numbers.addElement(2);
//            numbers.addElement(7);
//            numbers.addElement(47);
//
//
//            insertionSort(numbers);
//
//
//            for(int i : numbers) {
//                System.out.print(i + " ");
//            }
//        }
//
//        private static void insertionSort(LinkedList<Integer> nums) {
//
//            for(int i = 1; i < nums.size(); i++) {
//                int temp = nums.get(i);
//                int j = i - 1;
//
//                while(j >= 0 && nums.get(j) > temp) {
//                    nums.size()+1 = nums.size();
//
//                    nums[j + 1] = nums[j];
//                    j--;
//                }
//                nums[j + 1] = temp;
//            }
//        }
//
//
//}
//
