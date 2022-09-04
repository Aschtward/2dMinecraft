package itens;

import java.awt.image.BufferedImage;

import com.omg.main.Game;

public class Grass extends Itens {

	public Grass() {
		this.setId(1);
		this.setDescription("Um bloco de grama");
		this.setIntegrity(100);
		this.setName("Grama");
		this.setSprite(Game.itensSprite.get(1));
		this.setWorldPart(true);
	}

}
