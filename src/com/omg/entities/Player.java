package com.omg.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.omg.main.Game;
import com.omg.world.Camera;
import com.omg.world.World;

public class Player extends Entity{
	
	//Vari�veis de render
	private BufferedImage playerSpriteRight[], playerSpriteLeft[];
	private int frames = 0, maxFrames = 20,indexAnimation = 0;
	private int speed = 2;
	private int jumpHeight = 20,jumpFrames = 0;
	public int dir = 1,life = 90,beforelife = 3,hungry = 100;
	public int hungryTime;
	public boolean left,right,jump,isJumping;
	public boolean interaction,tookdamage = false;
	private int cont = 0;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		//Carregando sprites do spritesheet do game
		playerSpriteRight = new BufferedImage[3];
		playerSpriteLeft = new BufferedImage[3];
		playerSpriteRight[0] = Game.spritesheet.getSprite(0, 16, 16, 32);
		playerSpriteRight[1] = Game.spritesheet.getSprite(32, 16, 16, 32);
		playerSpriteRight[2] = Game.spritesheet.getSprite(16, 16, 16, 32);
		playerSpriteLeft[0] = Game.spritesheet.getSprite(48, 16, 16, 32);
		playerSpriteLeft[1] = Game.spritesheet.getSprite(80, 16, 16, 32);
		playerSpriteLeft[2] = Game.spritesheet.getSprite(64, 16, 16, 32);
	}
	
	public boolean placeBlock(int id,int x, int y) {
		
		int realX = (x + Camera.x)/World.tile_size;
		int realY = (y + Camera.y)/World.tile_size;
		int distX = Math.abs((this.getX()/World.tile_size) - realX);
		int distY = Math.abs((this.getY()/World.tile_size) - realY);
		
		if(distX < 4 && distY < 4 && id !=0 && (distY - distX) != 0) {
			return Game.world.colocarBlocos(realX, realY,id);
		}
		return false;
	}
	
	public void destroBlock(int x, int y) {
		
		int realX = (x + Camera.x)/World.tile_size;
		int realY = (y + Camera.y)/World.tile_size;
		int distX = Math.abs((this.getX()/World.tile_size) - realX);
		int distY = Math.abs((this.getY()/World.tile_size) - realY);
		
		if(distX < 4 && distY < 4) {
			 Game.world.deletarBlocos(realX, realY);
		}
	}
	
	public void tick() {
		hungryTime++;
		if(hungryTime == 600 && hungry > 0) {
			hungry--;
			hungryTime=0;
		}
		if(hungry == 0 && hungryTime > 300 ) {
			life --;
			hungryTime=0;
		}
		if(jump) {
			if(!World.isFree(getX(), getY()+4)) {
				isJumping = true;
			}else {
				jump = false;
			}
		}
		if(isJumping) {
			if(World.isFree(getX(), getY()-4)) {
				setY(getY()-4);
				jumpFrames++;
				if(jumpFrames == jumpHeight) {
					isJumping = false;
					jump = false;
					jumpFrames = 0;
				}
			}else {
				isJumping = false;
				jump = false;
				jumpFrames = 0;
			}
		}
		if(right && World.isFree(getX()+speed, getY())) {
			//direita
			setX(getX()+speed);
			dir = 1;
		}else if(left && World.isFree(getX()-speed, getY())) {
			//esquerda
			setX(getX()-speed);
			dir = 2;
		}
		if(World.isFree(getX(), getY()+2 + cont/2) && !isJumping) {
			setY(getY()+2 + cont/2);
			cont++;
		}else {
			cont = 0;
		}
		frames++;
		if(frames == maxFrames) {
			frames = 0 ;
			indexAnimation++;
			if(interaction) {
				if(indexAnimation == 3) {
					indexAnimation = 0;
				}
			}else if(indexAnimation > 1) {
				indexAnimation = 0;
			}
		}
		
		Camera.x = Camera.clamp(getX() - (Game.WIDTH/2),0,World.width*World.tile_size - Game.WIDTH);
		Camera.y = Camera.clamp(getY() - (Game.HEIGHT/2),0,World.height*World.tile_size - Game.HEIGHT);
	}
	
	public void render(Graphics g) {
		
		
		if(dir == 1 && right) {
			g.drawImage(playerSpriteRight[indexAnimation], getX() - Camera.x,getY() - Camera.y -32, World.tile_size, World.tile_size+32, null);
		}else if(dir == 2 && left){
			g.drawImage(playerSpriteLeft[indexAnimation], getX() - Camera.x,getY() - Camera.y-32, World.tile_size, World.tile_size+32, null);
		}else if(dir == 1){
			if(interaction && indexAnimation !=2) {
				g.drawImage(playerSpriteRight[2], getX() - Camera.x,getY() - Camera.y -32, World.tile_size, World.tile_size+32, null);
			}else {
				g.drawImage(playerSpriteRight[0], getX() - Camera.x,getY() - Camera.y-32, World.tile_size, World.tile_size+32, null);
			}
		}else {
			if(interaction && indexAnimation !=2) {
				g.drawImage(playerSpriteLeft[2], getX() - Camera.x,getY() - Camera.y-32, World.tile_size, World.tile_size+32, null);
			}else {
				g.drawImage(playerSpriteLeft[1], getX() - Camera.x,getY() - Camera.y-32, World.tile_size, World.tile_size+32, null);
			}
		}
	}
}
