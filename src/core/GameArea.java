package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GameArea extends JPanel implements MouseListener {
	
	Board board;
	
	int cellSize;
	
	public GameArea(Board board, int cellSize) {
		super();
		this.board = board;
		this.cellSize = cellSize;
		
		setBackground(Color.DARK_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i<board.getHeight(); i++) {
			for(int j=0; j<board.getWidth(); j++) {
				
				if(board.getGrid()[i][j]) {
					g.setColor(Color.GREEN);
				} else {
					g.setColor(Color.GRAY);
				}
				
				g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
				
				
				g.setColor(Color.BLACK);
				g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
			}
		}
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Board getBoard() {
		return board;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {

		int cellX = e.getX() / cellSize;
		int cellY = e.getY() / cellSize;
		
		board.getGrid()[cellY][cellX] = !board.getGrid()[cellY][cellX];
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public int getCellSize() {
		return cellSize;
	}
	
	public void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}
}
