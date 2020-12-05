package TPRoutes.Structures;

import java.util.ArrayList;

public class Matrice {

    private final Noeud[][] matrice;

    public Matrice(int taillematrice, float concentration_sousnoeuds) {
        this.matrice = new Noeud[taillematrice][taillematrice];
        Noeud nouveau, noeudhaut, noeudgauche,noeudbas,noeuddroite;
        Sousnoeud nouveausousnoeud, anciensousnoeud;
        int i, j, k;


        //Création des noeuds et sous-noeuds
        for (i = 0; i < taillematrice; i++) {
            for (j = 0; j < taillematrice; j++) {

                if (i != 0) noeudhaut = matrice[i-1][j];
                else noeudhaut = null;
                if(j != 0) noeudgauche= matrice[i][j-1];
                else noeudgauche=null;

                matrice[i][j] = new Noeud(j + 1, i + 1);
                nouveau = matrice[i][j];

                //Création des sous noeuds d'en haut
                nouveausousnoeud = new Sousnoeud(nouveau.getX(), nouveau.getY() - (1 / (concentration_sousnoeuds + 1)), nouveau);
                nouveau.setHaut(nouveausousnoeud);
                for (k = 2; k <= concentration_sousnoeuds; k++) {
                    anciensousnoeud = nouveausousnoeud;
                    nouveausousnoeud = new Sousnoeud(nouveau.getX(), nouveau.getY() - (k / (concentration_sousnoeuds + 1)), anciensousnoeud);
                    anciensousnoeud.setSousnoeud2(nouveausousnoeud);
                }
                if (noeudhaut != null) {
                    nouveausousnoeud.setNoeud(noeudhaut);
                    noeudhaut.setBas(nouveausousnoeud);
                }

                //Création des sous-noeuds à gauche
                nouveausousnoeud = new Sousnoeud(nouveau.getX() - (1 / (concentration_sousnoeuds + 1)), nouveau.getY(), nouveau);
                nouveau.setGauche(nouveausousnoeud);
                for (k = 2; k <= concentration_sousnoeuds; k++) {
                    anciensousnoeud = nouveausousnoeud;
                    nouveausousnoeud = new Sousnoeud(nouveau.getX() - (k / (concentration_sousnoeuds + 1)), nouveau.getY(), anciensousnoeud);
                    anciensousnoeud.setSousnoeud2(nouveausousnoeud);
                }
                if(noeudgauche!=null){
                    nouveausousnoeud.setNoeud(noeudgauche);
                    noeudgauche.setDroite(nouveausousnoeud);
                }
            }//Fin du for j
            //Création des sous-noeuds à droite (limite)
            nouveau=matrice[i][taillematrice-1];
            nouveausousnoeud = new Sousnoeud(nouveau.getX() + (1 / (concentration_sousnoeuds + 1)), nouveau.getY(), nouveau);
            nouveau.setDroite(nouveausousnoeud);
            for (k = 2; k <= concentration_sousnoeuds; k++) {
                anciensousnoeud = nouveausousnoeud;
                nouveausousnoeud = new Sousnoeud(nouveau.getX() + (k / (concentration_sousnoeuds + 1)), nouveau.getY(), anciensousnoeud);
                anciensousnoeud.setSousnoeud2(nouveausousnoeud);
            }
        }//fin du for i
        //Ajout des sous noeuds en bas (limite)
        for(i=0;i<taillematrice;i++){
            Noeud actuel = matrice[taillematrice-1][i];
            nouveausousnoeud = new Sousnoeud( actuel.getX(), actuel.getY()+(1 / (concentration_sousnoeuds + 1)),actuel);
            actuel.setBas(nouveausousnoeud);
            for (k = 2; k <= concentration_sousnoeuds; k++) {
                anciensousnoeud = nouveausousnoeud;
                nouveausousnoeud = new Sousnoeud(actuel.getX(), actuel.getY() + (k / (concentration_sousnoeuds + 1)), anciensousnoeud);
                anciensousnoeud.setSousnoeud2(nouveausousnoeud);
            }
        }
    }

    public Noeud[][] getMatrice() {
        return matrice;
    }
}
