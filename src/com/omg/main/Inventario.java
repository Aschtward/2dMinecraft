package com.omg.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.omg.entities.Workbench;
import itens.Itens;

public class Inventario {
	
	//Normal inventory variables
	private int itensNumbers = 5;
	private int boxSize = 40;
	private int size = boxSize*itensNumbers;
	public int initialPosition = 80;
	@SuppressWarnings("exports")
	public Itens[] items;
	public Workbench wk;
	public boolean expandedInventory = false,wi,wf;
	public int selected = 0;
	public int mx,my,xini,yini;
	public boolean drop = true,dividing = false;
	
	public Inventario() {
		items = new Itens[30];
		wk = new Workbench();

	}
	
	private void changeInventory(Itens[] items,int posIni, int posFinal,boolean wi,boolean wf,boolean dividing) {
		
		Itens inten1;
		Itens inten2;
		
		//Changin only on worktable
		if(wi && wf) {
			if(dividing && wk.items[posIni].getQuantity() >=2 && wk.items[posFinal] == null) {
				int quant =(int) wk.items[posIni].getQuantity()/2;
				wk.items[posFinal] = Game.setIten.managmentItens(wk.items[posIni].getId());
				wk.items[posFinal].setQuantity(quant);
				wk.items[posIni].setQuantity(wk.items[posIni].getQuantity() - quant);
			}else {
				inten1 = wk.items[posIni];
				inten2 = wk.items[posFinal];
				if(inten1 != null && inten2 != null && !dividing) {
					if(inten1.getId() == inten2.getId() && inten2.getQuantity() + inten1.getQuantity() <65) {
						wk.items[posFinal].setQuantity(wk.items[posIni].getQuantity() + wk.items[posFinal].getQuantity());
						wk.items[posIni] = null;
					}
				}else {
					wk.items[posIni] = inten2;
					wk.items[posFinal] = inten1;
				}
			}
		}else if(wi) {
			if(dividing && wk.items[posIni].getQuantity() >= 2 && items[posFinal] == null) {
				int quant =(int) wk.items[posIni].getQuantity()/2;
				items[posFinal] = Game.setIten.managmentItens(wk.items[posIni].getId());
				items[posFinal].setQuantity(quant);
				wk.items[posIni].setQuantity(wk.items[posIni].getQuantity() - quant);
			}else {
				inten1 = wk.items[posIni];
				inten2 = items[posFinal];
				if(inten1 != null && inten2 != null && !dividing) {
					if(inten1.getId() == inten2.getId() && inten2.getQuantity() + inten1.getQuantity() <65) {
						items[posFinal].setQuantity(wk.items[posIni].getQuantity() + items[posFinal].getQuantity());
						wk.items[posIni] = null;
					}
				}else {
					wk.items[posIni] = inten2;
					items[posFinal] = inten1;
				}
			}
		}else if(wf){
			if(dividing && items[posIni].getQuantity() >=2 && wk.items[posFinal] == null) {
				int quant =(int) items[posIni].getQuantity()/2;
				wk.items[posFinal] = Game.setIten.managmentItens(items[posIni].getId());
				wk.items[posFinal].setQuantity(quant);
				items[posIni].setQuantity(items[posIni].getQuantity() - quant);
			}
			inten1 = items[posIni];
			inten2 = wk.items[posFinal];
			if(inten1 != null && inten2 != null && !dividing) {
				if(inten1.getId() == inten2.getId() && inten2.getQuantity() + inten1.getQuantity() <65) {
					wk.items[posFinal].setQuantity(items[posIni].getQuantity() + wk.items[posFinal].getQuantity());
					items[posIni] = null;
				}
			}else {
				items[posIni] = inten2;
				wk.items[posFinal] = inten1;
			}
		}else {
			if(dividing && items[posIni].getQuantity() >= 2 && items[posFinal] == null) {
				int quant =(int) items[posIni].getQuantity()/2;
				items[posFinal] = Game.setIten.managmentItens(items[posIni].getId());
				items[posFinal].setQuantity(quant);
				items[posIni].setQuantity(items[posIni].getQuantity() - quant);
			}else {
				inten1 = items[posIni];
				inten2 = items[posFinal];
				if(inten1 != null && inten2 != null && !dividing) {
					if(inten1.getId() == inten2.getId() && inten2.getQuantity() + inten1.getQuantity() <65) {
						items[posFinal].setQuantity(items[posIni].getQuantity() + items[posFinal].getQuantity());
						items[posIni] = null;
					}
				}else {
					items[posIni] = inten2;
					items[posFinal] = inten1;
				}
			}
		}	
	}
	
