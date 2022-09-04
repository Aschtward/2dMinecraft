package itens;

import com.omg.main.Game;

public class Silver extends Itens{

	public Silver() {
		this.setId(6);
		this.setDescription("Um bloco de ferro");
		this.setIntegrity(100);
		this.setName("Ferro");
		this.setSprite(Game.itensSprite.get(6));
		this.setWorldPart(true);
	}
}
