package pokergamegui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.util.Arrays;
import java.util.ArrayList;

public class HandPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    public final String DECKBG = "\u272A";  
    public CardButton[] cards = new CardButton[5];
    public boolean enabled = false;
    public boolean isenabled(){
    	return enabled;
    }
   public int selectedCount = 0;
    private TablePanel table;
    public HandPanel(TablePanel table, PokerGamePerson p, boolean enabled) {
    	this.enabled = enabled;
    	this.table = table;
    	
    	Hand h = p.getHand();
        for(int i = 0; i < cards.length; i++)
        {
        	Card c = h.get(i);
        	cards[i] = new CardButton(i, this);
        	cards[i].val = c.getValue();
        	cards[i].suit = c.getSuit();
        	add(cards[i]);
        }
        repaint();
    }
    
    public CardButton[] getCards(){
    	return cards;
    
    }
    
    public HandPanel(TablePanel table,PokerGamePerson p)
    {
    	
    	this.table = table;
    	Hand h = p.getHand();
        for(int i = 0; i < cards.length; i++)
        {
        	Card c = h.get(i);
        	cards[i] = new CardButton(i, this);
        	cards[i].val = c.getValue();
        	cards[i].suit = c.getSuit();
        	add(cards[i]);
        }
        repaint();
        System.out.println("hand created " + this);
    }
    

	public void paint(Graphics g)
    {
        super.paint(g);
        
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 10;
        int offset = 1;

        //Outer rectangle
        g.setColor(Color.decode("#999999"));
        g.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        //Inner rectangle
        g.setColor(Color.decode("#cccccc"));
        g.fillRoundRect(offset, offset, getWidth() - (offset * 2), getHeight() - (offset * 2), arc, arc);

        g.setColor(Color.WHITE);
        for(int i = 0; i < cards.length; i++)
        {
            g.fillRoundRect(
                cards[i].getX() - offset,
                cards[i].getY() - offset,
                cards[i].getWidth() + (offset * 2),
                cards[i].getHeight() + (offset * 2),
                cards[i].arc.width,
                cards[i].arc.height
            );
        }
        super.paintChildren(g);
    }
    public void addCards()
    {
       for(int i = 0; i < cards.length; i++)
        {
            if(cards[i].val == -1)
            {
                cards[i].val = 0; //initialised but empty
                return;
            }
        }
        return;
    }
    
    
    public void setSelectedCount(int x){
    	selectedCount = x;
    
    }
    public int getSelectedCount(int x){
    	return selectedCount;
    
    }
    public void addPlayer(int v, String s)
    {
        for(int i = 0; i < cards.length; i++)
        {
            if(cards[i].val == -1)
            {
                cards[i].val = v;
                cards[i].suit = s;
                return;
            }
        }
        return;
    }
    
    public void removeCard(int pos)
    {
        if(cards[pos].val != -1)
        {
            cards[pos].val = 0;
        }
    }
    public void randomCards()
    {
        for(int i = 0; i < cards.length; i++)
        {
            if(cards[i].val != -1)
            {
                cards[i].randomCards();
            }
        }
    }
	public void selectCard(int pos) {
		
		if(cards[pos].selected){
			cards[pos].selected = false;
			selectedCount--;
		}
		else if(selectedCount <3){
			cards[pos].selected = true;
			selectedCount++;
		}
		
		if(!table.ChangeButton.isVisible()){
			//System.out.println(table.messages);
			
			table.ChangeButton.setVisible(true);
			table.ChangeButton.setEnabled(selectedCount >0  && selectedCount <=3);
		}
	}
    
	public void buttonClicked()
	{	if(selectedCount <=3)
		table.clearMessages();
	}
    
}