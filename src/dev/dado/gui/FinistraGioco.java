package dev.dado.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import dev.dado.utils.Functions;
import dev.dado.Main;
import dev.dado.Costanti;
import dev.dado.utils.Direzione;

public class FinistraGioco extends JPanel implements KeyListener {
	
	public FinistraGioco() {
		addKeyListener(this);
		this.setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint(); revalidate();
		
		//Background colore da decidere (forse nero)
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Costanti.finestraLunghezza, Costanti.finestraAltezza);
		g.setColor(Color.WHITE);
		g.drawRoundRect(5, 5, Costanti.finestraLunghezza -220, Costanti.finestraAltezza -150, 5, 5);
		g.drawRoundRect(790, 5, Costanti.finestraLunghezza -800, Costanti.finestraAltezza -150, 5, 5);
		g.drawRoundRect(5, Costanti.finestraAltezza -140, Costanti.finestraLunghezza -15, Costanti.finestraAltezza -500, 5, 5);
		
		//Floor
		g.setColor(Color.WHITE);
		
		int x, y;
		if(Costanti.floorAttuale.primoFloor()) {
			x=200; y=100;
		}
		else {
			x=15; y=20;
		}
		
		for(int i = 0; i< Costanti.floorAttuale.getHeight(); i++) {
			for(int j = 0; j< Costanti.floorAttuale.getWidth(); j++) {
				g.drawString(""+ Costanti.floorAttuale.getTileChar(j, i), x, y);
				x+=10;
			}
			//y+=15; x=15;
			if(Costanti.floorAttuale.primoFloor()) {x=200; y+=15;}
			else {x=15; y+=15;}
		}
		
		//Statistiche giocatore
		g.setFont(new Font("arial", Font.PLAIN, 30));
		g.drawString("Giocatore", 800, 50);
		g.setFont(new Font("arial", Font.PLAIN, 15));
		g.drawString("VITA: "+ Costanti.giocatore.getHP()+"/"+ Costanti.giocatore.getMaxHP(), 800, 75);
		g.drawString("FORZA: "+ Costanti.giocatore.getForza(), 800, 95);
		g.drawString("DIFESA: "+ Costanti.giocatore.getDif(), 800, 115);
		g.drawString("MONETE: "+ Costanti.giocatore.getGold(), 800, 140);
		g.drawString("CHIAVI: "+ Costanti.giocatore.getKeys(), 800, 160);
		g.drawString("Arma:", 800, 185);
		g.drawString(Costanti.giocatore.getArma().getNome(), 810, 205);
		g.drawString("Armatura:", 800, 230);
		g.drawString(Costanti.giocatore.getArmatura().getNome(), 810, 250);
		
		//Messaggi
		g.drawString(Functions.getMessaggio(), 15, 480);
		g.drawString(Functions.getMessaggio2(), 15, 500);
		g.drawString(Functions.getMessaggio3(), 15, 520);
		
		//Assets
		if(Costanti.floorAttuale.primoFloor()) {
			g.setFont(new Font("arial", Font.PLAIN, 40));
			g.drawString("Dado-RogueGame-ASCII-1.0.4", 200, 70);
			g.setFont(new Font("arial", Font.PLAIN, 15));
			g.drawString("Usa WASD o i tasti freccia per spostarti", 300, 150);
			g.drawString("Cammina verso le scale ^ per iniziare", 300, 170);
			g.drawString(Functions.getMessaggio(), 300, 190);
			
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(Costanti.giocatore.sonoAncoraInVitaForse()) {
			switch(arg0.getKeyCode()) {
			//Giocatore Avanti
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				Costanti.giocatore.setFacing(Direzione.AVATNI);
				Functions.handlePlayerMovment(Direzione.AVATNI);
				Costanti.floorAttuale.updatePlayerPos();
				Functions.moveMonsters();
				Costanti.floorAttuale.updateMonstersPos();
				break;
			//Giocatore Sinistra
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				Costanti.giocatore.setFacing(Direzione.SINISTRA);
				Functions.handlePlayerMovment(Direzione.SINISTRA);
				Costanti.floorAttuale.updatePlayerPos();
				Functions.moveMonsters();
				Costanti.floorAttuale.updateMonstersPos();
				break;
			//Giocatore Indietro
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				Costanti.giocatore.setFacing(Direzione.INDIETRO);
				Functions.handlePlayerMovment(Direzione.INDIETRO);
				Costanti.floorAttuale.updatePlayerPos();
				Functions.moveMonsters();
				Costanti.floorAttuale.updateMonstersPos();
				break;
			//Giocatore Destra
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				Costanti.giocatore.setFacing(Direzione.DESTRA);
				Functions.handlePlayerMovment(Direzione.DESTRA);
				Costanti.floorAttuale.updatePlayerPos();
				Functions.moveMonsters();
				Costanti.floorAttuale.updateMonstersPos();
				break;
			//Decisione SI
			case KeyEvent.VK_Y:
				Functions.makeDecision(true);
				Costanti.floorAttuale.updatePlayerPos();
				break;
			//Decisione NO
			case KeyEvent.VK_N:
				Functions.makeDecision(false);
				Costanti.floorAttuale.updatePlayerPos();
				break;
			}
			Functions.checkPlayerDeath();
		}
		else {
			Main.initGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
