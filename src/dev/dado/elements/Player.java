package dev.dado.elements;

import dev.dado.utils.Direzione;

public class Player extends Entity {
	
	private Direzione facing;

	private int gold;
	private int keys;
	
	private Arma armaEquipaggiata;
	private Armatura armaturaEquipaggiata;
	
	private boolean vivo;
	

	public Player(int posX, int posY) {
		super(posX, posY, 20, 1, 1);
		this.gold=0;
		this.armaEquipaggiata = Arma.manoVuota;
		this.armaturaEquipaggiata = Armatura.noArmatura;
		this.vivo =true;
		System.out.println("[Player]: Creazione giocatore");
	}
	
	@Override
	public void move(Direzione dir) {
		super.move(dir);
	}
	

	public void move() {
		this.move(facing);
	}
	

	public void setFacing(Direzione dir) {this.facing=dir;}
	

	public int getGold() {return gold;}
	

	public void addGold(int amount) {gold+=amount;}
	

	public void takeGold(int amount) {gold-=amount;}

	public int getKeys() {return keys;}

	public void addKey() {keys++;}

	public void takeKey() {if(keys>0) keys--;}

	public void equipWeapon(Arma weapon) {
		this.armaEquipaggiata = weapon;
		this.forza =1;
		this.forza +=this.armaEquipaggiata.getDanno();
	}

	public void equipArmor(Armatura armor) {
		this.armaturaEquipaggiata = armor;
		this.difesa =1;
		this.difesa +=this.armaturaEquipaggiata.getDif();
	}
	

	public Arma getArma() {return armaEquipaggiata;}

	public Armatura getArmatura() {return armaturaEquipaggiata;}
	

	public boolean sonoAncoraInVitaForse() {return vivo;}

	public void setMorte() {this.vivo =false;}
}