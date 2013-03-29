package pokergamegui;

import javax.swing.JComponent;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Cursor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;

class CardButton extends JComponent implements MouseListener
{
    private static final long serialVersionUID = 1L;

    private Dimension cardSize = new Dimension(40,60);
    final Font fnt1 = new Font("SansSerif", Font.BOLD, 18);
    final Font fnt2 = new Font("SansSerif", Font.BOLD, 11);
    
    private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
    private boolean mouseEntered = false;
    private boolean mousePressed = false;
    
    public enum Suits {
    	CLUBS("\u2663"), HEARTS("\u2665"), SPADES("\u2660"), DIAMONDS("\u2666");
    	private String s;
    	
    	private Suits(String value) {
            this.s = value;
    	}
    	@Override
        public String toString() {
            return this.s;
        }
    }
    
    public String val;
    public String suit;
    public int pos;
    public boolean selected = false;
    
    //public as shared with HandPanel
    public Dimension arc = new Dimension((int)Math.sqrt(cardSize.width), (int)Math.sqrt(cardSize.height));
    
    private HandPanel hand;

    public CardButton(int pos, HandPanel hand)
    {
        this(null,pos,hand);
    }
    public CardButton(ActionListener e, int pos,HandPanel hand)
    {
        super();

        this.hand = hand;

        enableInputMethods(true);
        addMouseListener(this);

        setSize(cardSize.width, cardSize.height);
        setFocusable(true);

        this.pos = pos;
    }
    
    public boolean getSelected(){
    	return selected;
    
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if(val != "-")
        {
            Graphics2D antiAlias = (Graphics2D)g;
            antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //rectangle
            g.setColor(Color.WHITE);
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc.width, arc.height);

            //border colour on roll over/out
            if((mouseEntered && hand.enabled && hand.selectedCount <3) || selected == true)
            {
                g.setColor(Color.GREEN);
            }
            else
            {
                g.setColor(Color.decode("#999999"));
            }
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc.width, arc.height);

            //inside border highlight
            g.setColor(Color.decode("#DDDDDD"));
            g.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, arc.width, arc.height);
            //Font colour
            g.setColor(Color.decode("#999999"));
            g.setFont(fnt1);

            if(hand.enabled){
            drawCenteredString(Suits.valueOf(suit.toUpperCase()).toString(), cardSize.width, cardSize.height, g);
            g.setFont(fnt2);
            drawStringTopAndBotom(val, cardSize.width, cardSize.height, g);
            }
            else{
            	drawCenteredString(hand.DECKBG, cardSize.width, cardSize.height, g);
            
            }
            
        }
    }
    //Draw Suit
    public void drawCenteredString(String s, int w, int h, Graphics g) {
       FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }
    //Draw values
    public void drawStringTopAndBotom(String s, int w, int h, Graphics g) {
    	 FontMetrics fm = g.getFontMetrics();
         int x = (w - fm.stringWidth(s)) -5 ;
         int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent()))) -2;
         g.drawString(s, x, y);
         x = 5 ;
         y = (fm.getAscent()) +2;
         g.drawString(s, x, y);
    }

	public void randomCards()
    {
        // Number of values in a suit
        int values = 14;
        int numSuits = 4;
        val = String.valueOf((int)(Math.random() * values + 1));
        suit = Suits.values()[(int)(Math.random() * numSuits)].toString();
        
        
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
        mouseEntered = true;
        if(val != "-" && hand.enabled)
        {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        repaint();
    }
    public void mouseExited(MouseEvent e)
    {
        mouseEntered = false;
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        repaint();
    }
    public void mousePressed(MouseEvent e)
    {
        notifyListeners(e);
        mousePressed = true;
        repaint();
    }
    public void mouseReleased(MouseEvent e)
    {
        mousePressed = false;
        if(val != "" && hand.enabled)
        {
        	
        	hand.selectCard(pos);
        	hand.repaint();
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        repaint();
    }
    public void addActionListener(ActionListener listener)
    {
        listeners.add(listener);
    }
    private void notifyListeners(MouseEvent e)
    {
    	
    	hand.buttonClicked();
    	
        ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, new String(), e.getWhen(), e.getModifiers());
        synchronized(listeners)
        {
            for (int i = 0; i < listeners.size(); i++)
            {
                ActionListener tmp = listeners.get(i);
                tmp.actionPerformed(evt);
            }
        }
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(getWidth(), getHeight());
    }
}