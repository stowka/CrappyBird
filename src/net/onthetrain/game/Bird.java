package net.onthetrain.game;

public class Bird {
	private int height;
	private boolean flying;
	
	public Bird(int height) {
		this.height = height;
		this.flying = true;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void fall() {
		this.height -= 1;
	}
	
	public void jump() {
		this.height += 40;
	}

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}
}
