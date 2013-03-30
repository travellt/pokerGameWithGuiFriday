package pokergamegui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class PokerGUI extends JFrame {

	private JPanel contentPane;
	private int gamePosition = 0;
	
	private int[] dealerswaps;
	private Game game;
	private TablePanel tp;
	PokerGUI gui;
	/**
	 * Create the frame.
	 */
	public PokerGUI(final Game game) {
		
        this.game = game;
        
		setTitle("Pkr5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		//gameStart();
		
		
		JMenuBar menuBar = new JMenuBar();		
		
        setJMenuBar(menuBar); //Make menu into proper full width app menu bar?;
        
        JMenu mnMenu = new JMenu("Menu");
        mnMenu.setMnemonic(KeyEvent.VK_M);
        JMenuItem miNewGame = new JMenuItem("New Game");
        mnMenu.add(miNewGame);
        miNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        mnMenu.addSeparator();
        JMenuItem miQuit = new JMenuItem("Quit");
        mnMenu.add(miQuit);
        miQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        menuBar.add(mnMenu);

        miQuit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		destroy();
        		
        	}
        });
        
        miNewGame.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		game.init();
        		gameStart();
        		pack();
        	}
        });
        
        gameStart();
        
	}
	
	public void gameStart(){
		
		if(tp != null) {			
			contentPane.remove(tp);
			game.newDeck();
		}
		
		tp = new TablePanel(this, game); //not working as expected on new game.
		contentPane.add(tp);
		pack();
		
	}
	
	
	public void processDealerSwap(){
		dealerswaps = game.players.get(0).evaluateTheHand();
	}
	public void swapPlayersCards(int[] swaps){
		
	
		 game.players.get(1).swapCards(swaps, game.getDeck());
		 game.players.get(0).swapCards(dealerswaps, game.getDeck());
		
	}
	
	

	public void checkWinner(String longname){		
		if (longname.equals("dealer"))
			 JOptionPane.showMessageDialog(this, "The Dealer is winning" 
		     ,"Results of round 1", JOptionPane.PLAIN_MESSAGE);
		else if (longname.equals("player"))
			JOptionPane.showMessageDialog(this, "You are winning" 
			     ,"Results of round 1", JOptionPane.PLAIN_MESSAGE);
		else
		JOptionPane.showMessageDialog(this, "Round 1 is a draw" 
			     ,"Results of round 1", JOptionPane.PLAIN_MESSAGE);
		
		return;
		
	}
	
	public String  getWinnerMsg(String longname){		
		String returnString = "";
		if (longname.equals("dealer")){
			 JOptionPane.showMessageDialog(this, "The dealer wins" 
		     ,"Results of round 2", JOptionPane.PLAIN_MESSAGE);
			 returnString = "The dealer won";
		}
		else if (longname.equals("player")){
			JOptionPane.showMessageDialog(this, "Well done, you won!" 
			     ,"Results of round 2", JOptionPane.PLAIN_MESSAGE);
			returnString = "You won!";
		}
		else{
		JOptionPane.showMessageDialog(this, "Game tied" 
			     ,"Results of round 2", JOptionPane.PLAIN_MESSAGE);
			returnString = "Game ended in a draw";
		}
		return returnString;
	}
	
	public int getGamePosition(){
		return gamePosition;
	
	}
	
	public void setGamePosition(int x){
		gamePosition = x;
	}
	
	
	public void display() {
		   setVisible(true);
	}
	public void destroy() {
		   setVisible(false);
		   dispose();
	}
	
}
