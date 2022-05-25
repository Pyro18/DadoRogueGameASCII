package dev.dado.elements;


import java.util.ArrayList;

import dev.dado.Costanti;
import dev.dado.utils.Assets;
import dev.dado.utils.ResponsabileRisorse;

public class Floor {

    private ArrayList<ArrayList<Assets>> assets;
    private boolean primoFloor;


    public Floor(int floorNumber) {
        System.out.println("[Floor]: Crazione floor n^" + floorNumber);

        assets = new ArrayList<ArrayList<Assets>>();

        ArrayList<String> strs = ResponsabileRisorse.readFloorFile("./src/assets/floors/floor" + floorNumber + ".txt");

        for (int i = 0; i < strs.size() - 1; i++) {
            char[] charray = strs.get(i).toCharArray();
            assets.add(new ArrayList<Assets>());
            for (int j = 0; j < charray.length; j++) {
                switch (charray[j]) {
                    case '.':
                        assets.get(i).add(Assets.VUOTO);
                        break;
                    case '#':
                        assets.get(i).add(Assets.MURO);
                        break;
                    case 'A':
                        assets.get(i).add(Assets.GIOCATORE);
                        break;
                    case '^':
                        assets.get(i).add(Assets.SCALE);
                        break;
                    case ',':
                        assets.get(i).add(Assets.TRAPPOLE);
                        break;
                    case 'p':
                        assets.get(i).add(Assets.POZIONE_VITA);
                        break;
                    case 'G':
                        assets.get(i).add(Assets.MONETE);
                        break;
                    case 'T':
                        assets.get(i).add(Assets.TESORO);
                        break;
                    case '!':
                        assets.get(i).add(Assets.CHIAVE);
                        break;
                    case '/':
                        assets.get(i).add(Assets.PORTA);
                        break;
                    case 'M':
                        assets.get(i).add(Assets.MOSTRO);
                        break;
                }
            }
        }

        if (floorNumber == 0) primoFloor = true;
        else primoFloor = false;
    }

    public int getHeight() {
        return assets.size();
    }


    public int getWidth() {
        return assets.get(0).size();
    }


    public Assets getTile(int x, int y) {
        return assets.get(y).get(x);
    }

    public char getTileChar(int x, int y) {
        return assets.get(y).get(x).symbol();
    }


    public void updatePlayerPos() {

        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (assets.get(i).get(j) == Assets.GIOCATORE)
                    assets.get(i).set(j, Assets.VUOTO);
            }
        }

        assets.get(Costanti.giocatore.getY()).set(Costanti.giocatore.getX(), Assets.GIOCATORE);
    }


    public void updateMonstersPos() {

        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (assets.get(i).get(j) == Assets.MOSTRO)
                    assets.get(i).set(j, Assets.VUOTO);
            }
        }

        for (int i = 0; i < Costanti.monsters.size(); i++) {
            if (Costanti.monsters.get(i).getHP() <= 0)
                Costanti.monsters.remove(i);
            else
                assets.get(Costanti.monsters.get(i).getY()).set(Costanti.monsters.get(i).getX(), Assets.MOSTRO);
        }

    }


    public boolean primoFloor() {
        return primoFloor;
    }
}
