package dev.dado.elements;

import dev.dado.utils.Functions;

public class Arma {

	public static Arma manoVuota = new Arma(" ", 0);
	public static Arma bastone = new Arma("Bastone", 1);
	public static Arma coltello = new Arma("Coltello", 2);
	public static Arma arco = new Arma("Arco", 3);
	public static Arma spada = new Arma("Spada", 4);
	
	private String nome;
	private int danno;
	
	/**Creazione Armi**/

	public Arma(String nome, int dmg) {
		this.nome = nome;
		this.danno = dmg;
	}
	

	public int getDanno() {
		return danno;
	}

	public String getNome() {
		return nome;
	}
	
	/**Assegnamento random delle armi*/

	public static Arma getRandomWeapon() {
		int scelta = Functions.getRandomNumber(4);
		if(scelta == 1) return bastone;
		else if(scelta == 2) return coltello;
		else if(scelta == 3) return arco;
		else if(scelta == 4) return spada;
		else return manoVuota;
	}
}
