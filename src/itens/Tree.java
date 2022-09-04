package itens;

import com.omg.main.Game;

public class Tree extends Itens{

	public Tree() {
		this.setId(2);
		this.setDescription("Um bloco de madeira");
		this.setIntegrity(100);
		this.setName("Madeira");
		this.setSprite(Game.itensSprite.get(2));
		this.setWorldPart(true);
	}
}
