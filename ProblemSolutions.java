
/******************************************************************
 *
 *  Jennifer Nambo / 002
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;
import java.util.PriorityQueue;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     *
     * PQ1 Problem Statement:
     * -----------------------
     *
     * You are given an array of integers of boulders where boulders[i] is the
     * weight of the ith boulder.
     *
     * We are playing a game with the boulders. On each turn, we choose the heaviest
     * two boulders and smash them together. Suppose the heaviest two boulders have
     * weights x and y. The result of this smash is:
     *
     *    If x == y, both boulders are destroyed, and
     *    If x != y, the boulder of weight x is destroyed, and the boulder of
     *               weight y has new weight y - x.
     *
     * At the end of the game, there is at most one boulder left.
     *
     * Return the weight of the last remaining boulder. If there are no boulders
     * left, return 0.
     *
     *
     * Example 1:
     *
     * Input: boulders = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
     * value of the last stone.
     *
     * Example 2:
     *
     * Input: boulders = [1]
     * Output: 1
     *
     *
     *
     * RECOMMENDED APPROACH
     *
     * Initializing Priority Queue in reverse order, so that it gives
     * max element at the top. Taking top Elements and performing the
     * given operations in the question as long as 2 or more boulders;
     * returning the 0 if queue is empty else return pq.peek().
     */

  public static int lastBoulder(int[] boulders) {

      /*
      Did use oracle for priority queue methods refresher!
      link of reference: https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html
      - Priority Queue in reverse order (could use Collections for this?)
      - as states above, smash two heaviest boulders
        - get top two elements
        -check for same weight then destroy (y==x)
        - if not, do y-x as stated above
      - then return weight of last boulder (0 if none left)
       */
      //the queue in reverse order as instructed
      PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
     //put boulders in queue
      for (int boulder : boulders) {
          maxHeap.offer(boulder);
      }
      //loop for smashing until none or 1 is left
      //get two largest ones
      while (maxHeap.size()> 1) {
          int x = maxHeap.poll();
          int y = maxHeap.poll();
          if (x != y) {
              //substract y from x if not ==
              maxHeap.offer(x-y);
          }
      }
      //returning depedning if there's any left
      if (maxHeap.isEmpty()){
          return 0;
      }else{
          return maxHeap.peek();
      }
  }


    /**
     * Method showDuplicates
     *
     * This method identifies duplicate strings in an array list. The list
     * is passed as an ArrayList<String> and the method returns an ArrayList<String>
     * containing only unique strings that appear more than once in the input list.
     * This returned array list is returned in sorted ascending order. Note that
     * this method should consider case (strings are case-sensitive).
     *
     * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
     * the method would return an ArrayList<String> containing: "Dog", "Lion"
     *
     * @param  input an ArrayList<String>
     * @return       an ArrayList<String> containing only unique strings that appear
     *               more than once in the input list. They will be in ascending order.
     */

    public static ArrayList<String> showDuplicates(ArrayList<String> input) {

        /*
        - count the number of times each string is in the list.
        - put the unique strings into an array list
        - return list in ascending order
         */

        //create list for unique strings
        ArrayList<String> uniqueList = new ArrayList<>();
        //loop will go through each string in given list
        for (int i = 0; i < input.size(); i++) {
            //current string in loop is held using var str
            String str = input.get(i);
            //counter for occurences of currentString
            int counter = 0;
            //this loop will also go through each str again to now begin counting its occurences
            for (String currentString : input) {
                //see if currentString matches string in previous loop
                if (currentString.equals(str)) {
                    //if there is a match than the counter increases
                    counter++;
                }
            }
            //checks that the string is appears in the list more than once
            //the other condition set here checks that the string has not been added,
            //to prevent it from being added more than once
            if (counter > 1 && !uniqueList.contains(str)) {
                //if the conditions are met then the string is added to uniqueList
                uniqueList.add(str);
            }
        }
        //list is sorted in ascending order and Comparator is used as well
        uniqueList.sort(Comparator.naturalOrder());
        return uniqueList;  // Make sure result is sorted in ascending order

    }


    /**
     * Finds pairs in the input array that add up to k.
     *
     * @param input   Array of integers
     * @param k       The sum to find pairs for

     * @return an ArrayList<String> containing a list of strings. The ArrayList
     *        of strings needs to be ordered both within a pair, and
     *        between pairs in ascending order. E.g.,
     *
     *         - Ordering within a pair:
     *            A string is a pair in the format "(a, b)", where a and b are
     *            ordered lowest to highest, e.g., if a pair was the numbers
     *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
     *         - Ordering between pairs:
     *            The ordering of strings of pairs should be sorted in lowest to
     *            highest pairs. E.g., if the following two string pairs within
     *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
     *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
     *
     *         Example output:
     *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
     *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
     *
     *  HINT: Considering using any Java Collection Framework ADT that we have used
     *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
     *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
     *  Java Framework documentation in its use.
     */

    public static ArrayList<String> pair(int[] input, int k) {

        /*
        - pairs are smallest to largest
        - sort pairs in ascending order
         */
        //hash set will be used to remember which numbers have been seen
        HashSet<Integer> checked = new HashSet<>();
        //pairs will be kept here
        ArrayList<String> finalList = new ArrayList<>();
        //hash set to store pairs already put in final list (no repeats)
        HashSet<String> holdingSet = new HashSet<>();

        //loop to iterate over the numbers given for finding pairs
        for (int number : input) {
            //find complement (pair)
            int pair = k - number;
            //here we will check if the pair is in the set
            if (checked.contains(pair)) {
                //if yes,here the pairs will be formatted so that the smaller number is first,
                int a = Math.min(number, pair);
                int b = Math.max(number, pair);
                String pairOrder = ("(" + a + ", " + b + ")");

                //if pair has not been added
                if (!holdingSet.contains(pairOrder)) {
                    finalList.add(pairOrder);
                    holdingSet.add(pairOrder);
                }
            }
            // then add pair to finalList
            checked.add(number);
        }
        System.out.println("before" + finalList);
        //Using Collections for ascending order
        Collections.sort(finalList);

        System.out.println("after" + finalList);

        return finalList;  // Make sure returned lists is sorted as indicated above
    }
}