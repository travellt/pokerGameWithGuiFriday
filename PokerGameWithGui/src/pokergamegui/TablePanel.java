package pokergamegui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TablePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private GridBagLayout layout = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();

	private PokerGUI gui;
	private Game game;
	
	protected JButton ChangeButton;
	protected JLabel messages;
	final String CHANGE = "Change Cards";
	
	private HandPanel player1Hand, player2Hand;
	
	public TablePanel(PokerGUI gui, Game game) {
		this.gui = gui;
		this.game = game;
		//this.players = game.players;
		
		setLayout(layout);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 15, 15, 15);
        addPlayers();
        System.out.println("table created " + this);
	}
	
	public void setMessages() {
        if (messages.isVisible())
        	messages.setVisible(false);
    }
	
	
	public void addPlayers(){
		//addPlayers(1);
		
		player1Hand = new HandPanel(this, game.players.get(0), true);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
        add(player1Hand, gbc);
        
        player2Hand = new HandPanel(this, game.players.get(1), true);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
        add(player2Hand, gbc);
        
        messages = new JLabel("Select up to 3 cards to change");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        add(messages, gbc);
        
        ChangeButton = new JButton(CHANGE);
        ChangeButton.addActionListener(this);
        ChangeButton.setVisible(false);
        ChangeButton.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        add(ChangeButton, gbc);
        
	}
	
	public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(CHANGE))
        {
        	
        	repaint();
        	player1Hand.repaint();
        	player2Hand.repaint();
        	
        	List<Integer> arr = new ArrayList<Integer>(); // we can’t alter the size of an array. So we need a work around using a List.
        	System.out.println(player1Hand.cards.length);
	        for (int x = 0; x < player1Hand.cards.length;x++){
	        	System.out.println(player1Hand.getCards()[x].getSelected());
	        	if(player1Hand.cards[x].selected){
	        		arr.add(new Integer(x));
	        	}
	        }
	        
	        int[] swaps = new int[arr.size()]; // now initialise our return array to be the same size as our List.
	        
	        for (int z = 0; z < arr.size();z++){
	        	swaps[z] = arr.get(z).intValue(); // add the values
	        	
	        }
	        gui.swapPlayersCards(swaps);
	        
	       
	        player1Hand = new HandPanel(this, game.players.get(0), true);	        
	        player2Hand = new HandPanel(this, game.players.get(1), true);
	        
	        repaint();
	        
	        
        	
        }
    }
	public void changeMessage(String msg){
		
	messages.setText(msg);
	
	}
	//More than one Computer player
	public void addPlayers(int cpuPlayers)
    {
		/*if(cpuPlayers >3){ //Max 4 players (3 computer 1 human)
			cpuPlayers = 3;
		}
		for(int i = 1; i <= cpuPlayers; i++){
			CardButton[] hand = new CardButton[5];
			HandPanel handContainer = new HandPanel(hand);
			for(int c = 0; c < 5; c++)
	        {
	        	handContainer.addCard();
	        }
			
			gbc.gridx = 0;
			gbc.gridy = 0;
	        add(handContainer, gbc);
		}*/
    }
}
