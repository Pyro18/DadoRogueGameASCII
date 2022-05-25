package dev.dado.utils;

import java.util.Random;

import dev.dado.elements.Armatura;
import dev.dado.elements.Mostri;
import dev.dado.Costanti;
import dev.dado.elements.Floor;
import dev.dado.elements.Arma;

public class Functions {


    public static void initMovingTiles() {

        Costanti.monsters.clear();

        for (int y = 0; y < Costanti.floorAttuale.getHeight() - 1; y++) {
            for (int x = 0; x < Costanti.floorAttuale.getWidth() - 1; x++) {
                switch (Costanti.floorAttuale.getTile(x, y)) {
                    case GIOCATORE:
                        Costanti.giocatore.setPos(x, y);
                        break;
                    case MOSTRO:
                        Costanti.monsters.add(new Mostri("Mostri", x, y, 10, 2, 2));
                    default:
                        break;
                }
            }
        }
    }


    public static int getRandomNumber(int n) {
        Random rand = new Random();
        return rand.nextInt(n) + 1;
    }


    public static void handlePlayerMovment(Direzione direction) {

        Assets tile = null;
        switch (direction) {
            case AVATNI:
                tile = Costanti.floorAttuale.getTile(Costanti.giocatore.getX(), Costanti.giocatore.getY() - 1);
                break;
            case SINISTRA:
                tile = Costanti.floorAttuale.getTile(Costanti.giocatore.getX() - 1, Costanti.giocatore.getY());
                break;
            case INDIETRO:
                tile = Costanti.floorAttuale.getTile(Costanti.giocatore.getX(), Costanti.giocatore.getY() + 1);
                break;
            case DESTRA:
                tile = Costanti.floorAttuale.getTile(Costanti.giocatore.getX() + 1, Costanti.giocatore.getY());
                break;
        }


        switch (tile) {
            case VUOTO:
                Costanti.giocatore.move(direction);
                messaggio = " ";
                messaggio2 = " ";
                messaggio3 = " ";
                break;
            case MURO:
                messaggio = "Ti sei imbattuto in un muro!";
                messaggio2 = " ";
                messaggio3 = " ";
                break;
            case SCALE:
                Costanti.giocatore.move(direction);
                Costanti.floorAttuale = new Floor(Functions.getRandomNumber(Costanti.numFloor));
                messaggio = "Sei andato in un nuovo floor!";
                messaggio2 = " ";
                messaggio3 = " ";
                floorsPulito++;
                Functions.initMovingTiles();
                break;
            case TRAPPOLE:
                Costanti.giocatore.move(direction);
                Costanti.giocatore.damage(Functions.getRandomNumber(2));
                messaggio = "Sei caduto in una trappola e hai subito danni!";
                messaggio2 = " ";
                messaggio3 = " ";
                break;
            case POZIONE_VITA:
                messaggio = "Hai trovato una pozione di cura! Vuoi berla?";
                messaggio2 = "   [Y] Si    [N] No";
                messaggio3 = " ";
                decisione = PlayerDecision.BEVI_POZIONE_VITA;
                break;
            case MONETE:
                Costanti.giocatore.move(direction);
                Costanti.giocatore.addGold(Functions.getRandomNumber(3) + 2);
                messaggio = "Hai raccolto una moneta!";
                messaggio2 = " ";
                messaggio3 = " ";
                break;
            case TESORO:
                messaggio = "Hai trovato un tesoro! Vuoi aprirlo per 10 monete?";
                messaggio2 = "   [Y] Si     [N] No";
                messaggio3 = " ";
                decisione = PlayerDecision.APRI_CASSA;
                break;
            case CHIAVE:
                Costanti.giocatore.move(direction);
                Costanti.giocatore.addKey();
                messaggio = "Hai preso una piccola chiave! Quale sarà il suo utilizzo?";
                messaggio2 = " ";
                messaggio3 = " ";
                break;
            case PORTA:
                messaggio = "Questa porta è chiusa... Vuoi usare una chiave per aprirla?";
                messaggio2 = "   [Y] Si     [N] No";
                messaggio3 = " ";
                decisione = PlayerDecision.APRI_PORTA;
                break;
            case MOSTRO:
                messaggio = "Ti sei imbatutto in un mostro!";
                messaggio2 = " ";
                messaggio3 = " ";
                Functions.monsterEncounter(direction);
                break;
            default:
                messaggio = "È successo qualcosa di strano...";
                messaggio2 = " ";
                messaggio3 = " ";
                break;
        }
    }


