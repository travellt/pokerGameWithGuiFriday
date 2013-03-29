/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokergamegui;

/**
 *
 * @author jameswillby
 */
public class Card {
    
    private String suit;
    private int value;
    
    public Card(String su, int x)
    {
        this.suit = su;
        this.value = x;
    }
    
    
    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
   
    boolean equals(Card e)
    {
    if (this.suit.equals(e.getSuit()) && this.value == e.getValue())
        return true;
    return false;
    }
}
