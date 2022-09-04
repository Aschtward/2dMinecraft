package com.omg.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cube extends Tiles{
	
	private int id;
	private int breakCof;
	private BufferedImage sprite;
	private boolean isDestructible;
	
	public Cube(int x, int y, BufferedImage sprite,int id) {
		super(x, y, sprite);
		this.sprite = sprite;
		this.id = id;
		this.isDestructible =  true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBreakCof() {
		return breakCof;
	}

	public void setBreakCof(int breakCof) {
		this.breakCof = breakCof;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public boolean isDestructible() {
		return isDestructible;
	}

	public void setDestructible(boolean isDestructible) {
		this.isDestructible = isDestructible;
	}

	
	
	

}
