package TPRoutes.Structures;

public class Sousnoeud{
    private float x,y;
    private Noeud noeud;
    private Sousnoeud sousnoeud1,sousnoeud2;

    public Sousnoeud(float x, float y, Noeud noeud) {
        this.x = x;
        this.y = y;
        this.noeud = noeud;
    }

    public Sousnoeud(float x, float y, Sousnoeud sousnoeud1) {
        this.x = x;
        this.y = y;
        this.sousnoeud1 = sousnoeud1;
        sousnoeud1.setSousnoeud2(this);
    }

    public Noeud getNoeud() {
        return noeud;
    }

    public void setNoeud(Noeud noeud) {
        this.noeud = noeud;
    }

    public Sousnoeud getSousnoeud1() {
        return sousnoeud1;
    }

    public void setSousnoeud1(Sousnoeud sousnoeud1) {
        this.sousnoeud1 = sousnoeud1;
    }

    public Sousnoeud getSousnoeud2() {
        return sousnoeud2;
    }

    public void setSousnoeud2(Sousnoeud sousnoeud2) {
        this.sousnoeud2 = sousnoeud2;
    }

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
}
