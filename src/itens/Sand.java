package itens;

import com.omg.main.Game;

public class Sand extends Itens{
	
	public Sand() {
		this.setId(3);
		this.setDescription("Um bloco de areia");
		this.setIntegrity(100);
		this.setName("Areia");
		this.setSprite(Game.itensSprite.get(3));
		this.setWorldPart(true);
	}
}
