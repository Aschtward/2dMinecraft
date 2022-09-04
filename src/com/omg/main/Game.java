package com.omg.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.omg.entities.Entity;
import com.omg.entities.Player;
import com.omg.graph.Spritesheet;
import com.omg.graph.UI;
import com.omg.world.FlooatingBlock;
import com.omg.world.World;

import itens.SettedItens;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener{
	
	
	private static final long serialVersionUID = 1227254042505466843L;
	
	///Definindo parametros para janela
	public static JFrame frame;
	public static int WIDTH = 800;
	public static int HEIGHT = 400;
	public static int SCALE = 1;
	
	//SpriteSheet
	public static BufferedImage image;
	public static Spritesheet spritesheet;
	public static Spritesheet itens;
	public static BufferedImage back;
	public static ArrayList<BufferedImage>itensSprite;
	
	//Aux
	public static Random rand;
	
	//Game
	public static UI ui;
	public static List<Entity> entities;
	public static List<FlooatingBlock> fBlocks;
	public static SettedItens setIten;
	public static World world;
	public static Player player;
	public static Inventario inventario;
	private Thread thread;
	private boolean isRunning = true;


	public Game() {
		
		///Cria��o da Janela
		setPreferredSize(new Dimension(SCALE*WIDTH,SCALE*HEIGHT));
		inicia_frame();
		///
		addKeyListener(this);
		addMouseListener(this);
		entities = new ArrayList<Entity>();
		fBlocks = new ArrayList<FlooatingBlock>();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
		try {
			back = ImageIO.read(getClass().getResource("/bgclouds.png"));
		}catch(IOException e) {
			
		}
		
		spritesheet = new Spritesheet("/text.png");
		itens = new Spritesheet("/text.png");
		itensSprite = Spritesheet.createSpriteArray(itens, 9, 0);
		setIten = new SettedItens();
		rand = new Random();
		world = new World("/map1.png");
		player = new Player(32*40,0,32,64,null);
		entities.add(player);
		inventario = new Inventario();
		ui = new UI();
	}
	
	public void inicia_frame() {///Inicializa janela
		frame = new JFrame("2D-CRAFT");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}
		
	public void tick() {
		for(Entity e:entities) {
			e.tick();
		}
		inventario.tick();
		for(int i = 0; i <fBlocks.size();i++) {
			fBlocks.get(i).tick();
		}
		ui.tick();
	}
	
	public void  render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(122,102,255));
		g.fillRect(0, 0,WIDTH,HEIGHT);
		g.drawImage(back,0,0,back.getWidth()*4,back.getHeight()*4,null);
		world.render(g);
		for(Entity e:entities) {
			e.render(g);
		}
		for(int i = 0; i <fBlocks.size();i++) {
			fBlocks.get(i).render(g);
		}
		
		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		inventario.render(g);
		ui.render(g);
		bs.show();
	}
	
	public void run() {
		
		//Implementa��o game looping
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();
		int frames = 0;
		requestFocus();
		while(isRunning) {
			long now = System.nanoTime();
			delta+=(now - lastTime)/ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				frames = 0;
				timer += 1000;
			}
		}//Fim implementa��o game looping
		
		stop();
}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_1) {
			inventario.selected = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_2) {
			inventario.selected = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_3) {
			inventario.selected = 2;
		}
		if(e.getKeyCode() == KeyEvent.VK_4) {
			inventario.selected = 3;
		}
		if(e.getKeyCode() == KeyEvent.VK_5) {
			inventario.selected = 4;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_I) {
			inventario.expandedInventory = !inventario.expandedInventory;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true; //Direita
			player.dir = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;//Esquerda
			player.dir = 2;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.jump = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_E)
			player.interaction = true;
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE && inventario.expandedInventory) {
			inventario.expandedInventory = false;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false; //Direita
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;//Esquerda
		}
		if(e.getKeyCode() == KeyEvent.VK_E)
			player.interaction = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(inventario.expandedInventory) {
				inventario.xini = e.getX();
				inventario.yini= e.getY();
				inventario.drop = false;
				if(e.getButton() == MouseEvent.BUTTON3) {
					inventario.dividing = true;
				}
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			if(player.placeBlock(inventario.getSelected(), e.getX(), e.getY())) {
				inventario.decreasedSelected();
			}
		}
		if(e.getButton() == MouseEvent.BUTTON1) {
			//Deletando blocos
			if(!inventario.expandedInventory) {
				player.destroBlock(e.getX(), e.getY());
				player.interaction = true;
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(inventario.expandedInventory) {
			inventario.xini = e.getX();
			inventario.yini= e.getY();
			inventario.drop = true;
		}else {
			player.interaction = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
