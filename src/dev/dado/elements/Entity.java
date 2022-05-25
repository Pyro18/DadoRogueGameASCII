package dev.dado.elements;

import dev.dado.utils.Direzione;

public class Entity {

    protected int posX;
    protected int posY;

    protected float vita;
    protected int vitaMax;

    protected int forza;
    protected int difesa;


    protected Entity(int posX, int posY, int vita, int forza, int difesa) {
        this.setPos(posX, posY);
        this.vita = vita;
        this.vitaMax = vita;
        this.forza = forza;
        this.difesa = difesa;
    }

    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getX() {
        return posX;
    }


    public int getY() {
        return posY;
    }


    public float getHP() {
        return vita;
    }


    public int getMaxHP() {
        return vitaMax;
    }


    public int getForza() {
        return forza;
    }


    public int getDif() {
        return difesa;
    }


    public void damage(float quantita) {
        this.vita -= quantita;
    }


    public void heal(int quantita) {
        this.vita += quantita;
        if (vita > vitaMax) vita = vitaMax;
    }

    protected void move(Direzione dir) {
        switch (dir) {
            case AVATNI:
                this.posY--;
                break;
            case SINISTRA:
                this.posX--;
                break;
            case INDIETRO:
                this.posY++;
                break;
            case DESTRA:
                this.posX++;
                break;
        }
    }
}
