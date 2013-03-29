/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokergamegui;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author jameswillby
 */
class Dealer implements PokerGamePerson {

    private Hand hand = null;
    private DealerAI dealerBrain = null;
   
    
    public Dealer(){                // create a new instance and initialises and assigns the deck,                          
        // the dealers hand and the discard pile. 
        hand = new Hand();
    }
        
       
    @Override
    public void takeCard(Card cr) { // pick up dealt card and add to the dealer's hand
        hand.add(cr);   
    }

    @Override
    public int[] evaluateTheHand(){
        dealerBrain =  new DealerAI(hand);
        return  CardsToArray(dealerBrain.whichCardsShouldISwap());
      
    }
    

    @Override
    public Hand getHand() {
        return hand;
    }
  
    public void swapCards(int[] positions, Deck deck) {
    	
    	for (int i = 0; i < positions.length; i++){
    		hand.setElementAt(deck.returnTheTopCard(), positions[i]);
    	}
    }
	
    
	@Override
	   public int[] CardsToArray(Vector<Card> throwCards) {
	        
	       
	        List<Integer> array = new ArrayList<Integer>(); // we can’t alter the size of an array. So we need a work around using a List.
	        
	        if(throwCards.size() == 0) {int[] x = new int[0]; return x;}
	        
	        for (int x = 0; x < throwCards.size();x++){
	        	for (int y = 0; y < hand.size();y++){ // cards is the player hand.
	        		if (hand.get(y).equals(throwCards.get(x)) && !array.contains(Integer.valueOf(y)))
	            array.add(new Integer(y));
	        }}
	        
	        int[] x = new int[array.size()]; // now initialise our return array to be the same size as our List.
	        
	        for (int z = 0; z < array.size();z++)
	        x[z] = array.get(z).intValue(); // add the values
	        
	        return x; // return the array
	}

}