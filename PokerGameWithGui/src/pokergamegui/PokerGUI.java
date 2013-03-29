package pokergamegui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
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
	private int[] dealerSwaps;
	private Game game;
	
	/**
	 * Create the frame.
	 */
	public PokerGUI(Game game) {
		
        this.game = game;

		setTitle("Pkr5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		gameStart();
		
		
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
        		//New Game
        		gameStart();
        	}
        });
		
	}
	
	public void gameStart(){
		TablePanel tp = new TablePanel(this, game);
		contentPane.add(tp);
		dealerSwaps = game.players.get(0).evaluateTheHand();
		String winner = game.getWinner().getClass().getName();
		System.out.println(winner.getClass().getName());
	}
	
	
	public void swapPlayersCards(int[] swaps){
		
	
		 game.players.get(1).swapCards(swaps, game.getDeck());
		 game.players.get(0).swapCards(dealerSwaps, game.getDeck());
		
	}
	
	
	
	public void display() {
		   setVisible(true);
	}
	public void destroy() {
		   setVisible(false);
		   dispose();
	}
}
