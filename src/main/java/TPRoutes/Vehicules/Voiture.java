package TPRoutes.Vehicules;

import TPRoutes.Structures.Noeud;
import TPRoutes.Structures.Sousnoeud;

import static java.lang.Math.abs;

public class Voiture implements Runnable{
    private float x,y;
    private float vitesse;
    private float acceleration,freinage;//Puissance d'accélération et de freinage
    private boolean accident;
    private byte direction;//1=haut 2=droite 3=bas 4=gauche
    private boolean dansnoeud;
    private Noeud noeud;
    private Sousnoeud sousnoeud;
    private boolean exit;
    private int vitesse_max;

    //Constructeur

    public Voiture(Noeud noeud, byte direction, float acceleration, float freinage, int vitesse_max) {
        this.x = noeud.getX();
        this.y = noeud.getY();
        this.noeud=noeud;
        this.direction = (byte)((direction%5)+1);
        this.acceleration = abs(acceleration);
        this.freinage=abs(freinage);
        vitesse=0;
        accident=false;
        dansnoeud=true;
        sousnoeud=null;
        this.vitesse_max=vitesse_max;
    }

    //Getter setter
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVitesse() {
        return vitesse;
    }

    public void setVitesse(float vitesse) {
        this.vitesse = vitesse;
    }

    public boolean isAccident() {
        return accident;
    }

    public void setAccident(boolean accident) {
        this.accident = accident;
    }

    public byte getDirection() {
        return direction;
    }

    public void setDirection(byte direction) {
        this.direction = direction;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getFreinage() {
        return freinage;
    }

    public void setFreinage(float freinage) {
        this.freinage = freinage;
    }

    public boolean isDansnoeud() {
        return dansnoeud;
    }

    public void setDansnoeud(boolean dansnoeud) {
        this.dansnoeud = dansnoeud;
    }

    public Noeud getNoeud() {
        return noeud;
    }

    public void setNoeud(Noeud noeud) {
        this.noeud = noeud;
    }

    public Sousnoeud getSousnoeud() {
        return sousnoeud;
    }

    public void setSousnoeud(Sousnoeud sousnoeud) {
        this.sousnoeud = sousnoeud;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
    //Méthodes

    public void accelerer(){

        this.vitesse+=acceleration;
    }

    public void arreter(){
        while(this.vitesse>0){
            freiner();
        }
    }

    public void freiner(){
        this.vitesse-=freinage;
        if(vitesse<=0) vitesse=0;

    }

    //Méthode run

    public void run() {
        while (!exit) {
            accelerer();
            switch (direction) {
                case 1:
                    if (isDansnoeud()) { //Si il est dans un carrefour
                        if (noeud.getHaut() != null) {
                            sousnoeud = noeud.getHaut();
                            noeud = null;
                            dansnoeud = false;
                        }else exit=true; //si il atteint la fin de la matrice, se détruit
                    }else {

                    }
                    break;
                case 2:
                    if (isDansnoeud()) {

                    }
                    break;
                case 3:
                    if (isDansnoeud()) {

                    }
                    break;
                case 4:
                    if (isDansnoeud()) {

                    }
                    break;

            }
        }
    }
}
