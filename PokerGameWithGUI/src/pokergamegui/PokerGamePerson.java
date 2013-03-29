package pokergamegui;

import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jameswillby
 */
public interface PokerGamePerson {
    
     public void takeCard(Card cr);
     public int[] evaluateTheHand();
     public Hand getHand();
     public void swapCards(int[] positions, Deck deck);
     public int[] CardsToArray(Vector<Card> throwCards);
}
