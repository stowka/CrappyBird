package net.onthetrain.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import net.onthetrain.game.Game;
import net.onthetrain.game.Obstacle;
import net.onthetrain.game.ObstacleUp;

public class Panel extends JPanel implements KeyListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game = null;
	private Image img = null;

	public Panel(Game game) {
		this.game = game;
		try {
			this.img = ImageIO.read(new File("src/img/angry_bird.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp, gp2;

		RenderingHints rh = g2d.getRenderingHints();
		rh.put(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);

		// Sky
		g2d.setColor(new Color(0, 216, 255));
		gp = new GradientPaint(0, 0, new Color(0, 216, 255), getWidth() / 2, 0,
				new Color(0, 196, 235), false);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, getWidth(), getHeight() - 50);

		// Ground
		g2d.setColor(new Color(17, 122, 24));
		g2d.fillRect(0, getHeight() - 50, getWidth(), getHeight());

		g2d.drawImage(img, 80, getHeight() - game.getBird().getHeight(), 40,
				40, this);

		// Obstacles

		// g2d.setColor(new Color(226, 156, 58));
		Color color1 = new Color(226, 156, 58);
		Color color2 = new Color(52, 8, 0);
		for (Obstacle o : game.getObstacles()) {
			if (o instanceof ObstacleUp) {
				gp = new GradientPaint(o.getPosition() - o.getWidth(), 0,
						color1, o.getPosition()
								- (o.getWidth() / 2), 0, color2, false);
				gp2 = new GradientPaint(o.getPosition() - (o.getWidth() / 2),
						0, color2, o.getPosition(), 0, color1, false);
				g2d.setPaint(gp);
				g2d.fillRoundRect(o.getPosition() - o.getWidth(), 0,
						o.getWidth() / 2 + 8, o.getHeight(), 8, 8);
				g2d.setPaint(gp2);
				g2d.fillRoundRect(o.getPosition() - (o.getWidth() / 2), 0,
						o.getWidth() / 2 - 8, o.getHeight(), 8, 8);
			} else {
				gp = new GradientPaint(o.getPosition() - o.getWidth(), 0,
						color1, o.getPosition()
								- (o.getWidth() / 2), 0, color2, false);
				gp2 = new GradientPaint(o.getPosition() - (o.getWidth() / 2),
						0, color2, o.getPosition(), 0, color1, false);
				g2d.setPaint(gp);
				g2d.fillRoundRect(o.getPosition() - o.getWidth(),
						getHeight() - (o.getHeight() + 50), o.getWidth() / 2 + 8,
						o.getHeight(), 8, 8);
				g2d.setPaint(gp2);
				g2d.fillRoundRect(o.getPosition() - (o.getWidth() / 2), getHeight()
						- (o.getHeight() + 50), o.getWidth() / 2 - 8, o.getHeight(), 8, 8);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == 49) {
			game.getBird().jump();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		game.getBird().jump();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
