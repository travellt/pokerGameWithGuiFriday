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
		
		gui.processDealerSwap(); //process dealer AI (cards to swap)
		setLayout(layout);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 15, 15, 15);
        addPlayers(false);
        addMessage();
        addChangeButton();
        gui.setGamePosition(1);
        gui.checkWinner(game.getWinner());
        revalidate();
        repaint();
        
	}
	
	private void addMessage(){
		
		messages = new JLabel("Select up to 3 cards to change");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        add(messages, gbc);
		
	}
	private void addChangeButton(){
		ChangeButton = new JButton(CHANGE);
        ChangeButton.addActionListener(this);
        ChangeButton.setVisible(false);
        ChangeButton.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        add(ChangeButton, gbc);
		
	}
	public void addPlayers(boolean showDealer){
		
		player1Hand = new HandPanel(this, game.players.get(0), showDealer); //showDealer in place of true
		gbc.gridx = 0;
		gbc.gridy = 0;
        add(player1Hand, gbc);
        
        player2Hand = new HandPanel(this, game.players.get(1), true);
		gbc.gridx = 0;
		gbc.gridy = 3;
        add(player2Hand, gbc);
        revalidate();
        repaint();
	}
	
	public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals(CHANGE))
        {
        	
        	List<Integer> arr = new ArrayList<Integer>(); // we cant alter the size of an array. So we need a work around using a List.
	        for (int x = 0; x < player2Hand.cards.length;x++){	        	
	        	if(player2Hand.cards[x].selected){
	        		arr.add(new Integer(x));
	        	}
	        }
	        
	        int[] swaps = new int[arr.size()]; // now initialise our return array to be the same size as our List.
	        
	        for (int z = 0; z < arr.size();z++){
	        	swaps[z] = arr.get(z).intValue(); // add the values
	        	
	        }
	        gui.swapPlayersCards(swaps);
	        remove(player1Hand);
	        remove(player2Hand);
	        addPlayers(true);
	        removeButton();
	        gui.setGamePosition(2);
	        player1Hand.setSelectedCount(5);
	        player2Hand.setSelectedCount(5);
	        String winnerMsg = gui.getWinnerMsg(game.getWinner());
	        
	        changeMessage(winnerMsg);
	
        }
    }
	public void changeMessage(String msg){
		messages.setText(msg);
	}
	
	public void removeButton(){
		remove(ChangeButton);
		
	}
	
	public void clearMessages() {
		if (messages.isVisible())
			changeMessage(" ");
	}

	public HandPanel getPlayer2Hand() {
		// TODO Auto-generated method stub
		return player2Hand;
	}

	

	
	
}
