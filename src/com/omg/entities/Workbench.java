package com.omg.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.omg.main.Game;

import itens.Itens;

public class Workbench {
	
	private int itensNumbers = 3;
	private int boxSize = 40;
	private int size = boxSize*itensNumbers;
	public int initialPosition = 320;
	public Itens[] items;
	public int selected = 0;
	public int mx,my;
	
	public int xini = 0,xfinal = 0,yini = 0,yfinal = 0;
	
	public Workbench() {
		items = new Itens[9];
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(initialPosition-5, Game.HEIGHT-40*6-5, size+120, size+10);
		for(int i = 0; i < itensNumbers;i++) {
			for(int j = 0; j < itensNumbers;j++) {
				g.setColor(Color.black);
				g.drawRect(initialPosition + (i*boxSize), (Game.HEIGHT-40*6)+j*boxSize, boxSize, boxSize);
				if(items[i+(j*3)] != null) {
					g.setColor(Color.white);
					g.drawImage(items[i+(j*3)].getSprite(), initialPosition + (i*boxSize) +5, (Game.HEIGHT-40*6)+j*boxSize +5, boxSize-10, boxSize-10,null);
					g.setFont(new Font("Arial",10,10));
					g.drawString(String.valueOf(items[i+(j*3)].getQuantity()), initialPosition + (i*boxSize) + boxSize-12, (Game.HEIGHT-40*6)+(j*boxSize)+boxSize-5);
				}
			}
		}
		g.setColor(Color.black);
		g.drawRect(initialPosition + (4*boxSize), (Game.HEIGHT-40*6)+boxSize, boxSize, boxSize);
		
	}
}