	public int getPos(int mx, int my) {
		if(((mx/40))+((my/40))*5 > 0)
			return ((mx/40)-2)+((my/40)-4)*5;
		return -1;
	}
	
	public int getPosWorkbench(int mx, int my) {
		if(((mx/40)-7)+((my/40)-4)*3 > 0)
			return ((mx/40)-8)+((my/40)-4)*3;
		return -1;
	}
	
	public void tick() {
		
		if(xini/40 < 7) {
			if(mx == -1) {
				mx = getPos(xini,yini);
			}else if(my == -1 && drop) {
				my = getPos(xini,yini);
			}
		}else {
			if(mx == -1) {
				mx = getPosWorkbench(xini,yini);
				wi = true;
			}else if(my == -1 && drop) {
				my = getPosWorkbench(xini,yini);
				wf = true;
			}
			
		}
		if(mx != -1 && my != -1) {
			if(mx != my) {
				changeInventory(items,mx,my,wi,wf,dividing);
			}
			wi = false;
			wf = false;
			dividing = false;
			xini = -1;
			yini = -1;
			mx = -1;
			my = -1;
		}
		
	}
	
	
	public void render(@SuppressWarnings("exports") Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(initialPosition, Game.HEIGHT-boxSize, size, boxSize);
		
		for(int i = 0; i < itensNumbers;i++) {
			g.setColor(Color.black);
			g.drawRect(initialPosition + (i*boxSize), Game.HEIGHT-boxSize, boxSize, boxSize);
			if(items[25+i] != null) {
				g.setColor(Color.white);
				g.drawImage(items[i+25].getSprite(), initialPosition + (i*boxSize) + 5, Game.HEIGHT-(boxSize-5), boxSize - 10, boxSize - 10,null);
				g.setFont(new Font("Arial",10,10));
				g.drawString(String.valueOf(items[i+25].getQuantity()), initialPosition + (i*boxSize) + boxSize-10, (Game.HEIGHT));
			}
		}
		
		if(expandedInventory) {
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(initialPosition, Game.HEIGHT-40*6, size, size);
			wk.render(g);
			for(int i = 0; i < itensNumbers;i++) {
				for(int j = 0; j < itensNumbers;j++) {
					g.setColor(Color.black);
					g.drawRect(initialPosition + (i*boxSize), (Game.HEIGHT-40*6)+j*boxSize, boxSize, boxSize);
					if(items[i+(j*5)] != null) {
						g.setColor(Color.white);
						g.drawImage(items[i+(j*5)].getSprite(), initialPosition + (i*boxSize) +5, (Game.HEIGHT-40*6)+j*boxSize +5, boxSize-10, boxSize-10,null);
						g.setFont(new Font("Arial",10,10));
						g.drawString(String.valueOf(items[i+(j*5)].getQuantity()), initialPosition + (i*boxSize) + boxSize-12, (Game.HEIGHT-40*6)+(j*boxSize)+boxSize-5);
					}
				}
			}
		}
	}
	
	public int getSelected() {
		if(items[25+selected] != null && items[25+selected].isWorldPart())
			return items[25+selected].getId();
		return 0;
	}
	
	public void decreasedSelected() {
		 items[25+selected].setQuantity(items[25+selected].getQuantity() - 1);
		 if( items[25+selected].getQuantity() == 0) {
			 items[25+selected] = null;
		 }
	}

}
