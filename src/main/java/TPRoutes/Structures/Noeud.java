package TPRoutes.Structures;


//Cette classe modélise un noeud/un carrefour
public class Noeud {
    private int x;
    private int y;
    private byte feu;//1=vert 2=orange 3=rouge
    private Sousnoeud haut,bas,gauche,droite;


    //Constructeur
    public Noeud(int x, int y) {
        this.x = x;
        this.y = y;
        feu=1;
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

    public byte getFeu() {
        return feu;
    }

    public void setFeu(byte feu) {
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
}
