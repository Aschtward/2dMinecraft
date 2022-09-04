package itens;

import com.omg.main.Game;
import com.omg.world.Cube;

public class SettedItens {

	public SettedItens() {
	}
	
	public Itens managmentItens(int id) {
		if(id == 1) {
			return new Grass();
		}
		if(id == 2) {
			return new Tree();
		}
		if(id == 3) {
			return new Sand();
		}
		if(id == 4) {
			return new Diamond();
		}
		if(id == 5) {
			return new Coal();
		}
		if(id == 6) {
			return new Silver();
		}
		if(id == 7) {
			return new Gold();
		}
		if(id == 8) {
			return new Stone();
		}
		return null;
	}

}
