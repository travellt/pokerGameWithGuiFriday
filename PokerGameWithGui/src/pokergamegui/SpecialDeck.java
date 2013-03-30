/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokergamegui;
import java.util.*;
/**
 *
 * @author jameswillby
 */
public class SpecialDeck extends Deck {
    
   
private static String[] suits = {"Clubs","Hearts","Spades","Diamonds"};  

private static int[] values = {0,0,2,3,4,5,6,7,8,9,10,11,12,13,14};
private Vector<Card> deck = null;

public SpecialDeck(Hand hand)       
{
        deck = new Vector<Card>(); // initialize the empty vector
        for (int x = 0; x < suits.length;x++)
        	for(int y =2;y < values.length;y++){
        		Card tempcard = new Card(suits[x],values[y]);
        		int addit = 0;
        		for (int i = 0; i < hand.size(); i++){
        			if ((hand.get(i).getSuit().equals(tempcard.getSuit())) && hand.get(i).getValue() == tempcard.getValue())
        				addit = 1;
        		}
        		if (addit == 0)
        			deck.add(tempcard); // add the cards of each suit in order
        	}
        
}

public int cardsInDeck()
{
        return deck.size();
}

public Card getTopCard() {
	return deck.get(deck.size()-1);
}

public Card get(int j) {
	return deck.get(j);
}

public void printDeck()
{
        for (int x=0; x < deck.size();x++)
        System.out.println("Suit of " + deck.elementAt(x).getSuit() + " value of " + deck.elementAt(x).getValue());
}

public Card returnTheTopCard(){
	 Card card = deck.get(deck.size()-1);
	 deck.removeElementAt(deck.size()-1); 
	 return card;
	}

}