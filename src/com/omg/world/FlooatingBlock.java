package com.omg.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.omg.main.Game;

import itens.Itens;

public class FlooatingBlock {
	
	private BufferedImage sprite;
	private int x,y;
	private int id;
	private int maxY = 0;
	private boolean bouncing = false;
	
	public FlooatingBlock(BufferedImage sprite, int x, int y,int id) {
		super();
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public void tick() {
		if(collided())
			remove(this);
		if(World.isFree(x, y)) {
			y +=4;
		}
	}
	
	public boolean collided() {
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),32,32);
		Rectangle ent = new Rectangle(this.getX(),this.getY(),8,8);
		return player.intersects(ent);
	}
	
	public void remove(FlooatingBlock fb) {
		Game.fBlocks.remove(fb);
		boolean isIn = false;
		for(int i = 0 ; i < Game.inventario.items.length; i++) {
			if(Game.inventario.items[i] != null) {
				if(Game.inventario.items[i].getId() == this.id && Game.inventario.items[i].getQuantity() < 65 ) {
					Game.inventario.items[i].setQuantity(Game.inventario.items[i].getQuantity() + 1);
					isIn = true;
				}
			}
		}
		if(!isIn) {
			for(int i = 0 ; i < Game.inventario.items.length; i++) {
				if(Game.inventario.items[i] == null && !isIn) {
					Game.inventario.items[i] = Game.setIten.managmentItens(fb.getId());
					Game.inventario.items[i].setQuantity(1);
					isIn = true;
				}
			}
		}
	}


	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public int getX() {
		return x;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void render(Graphics g) {
		g.drawImage(sprite,x - Camera.x + 8,y - Camera.y + 8,World.tile_size/2,World.tile_size/2,null);
		
	}

}
