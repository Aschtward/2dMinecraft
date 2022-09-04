package itens;

import com.omg.main.Game;

public class Diamond extends Itens{
	
	public Diamond() {
		this.setId(4);
		this.setDescription("Um bloco de diamante. "
				+ "/n Refine para obter diamantes");
		this.setIntegrity(100);
		this.setName("Diamante");
		this.setSprite(Game.itensSprite.get(4));
		this.setWorldPart(false);
	}

}
