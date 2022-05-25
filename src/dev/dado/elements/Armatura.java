package dev.dado.elements;

import dev.dado.utils.Functions;

public class Armatura {

	public static Armatura noArmatura = new Armatura(" ", 0);
	public static Armatura pelle = new Armatura("Armatura in pelle", 1);
	public static Armatura maglia = new Armatura("Armatura in maglia", 2);
	public static Armatura ferro = new Armatura("Armatura in ferro", 3);
	public static Armatura oro = new Armatura("Armatura in oro", 4);
	
	private String nome;
	private int dif;
	

	public Armatura(String name, int dif) {
		this.nome =name; this.dif =dif;
	}
	

	public int getDif() {return dif;}


	public String getNome() {return nome;}
	
	/**Gets random arma*/
	public static Armatura getRandomArmor() {
		int choice = Functions.getRandomNumber(4);
		if(choice == 1) return pelle;
		else if(choice == 2) return maglia;
		else if(choice == 3) return ferro;
		else if(choice == 4) return oro;
		else return noArmatura;
	}
}
