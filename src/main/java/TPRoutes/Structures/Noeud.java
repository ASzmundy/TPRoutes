package TPRoutes.Structures;


import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

//Cette classe modélise un noeud/un carrefour
public class Noeud {
    private int x;
    private int y;
    private boolean feu;//true = haut bas en vert ; false = gauche droite en vert
    private ArrayList<Circle> dessinfeux;
    private Sousnoeud haut,bas,gauche,droite;


    //Constructeur
    public Noeud(int x, int y) {
        Random rd = new Random();
        this.x = x;
        this.y = y;
        feu=rd.nextBoolean();
        haut=null;
        bas=null;
        gauche=null;
        droite=null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isFeu() {
        return feu;
    }

    public void setFeu(boolean feu) {
        this.feu = feu;
    }

    public Sousnoeud getHaut() {
        return haut;
    }

    public void setHaut(Sousnoeud haut) {
        this.haut = haut;
    }

    public Sousnoeud getBas() {
        return bas;
    }

    public void setBas(Sousnoeud bas) {
        this.bas = bas;
    }

    public Sousnoeud getGauche() {
        return gauche;
    }

    public void setGauche(Sousnoeud gauche) {
        this.gauche = gauche;
    }

    public Sousnoeud getDroite() {
        return droite;
    }

    public void setDroite(Sousnoeud droite) {
        this.droite = droite;
    }

    public ArrayList<Circle> getDessinfeux() {
        return dessinfeux;
    }

    public void setDessinfeux(ArrayList<Circle> dessinfeux) {
        this.dessinfeux = dessinfeux;
    }
}