    public static void makeDecision(boolean yn) {
        if (decisione == PlayerDecision.NESSUNA) {
            return;
        } else if (decisione == PlayerDecision.BEVI_POZIONE_VITA && yn == true) {
            Costanti.giocatore.heal(Functions.getRandomNumber(5) + 3);
            messaggio = "non ne ho bisogno adesso...";
            messaggio2 = " ";
            messaggio3 = " ";
            Costanti.giocatore.move();
        } else if (decisione == PlayerDecision.BEVI_POZIONE_VITA && yn == false) {
            messaggio = "non ne ho bisogno adesso...";
            messaggio2 = " ";
            messaggio3 = " ";
        } else if (decisione == PlayerDecision.APRI_CASSA && yn == true) {
            if (Costanti.giocatore.getGold() >= 10) {
                Costanti.giocatore.takeGold(10);
                messaggio = "Hai aperto la cassa!";
                messaggio2 = " ";
                messaggio3 = " ";


                if (Functions.getRandomNumber(2) == 1) {
                    Costanti.giocatore.equipWeapon(Arma.getRandomWeapon());
                    messaggio2 = Costanti.giocatore.getArma().getNome() + " attrezzata!";
                } else {
                    Costanti.giocatore.equipArmor(Armatura.getRandomArmor());
                    messaggio2 = Costanti.giocatore.getArmatura().getNome() + " attrezzata!";
                }

                Costanti.giocatore.move();

            } else {
                messaggio = "Non hai abbastanza oro...";
                messaggio2 = " ";
                messaggio3 = " ";
            }
        } else if (decisione == PlayerDecision.APRI_CASSA && yn == false) {
            messaggio = "non voglio aprirlo...";
            messaggio2 = " ";
            messaggio3 = " ";
        } else if (decisione == PlayerDecision.APRI_PORTA && yn == true) {
            if (Costanti.giocatore.getKeys() > 0) {
                Costanti.giocatore.takeKey();
                Costanti.giocatore.move();
                messaggio = "Hai aperto la porta!";
                messaggio2 = " ";
                messaggio3 = " ";
            } else {
                messaggio = "non hai la chiave...";
                messaggio2 = " ";
                messaggio3 = " ";
            }
        } else if (decisione == PlayerDecision.APRI_PORTA && yn == false) {
            messaggio = "Sei sicuro che sia una buona idea...? Chissà cosa c'è dietro questa porta.";
            messaggio2 = " ";
            messaggio3 = " ";
        }

        decisione = PlayerDecision.NESSUNA;
    }


    public static void moveMonsters() {
        for (int i = 0; i < Costanti.monsters.size(); i++) {
            Costanti.monsters.get(i).moveRandom();
        }
    }


    public static void monsterEncounter(Direzione direction) {
        int monsterX = 0, monsterY = 0;

        switch (direction) {
            case AVATNI:
                monsterX = Costanti.giocatore.getX();
                monsterY = Costanti.giocatore.getY() - 1;
                break;
            case SINISTRA:
                monsterX = Costanti.giocatore.getX() - 1;
                monsterY = Costanti.giocatore.getY();
                break;
            case INDIETRO:
                monsterX = Costanti.giocatore.getX();
                monsterY = Costanti.giocatore.getY() + 1;
                break;
            case DESTRA:
                monsterX = Costanti.giocatore.getX() + 1;
                monsterY = Costanti.giocatore.getY();
                break;
        }

        for (int i = 0; i < Costanti.monsters.size(); i++) {
            if (Costanti.monsters.get(i).getX() == monsterX && Costanti.monsters.get(i).getY() == monsterY) {
                float playerAttack = Costanti.giocatore.getForza() - (Costanti.monsters.get(i).getDif() / 10) * Costanti.giocatore.getForza();
                float monsterAttck = Costanti.monsters.get(i).getForza() - (Costanti.giocatore.getDif() / 10) * Costanti.monsters.get(i).getForza();
                Costanti.monsters.get(i).damage(playerAttack);
                Costanti.giocatore.damage(monsterAttck);
                messaggio2 = "Hai attaccato il mostro e l'hai lasciato a " + Costanti.monsters.get(i).getHP() + " HP!";
                messaggio3 = "Il mostro ti ha attaccato!";
            }
        }
    }


    public static void checkPlayerDeath() {
        if (Costanti.giocatore.getHP() <= 0) {
            messaggio = "Sei morto!";
            messaggio2 = "Hai pulito il floor " + floorsPulito;
            messaggio3 = "Premi un pulsante qualsiasi per continuare";
            Costanti.giocatore.setMorte();
        }
    }

    private static String messaggio = " ";
    private static String messaggio2 = " ";
    private static String messaggio3 = " ";
    private static int floorsPulito = 0;


    public static String getMessaggio() {
        return messaggio;
    }


    public static String getMessaggio2() {
        return messaggio2;
    }


    public static String getMessaggio3() {
        return messaggio3;
    }

    private static PlayerDecision decisione = PlayerDecision.NESSUNA;

    private enum PlayerDecision {
        NESSUNA,
        BEVI_POZIONE_VITA,
        APRI_CASSA,
        APRI_PORTA;
    }
}
