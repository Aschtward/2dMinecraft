package itens;

import com.omg.main.Game;

public class Stone extends Itens{

	public Stone() {
		this.setId(8);
		this.setDescription("Um bloco de pedra");
		this.setIntegrity(100);
		this.setName("Pedra");
		this.setSprite(Game.itensSprite.get(8));
		this.setWorldPart(true);
	}
}
