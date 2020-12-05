import TPRoutes.Structures.Matrice;

public class test {
    public static void main(String[] args) {
        int taillematrice = 10; //n noeuds * n noeuds
        float concentration_sousnoeuds = 3; //n sous-noeuds entre 2 noeuds (je l'ai mis en float pour enlever les erreurs mais normalement c'est un int)
        Matrice matrice= new Matrice(taillematrice,concentration_sousnoeuds);
        int i =0;
    }
}
