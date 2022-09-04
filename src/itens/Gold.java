package itens;

import com.omg.main.Game;

public class Gold extends Itens{

	public Gold() {
		this.setId(7);
		this.setDescription("Um bloco de ouro");
		this.setIntegrity(100);
		this.setName("Ouro");
		this.setSprite(Game.itensSprite.get(7));
		this.setWorldPart(true);
	}
}
