package com.omg.world;

import java.awt.Graphics;

import com.omg.main.Game;

public class World {
	
	public static int width, height;
	public static int tile_size = 32;
	public static Cube[] tiles;
	public static int[][] map;

	public World(String path) {
		width = 500;
		height = 160;
		tiles = new Cube[width*height];
		map = new int[height][width];
		gerar(height,width);
		for(int i = 0; i < width;i++) {
			int rand = Game.rand.nextInt(4)+20;
			for(int j = 0; j < height;j++) {
				if(map[j][i] != 0) {
					tiles[i+j*width] = new Cube(i*32,j*32,Game.itensSprite.get(1),1);
					if(j-1 > 0 && tiles[i+(j-1)*width] != null && tiles[i+(j-1)*width].getId() == 1) {
						tiles[i+j*width] = new Cube(i*32,j*32,Game.itensSprite.get(0),1);
					}
					if(rand < 0) {
						tiles[i+j*width] = new Cube(i*32,j*32,Game.itensSprite.get(8),8);
					}
					rand--;
				}else if(j > 70) {
					tiles[i+j*width] = new Water(i*32,j*32,Game.itensSprite.get(3),3);//Create ocean
				}
				if(j > 90) {
					if(Game.rand.nextInt(100) < 5)
						tiles[i+j*width] = new Cube(i*32,j*32,Game.itensSprite.get(5),5);
				}
				if(j > 95) {
					if(Game.rand.nextInt(300) < 3)
						tiles[i+j*width] = new Cube(i*32,j*32,Game.itensSprite.get(6),6);
				}
				if(j > 130) {
					if(Game.rand.nextInt(500) < 3)
						tiles[i+j*width] = new Cube(i*32,j*32,Game.itensSprite.get(7),7);
				}
				if(j > 140) {
					if(Game.rand.nextInt(600) < 3)
						tiles[i+j*width] = new Cube(i*32,j*32,Game.itensSprite.get(4),4);
				}
			}
		}
	}
	
	public void gerar(int width,int height) {
		for(int x = 0; x <width;x++) {
			for(int y = 0; y < height; y++) {
				map[x][y] = Game.rand.nextInt(2) < 1 ? 0 : 1;
			}
		}
		int size = 0;
		for(int x = 0; x <height;x++) {
			for(int y = 0; y < width; y++) {
				if(map[y][x] == 0) {
					size++;
				}
				 map[y][x] = 1;
			}
			for(int y1 = 0; y1 < size;y1++) {
				map[y1][x] = 0;
			}
			size = 0;
		}
		smoothMap();
		for(int x = 0; x <width;x++) {
			for(int y = 0; y < height; y++) {
				System.out.print(map[x][y]);
			}
			System.out.println();
		}
	}
	public int getNeighboursCellCount(int x, int y)
	{
	    int neighbors = 0;
	    for (int i = -3; i <= 3; i++)
	        for (int j = -3; j <= 3; j++)
	            neighbors += map[i + x][ j + y];
	 
	    neighbors -= map[x][y];
	 
	    return neighbors;
	}
	public void smoothMap()
	{
	    for (int x = 3; x < height - 3; x++)
	    {
	        for (int y = 3; y < width - 3; y++)
	        {
	            int neighbors = getNeighboursCellCount(x, y);
	 
	            if (neighbors > 4)
	                map[x][ y] = 1;
	            else if (neighbors < 4)
	                map[x][ y] = 0;
	        }
	    } 
	}
	
	public boolean colocarBlocos(int x,int y,int id) {
		Cube cube = new Cube(x*World.tile_size,y*World.tile_size,Game.itensSprite.get(id),id);
		if(tiles[x+(y*width)] == null) {
			tiles[x+(y*width)] = cube;
			return true;
		}
		return false;
	}
	
	public void deletarBlocos(int x,int y) {
		if(tiles[x+(y*width)] != null && tiles[x+(y*width)].isDestructible()) {
			FlooatingBlock fb = new FlooatingBlock(tiles[x+(y*width)].getSprite(),x*32,y*32,tiles[x+(y*width)].getId());
			Game.fBlocks.add(fb);
			tiles[x+(y*width)] = null;
		}
	}
	public static boolean isFree(int xnext, int ynext) {
		
		int x1 = xnext / tile_size;
		int y1 = ynext/ tile_size;
		
		int x2 = (xnext+tile_size-1) / tile_size;
		int y2 = ynext/ tile_size;
		
		int x3 = xnext / tile_size;
		int y3 = (ynext+tile_size-1)/ tile_size;
		
		int x4 = (xnext+tile_size-1) / tile_size;
		int y4 = (ynext+tile_size+1)/ tile_size;
		
		int x5 = xnext / tile_size;
		int y5 = (ynext+tile_size+1)/ tile_size;
		
		int x6 = (xnext+tile_size-1) / tile_size;
		int y6 = (ynext+tile_size+1)/ tile_size;
		
		int x7 = xnext / tile_size;
		int y7 = (ynext+tile_size-1)/ tile_size;
		
		int x8 = (xnext+tile_size-1) / tile_size;
		int y8 = (ynext+tile_size-1)/ tile_size;
		
		if(xnext > width* 32 || ynext > height*32 || xnext < 2 || ynext < 2)
			return false;
			
		return !(tiles[x1+(y1*World.width)] instanceof Cube ||
				tiles[x2+(y2*World.width)] instanceof Cube ||
				tiles[x3+(y3*World.width)] instanceof Cube ||
				tiles[x4+(y4*World.width)] instanceof Cube ||
				tiles[x5+(y5*World.width)] instanceof Cube ||
				tiles[x6+(y6*World.width)] instanceof Cube||
				tiles[x7+(y7*World.width)] instanceof Cube ||
				tiles[x8+(y8*World.width)] instanceof Cube);
	}
	public static void worldRestart(String path) {
	}
	
	
	public void render(Graphics g) {
		
		int xstart = Camera.x / tile_size ;
		int ystart = Camera.y / tile_size ;
		int xfinal = xstart  + (Game.WIDTH / tile_size) ;
		int yfinal = ystart + (Game.HEIGHT/ tile_size) ;
		for(int i = xstart; i <= xfinal + 1; i++) {
			for(int j = ystart; j <= yfinal + 1;  j++) {
				if(i < 0 || j < 0 || i >= width|| j >= height) {
					continue;
				}
				Tiles tile = tiles[i+ (j*width)];
				if(tile != null)
					tile.render(g);
			}
		}
	}

}
