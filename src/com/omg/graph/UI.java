package com.omg.graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.omg.main.Game;
import com.omg.world.World;


public class UI {
	
	private BufferedImage heart = Game.spritesheet.getSprite(0,144,16, 16);
	private BufferedImage meat = Game.spritesheet.getSprite(16,144,16, 16);
	private int lifeRender;
	private int meatRender;
	
	public void tick() {
		lifeRender = Game.player.life/10;
		meatRender = Game.player.hungry/10;
	}
	public void render(Graphics g) {
		for(int i = 0 ; i < lifeRender;i++) {
			g.drawImage(heart,(i*20),10,32,32,null);
		}
		for(int i = 0 ; i < meatRender;i++) {
			g.drawImage(meat,(Game.WIDTH-32)-(i*20),10,32,32,null);
		}
	}
}
