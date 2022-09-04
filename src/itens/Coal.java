package itens;

import com.omg.main.Game;

public class Coal extends Itens{

	public Coal() {
		this.setId(5);
		this.setDescription("Um bloco de carvão");
		this.setIntegrity(100);
		this.setName("carvão");
		this.setSprite(Game.itensSprite.get(5));
		this.setWorldPart(true);
	}
}
