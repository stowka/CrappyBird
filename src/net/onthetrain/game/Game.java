package net.onthetrain.game;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import net.onthetrain.gui.Frame;

public class Game implements Runnable {
	private int height, width;
	private List<Obstacle> obstacles;
	private Bird bird;

	private long time;
	private int score;

	private static final int WIDTH = 600;
	private static final int HEIGHT = 480;

	public Game() {
		this.height = HEIGHT;
		this.width = WIDTH;
		this.bird = new Bird((height / 2) + 100);
		this.obstacles = new ArrayList<Obstacle>();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(List<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public void addObstacle(Obstacle obstacle) {
		this.obstacles.add(obstacle);
	}

	public void updateObstaclePositions() {
		for (int i = 0; i < obstacles.size(); i += 1) {
			obstacles.get(i).updatePosition();
			if (obstacles.get(i).getPosition() <= 0)
				obstacles.remove(i);
		}
	}

	public boolean checkObstacle() {
		for (Obstacle o : obstacles) {
			if ((o instanceof ObstacleUp
					&& o.getPosition() - o.getWidth() < 120
					&& o.getPosition() > 80 && o.getHeight() >= (getHeight() - bird
					.getHeight()))
					|| (o instanceof ObstacleDown
							&& o.getPosition() - o.getWidth() < 120
							&& o.getPosition() > 80 && o.getHeight() + 88 >= bird
							.getHeight())) {
				return false;
			}
		}
		return true;
	}

	public void lose(long time, String reason) {
		bird.setFlying(false);

		score = (int) (System.currentTimeMillis() - time) / 1000;
		JOptionPane.showMessageDialog(null, "GAME OVER\n" + score
				+ " seconds");
		System.exit(0);
	}

	@Override
	public void run() {
		while (true) {
			time = System.currentTimeMillis();
			int n = 0;
			while (bird.isFlying()) {
				if (++n % ((int) (Math.random() * 50 + 50)) == 0) {
					Obstacle o = Math.random() >= 0.5 ? new ObstacleDown(
							height, width) : new ObstacleUp(height, width);
					addObstacle(o);
					n = 0;
				}

				updateObstaclePositions();

				if (!checkObstacle())
					lose(time, "obstacle");

				bird.fall();

				if (bird.getHeight() <= 88)
					lose(time, "ground");

				if (bird.getHeight() >= height - 20) {
					lose(time, "sky");
				}
				
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		Game game = new Game();
		Frame frame = new Frame(game);
		new Thread(frame).start();
		new Thread(game).start();
	}

	public Bird getBird() {
		return bird;
	}
}
