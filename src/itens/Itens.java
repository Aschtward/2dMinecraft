package itens;

import java.awt.image.BufferedImage;

public class Itens {
	
	private int id;
	private String description;
	private boolean isWorldPart;
	private String name;
	private int integrity;
	private int quantity;
	private BufferedImage sprite;


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isWorldPart() {
		return isWorldPart;
	}

	public void setWorldPart(boolean isWorldPart) {
		this.isWorldPart = isWorldPart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIntegrity() {
		return integrity;
	}

	public void setIntegrity(int integrity) {
		this.integrity = integrity;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	

}
