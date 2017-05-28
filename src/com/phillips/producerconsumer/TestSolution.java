/**
 * 
 */
package com.phillips.producerconsumer;

import java.util.*;
/**
 * @author Bryan Phillips <phillips.bryan.j@gmail.com>
 *
 */
public class TestSolution {

    private List<Boolean> leaves = new ArrayList<>();
    
    private int position;
        
    /**
     * Initialization of view field with n leaves and grasshopper on leaf 'position'.
     * 
     * @param n Number of leaves in row.
     * @param position
     */
    public TestSolution(int n, int position) {
        
        this.position = position;
        
        // Create the list of leaves for eating
        for(int index = 0; index < n; index++){
            leaves.add(Boolean.FALSE);    
        }
    }

/*    private void eatLeaf(){
        
        if(this.position >= 0 && this.position < leaves.size()){
            leaves.set(this.position - 1, Boolean.TRUE);
        }
    }
*/    
    // Move position, given the increment.
//    private int findLeaf(int increment){
        
//        int tempPosition = this.position + increment;
        
//        if(tempPosition >= 0 && tempPosition < leaves.size()){

             // Leaf not eaten, when false. Leave at temp posiiton.
//            if(leaves.get(tempPosition - 1)){
                
                // Traverse the leaves until one is found.
//                findLeaf(increment > 0 ? 1 : -1);
  //          }
               
        // If out of bounds, return initial position.
//        else {
//            tempPosition = this.position;
 //       }
        
//        return tempPosition;
 //   }
    
    /**
     * Grasshopper has eaten the current leaf and hopped left.
     */
    public void eatAndHopLeft() {
        
 //       eatLeaf();       
        
//        this.position = findLeaf(-2);
    }

    /**
     * Grasshopper has eaten the current leaf and hopped right.
     */
    public void eatAndHopRight() {
        
  //      eatLeaf();
        
 //       this.position = findLeaf(2);
    }

    /**
     * Return the leaf number that grasshopper is feeding on right now.
     * 
     * @return Leaf number that grasshopper is feeding on right now.
     */
    public int whereAmI() {
        return this.position;
    }

    public static void main(String[] args) {
    	TestSolution g = new TestSolution(5, 2);
        System.out.println(g.whereAmI());

        g.eatAndHopRight();
        System.out.println(g.whereAmI());

        g.eatAndHopLeft();
        System.out.println(g.whereAmI());
    }
}
