package dev.dado;

import java.util.ArrayList;

import javax.swing.JFrame;

import dev.dado.elements.Floor;
import dev.dado.elements.Mostri;
import dev.dado.elements.Player;
import dev.dado.gui.FinistraGioco;
import dev.dado.utils.Functions;

public class Main {

	private static JFrame window;
	private static FinistraGioco gameBoard;
	
	public static void main(String[] args) {
		
		System.out.println("[Main]: Starting...");
		createWindow();
		createGameBoard();
		initGame();
	}
	
	private static void createWindow() {
		System.out.println("[Main]: Creazione Finestra di Gioco");
		window = new JFrame("Dado-RogueGame-ASCII-1.0.4");
		window.setVisible(true);
		window.setResizable(false);
		window.setBounds(200, 80, Costanti.finestraLunghezza, Costanti.finestraAltezza);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static void createGameBoard() {
		System.out.println("[Main]: Creazione della GameBoard");
		gameBoard = new FinistraGioco();
		window.add(gameBoard);
		gameBoard.requestFocusInWindow();
	}

	public static void initGame() {
		//funzione rigioca
		Costanti.floorAttuale = new Floor(0);
		Costanti.giocatore = new Player(3, 2);
		Costanti.monsters = new ArrayList<Mostri>();
		
		Functions.initMovingTiles();
	}

}
