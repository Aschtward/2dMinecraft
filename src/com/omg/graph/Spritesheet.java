package com.omg.graph;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.omg.main.Game;

public class Spritesheet {
	
	private BufferedImage spritesheet;

	public Spritesheet(String path) {
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
	
	public static ArrayList<BufferedImage> createSpriteArray(Spritesheet sprite,int sizex, int sizey) {
		ArrayList<BufferedImage> bg = new ArrayList<BufferedImage>();
		for(int i = 0; i <sizey + 1;i++) {
			for(int j = 0; j<sizex+1;j++) {
				BufferedImage bi = sprite.getSprite(j*16, i*16, 16, 16);
				bg.add(bi);
			}
		}
		return bg;
	}

}
