package core;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class GameWindow extends JFrame implements ActionListener, WindowListener {
	
	static int WINDOW_HEIGHT = 900;
	static int WINDOW_WIDTH = 1600;
	static int DEFAULT_CELL_SIZE = 50;
	int cellSize;
	
	Game game;
	Board board;
	
	GameArea gameArea;
	JButton newGridButton, nextGenButton;
	JPanel mainPanel, sidePanel;
	JScrollPane scrollPane; 
	JTextField newWidth, newHeight;
	
	public GameWindow(Game game) {
		super("Conway's Game of Life");
		
		this.game = game;
		this.board = game.getBoard();
		this.cellSize = DEFAULT_CELL_SIZE;
		
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 0;
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		this.add(mainPanel, c);
		
		scrollPane = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scrollPane, c);
	    
		initializeGameArea();

		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.weightx = 0;
		sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(200, 100));
		sidePanel.setLayout(new GridBagLayout());
		this.add(sidePanel, c);
		
		GridBagConstraints sideC = new GridBagConstraints();
		sideC.fill = GridBagConstraints.CENTER;
		sideC.gridx = 0;
		sideC.gridy = 0;
		sideC.gridwidth = 2;
		
		newGridButton = new JButton("New Grid");
		newGridButton.addActionListener(this);
		sidePanel.add(newGridButton, sideC);
		
		sideC.gridx = 0;
		sideC.gridy = 1;
		sideC.gridwidth = 1;
		
		JLabel widthLabel = new JLabel("Width: ");
		sidePanel.add(widthLabel, sideC);
		
		sideC.gridx = 1;
		sideC.gridy = 1;
		sideC.gridwidth = 1;
		
		newWidth = new JTextField(Integer.toString(board.getWidth()) ,5);
		sidePanel.add(newWidth, sideC);
		
		sideC.gridx = 0;
		sideC.gridy = 2;
		sideC.gridwidth = 1;
		
		JLabel heightLabel = new JLabel("Height: ");
		sidePanel.add(heightLabel, sideC);
		
		sideC.gridx = 1;
		sideC.gridy = 2;
		sideC.gridwidth = 1;
		
		newHeight = new JTextField(Integer.toString(board.getHeight()), 5);
		sidePanel.add(newHeight, sideC);
		
		sideC.gridx = 0;
		sideC.gridy = 3;
		sideC.gridwidth = 2;
		
		nextGenButton = new JButton("Next Generation");
		nextGenButton.addActionListener(this);
		sidePanel.add(nextGenButton, sideC);
		
		addWindowListener(this);
		this.setVisible(true);
	}
	
	private boolean isInteger(String string) {
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) < 48 || string.charAt(i) > 57) {
				return false;
			}
		}
		
		return true;
	}
	
	
	// (Re-)Creates the game area. Called initially and when pressing the "New Grid" button
	private void initializeGameArea() {
		
		mainPanel.removeAll();
		
		GridBagConstraints mainC = new GridBagConstraints();
		mainC.fill = GridBagConstraints.BOTH;
		
		this.cellSize = (int) Math.min(Math.min((mainPanel.getMaximumSize().height - 20) / board.getHeight(), (mainPanel.getMaximumSize().width - 20) / board.getWidth()), DEFAULT_CELL_SIZE);

		if(gameArea != null) {
			gameArea.setVisible(false);
		}	
		gameArea = new GameArea(board, cellSize);
		gameArea.setPreferredSize(new Dimension(board.getWidth() * gameArea.getCellSize(), board.getHeight() * gameArea.getCellSize()));
		mainPanel.add(gameArea, mainC);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nextGenButton){
			
			game.nextGeneration();
			gameArea.repaint();
			
		} else if (e.getSource() == newGridButton) {
			
			if(isInteger(newWidth.getText()) && isInteger(newHeight.getText())) {
				
				board = new Board(Integer.parseInt(newHeight.getText()), Integer.parseInt(newWidth.getText()), null);
				game.setBoard(board);
				initializeGameArea();
				mainPanel.repaint();
				gameArea.revalidate();
				//gameArea.repaint();
			}
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
