package TPRoutes.Vehicules;

import TPRoutes.Structures.Noeud;
import TPRoutes.Structures.Sousnoeud;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.abs;

public class Voiture{
    private float x,y;
    private float vitesse; // en m/cycle
    private float acceleration,freinage;//Puissance d'accélération et de freinage
    private boolean accident;
    private byte direction;//1=haut 2=droite 3=bas 4=gauche
    private Noeud noeud;
    private Sousnoeud sousnoeud;
    private boolean exit;
    private float vitesse_max; // en m/cycle
    private Rectangle dessinvoiture;
    private float distanceprochainnoeud; //distance parcourue du sous-noeud en m

    //Constructeur

    public Voiture(Sousnoeud sousnoeud, byte direction, float acceleration, float freinage, float vitesse_max) {
        this.x = sousnoeud.getX();
        this.y = sousnoeud.getY();
        this.sousnoeud=sousnoeud;
        this.direction = direction;
        this.acceleration = abs(acceleration);
        this.freinage=abs(freinage);
        vitesse=0;
        accident=false;
        noeud=null;
        this.vitesse_max=vitesse_max;
        this.distanceprochainnoeud=100;
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

    public float getDistanceprochainnoeud() {
        return distanceprochainnoeud;
    }

    public void setDistanceprochainnoeud(float distanceprochainnoeud) {
        this.distanceprochainnoeud = distanceprochainnoeud;
    }

    public float getVitesse_max() {
        return vitesse_max;
    }

    //Méthodes

    public void accelerer(){
        this.vitesse+=acceleration;
    }

    public void freiner(){
        this.vitesse-=freinage;
        if(vitesse<=0) vitesse=0;

    }

    public Rectangle getDessinvoiture() {
        return dessinvoiture;
    }

    public void setDessinvoiture(Rectangle dessinvoiture) {
        this.dessinvoiture = dessinvoiture;
    }
}
