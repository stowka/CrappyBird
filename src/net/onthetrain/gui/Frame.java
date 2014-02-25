package net.onthetrain.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import net.onthetrain.game.Game;

public class Frame extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 480;
	
	private Game game = null;
	private Panel panel = null;
	
	public Frame(Game game) {
		this.setGame(game);
		this.panel = new Panel(game);
		
		this.setTitle("Crappy Bird - De Gieter");
		
		this.setResizable(false);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(panel);
		this.addMouseListener(panel);
		
		this.setContentPane(panel);
		
		this.setVisible(true);
	}

	@Override
	public void run() {
		while(game.getBird().isFlying()) {
			panel.repaint();
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
