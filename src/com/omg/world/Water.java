package com.omg.world;

import java.awt.image.BufferedImage;

import com.omg.main.Game;

public class Water extends Cube{
	
	private boolean isDestructible;
	
	public Water(int x, int y, BufferedImage sprite,int id) {
		super(x, y, sprite, id);
		this.setDestructible(false);
	}

	public boolean isDestructible() {
		return isDestructible;
	}

	public void setDestructible(boolean isDestructible) {
		this.isDestructible = isDestructible;
	}
	
	

}
