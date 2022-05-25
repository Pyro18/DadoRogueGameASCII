package dev.dado.elements;

import dev.dado.Costanti;
import dev.dado.utils.Direzione;
import dev.dado.utils.Functions;
import dev.dado.utils.Assets;

public class Mostri extends Entity {

	private String name;
	

	public Mostri(String name, int posX, int posY, int health, int strenght, int defence) {
		super(posX, posY, health, strenght, defence);
		this.name=name;
		System.out.println("[Monster]: Creazione Mostri");
	}
	

	public void moveRandom() {
		switch(Functions.getRandomNumber(4)) {
		case 1:
			if(Costanti.floorAttuale.getTile(this.getX(), this.getY()-1) == Assets.VUOTO)
				super.move(Direzione.AVATNI);
			else if(Costanti.floorAttuale.getTile(this.getX(), this.getY()-1) == Assets.GIOCATORE)
				Functions.monsterEncounter(Direzione.AVATNI);
			break;
		case 2:
			if(Costanti.floorAttuale.getTile(this.getX()-1, this.getY()) == Assets.VUOTO)
				super.move(Direzione.SINISTRA);
			else if(Costanti.floorAttuale.getTile(this.getX()-1, this.getY()) == Assets.GIOCATORE)
				Functions.monsterEncounter(Direzione.SINISTRA);
			break;
		case 3:
			if(Costanti.floorAttuale.getTile(this.getX(), this.getY()+1) == Assets.VUOTO)
				super.move(Direzione.INDIETRO);
			else if(Costanti.floorAttuale.getTile(this.getX(), this.getY()+1) == Assets.GIOCATORE)
				Functions.monsterEncounter(Direzione.INDIETRO);
			break;
		case 4:
			if(Costanti.floorAttuale.getTile(this.getX()+1, this.getY()) == Assets.VUOTO)
				super.move(Direzione.DESTRA);
			else if(Costanti.floorAttuale.getTile(this.getX()+1, this.getY()) == Assets.GIOCATORE)
				Functions.monsterEncounter(Direzione.DESTRA);
			break;
		}
	}
	

	public String getName() {return name;}

}
