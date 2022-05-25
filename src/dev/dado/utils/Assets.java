package dev.dado.utils;

public enum Assets {

	VUOTO('.'),
	MURO('#'),
	GIOCATORE('A'),
	SCALE('^'),
	TRAPPOLE(','),
	POZIONE_VITA('p'),
	MONETE('G'),
	TESORO('T'),
	CHIAVE('!'),
	PORTA('/'),
	MOSTRO('M');
	
	private char symbol;
	
	Assets(char symbol) {
		this.symbol=symbol;
	}
	
	public char symbol() {return symbol;}
}
