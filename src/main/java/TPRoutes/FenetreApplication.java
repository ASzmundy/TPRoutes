package TPRoutes;

import TPRoutes.Exceptions.ExceptionSensIncorrect;
import TPRoutes.Structures.Matrice;
import TPRoutes.Structures.Noeud;
import TPRoutes.Structures.Sousnoeud;
import TPRoutes.Vehicules.Voiture;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;


public class FenetreApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws ExceptionSensIncorrect {
        //ON SUPPOSE QU'IL Y A 100m ENTRE CHAQUE NOEUD
        //CHOIX DES VARIABLES
        int taillematrice = 10; //n noeuds * n noeuds
        float concentration_sousnoeuds = 3; //n sous-noeuds entre 2 noeuds (je l'ai mis en float pour enlever les erreurs mais normalement c'est un int)
        int zoom=50; //Zoom
        int nbvoituresmax=10; //Nombre max de voitures max présentes sur la map
        float acceleration=5; //Nb de m/cycle gagné en accélérant
        float freinage=7; //Nb de m/cycle
        float vitessemax=14; //Vitesse max des véhicules


        Matrice matrice= new Matrice(taillematrice,concentration_sousnoeuds);
        Group root = new Group();
        Scene scene = new Scene(root,800,600, Color.BLACK);
        primaryStage.setTitle("Vroom vroom");
        primaryStage.setScene(scene);
        ArrayList<Voiture> voitures = new ArrayList<>();
        ArrayList<Rectangle> voituredessins = new ArrayList<>();


        //Dessin Matrice
        for(int i=0;i<taillematrice;i++){
            for(int j=0;j<taillematrice;j++){
                Noeud noeud=matrice.getMatrice()[i][j];
                Rectangle recnode = new Rectangle(noeud.getX()*zoom,noeud.getY()*zoom,zoom/5,zoom/5);
                recnode.setFill(Color.BLUE);
                root.getChildren().add(recnode);
                // ajout des sous noeuds entre les carrefours
                Rectangle between;
                // à gauche
                Sousnoeud sousnoeud=noeud.getGauche();
                between = new Rectangle(sousnoeud.getX()*zoom,sousnoeud.getY()*zoom,recnode.getWidth()+recnode.getWidth()/concentration_sousnoeuds,recnode.getHeight());
                between.setFill(Color.GRAY);
                root.getChildren().add(between);
                for(int k=1;k<concentration_sousnoeuds;k++) {
                    sousnoeud=sousnoeud.getSousnoeud2();
                    between = new Rectangle(sousnoeud.getX()*zoom,sousnoeud.getY()*zoom,recnode.getWidth()+recnode.getWidth()/concentration_sousnoeuds,recnode.getHeight());
                    between.setFill(Color.GRAY);
                    root.getChildren().add(between);
                }
                // en haut
                sousnoeud=noeud.getHaut();
                between = new Rectangle(sousnoeud.getX()*zoom,sousnoeud.getY()*zoom,recnode.getWidth(),recnode.getHeight()+recnode.getHeight()/concentration_sousnoeuds);
                between.setFill(Color.GRAY);
                root.getChildren().add(between);
                for(int k=1;k<concentration_sousnoeuds;k++) {
                    sousnoeud=sousnoeud.getSousnoeud2();
                    between = new Rectangle(sousnoeud.getX()*zoom,sousnoeud.getY()*zoom,recnode.getWidth(),recnode.getHeight()+recnode.getHeight()/concentration_sousnoeuds);
                    between.setFill(Color.GRAY);
                    root.getChildren().add(between);
                }
            }//Fin du for j
            // à droite (limite)
            Noeud noeud=matrice.getMatrice()[i][taillematrice-1];
            Sousnoeud sousnoeud=noeud.getDroite();
            Rectangle between = new Rectangle(sousnoeud.getX()*zoom,sousnoeud.getY()*zoom,zoom/5+zoom/5/concentration_sousnoeuds,zoom/5);
            between.setFill(Color.GRAY);
            root.getChildren().add(between);
            for(int k=1;k<concentration_sousnoeuds;k++) {
                sousnoeud=sousnoeud.getSousnoeud2();
                between = new Rectangle(sousnoeud.getX()*zoom,sousnoeud.getY()*zoom,zoom/5+zoom/5/concentration_sousnoeuds,zoom/5);
                between.setFill(Color.GRAY);
                root.getChildren().add(between);
            }
        }//Fin du for i
        for(int i=0;i<taillematrice;i++){
            Noeud noeud=matrice.getMatrice()[taillematrice-1][i];
            Sousnoeud sousnoeud=noeud.getBas();
            Rectangle between = new Rectangle(sousnoeud.getX()*zoom,sousnoeud.getY()*zoom,zoom/5,zoom/5+zoom/5/concentration_sousnoeuds);
            between.setFill(Color.GRAY);
            root.getChildren().add(between);
            for(int k=1;k<concentration_sousnoeuds;k++) {
                sousnoeud=sousnoeud.getSousnoeud2();
                between = new Rectangle(sousnoeud.getX()*zoom,sousnoeud.getY()*zoom,zoom/5,zoom/5+zoom/5/concentration_sousnoeuds);
                between.setFill(Color.GRAY);
                root.getChildren().add(between);
            }
        }

        //Spawn des voitures
        int nbvoitures=0;

        while(nbvoitures<nbvoituresmax){ //Tant qu'il y a pas assez de voitures
            int x,y; //point de spawn
            int senshasardint; //version int sens
            byte senshasard; //sens du véhicule casté en byte
            Noeud noeudchoisi;

            Random r = new Random();
            senshasardint=r.nextInt(4)+1; //Choisit un sens au hasard
            senshasard=(byte)senshasardint;

            switch (senshasardint) { // va déterminer un bord de la map où spawner en fonction du sens choisi
                case 1: //le véhicule va de bas en haut
                    x = r.nextInt(taillematrice);
                    y = taillematrice - 1;
                    break;
                case 2://le véhicule va de gauche à droite
                    x = 0;
                    y = r.nextInt(taillematrice);
                    break;
                case 3://le véhicule va de haut en bas
                    x = r.nextInt(taillematrice);
                    y = 0;
                    break;
                case 4://le véhicule va de droite à gauche
                    x = taillematrice - 1;
                    y = r.nextInt(taillematrice);
                    break;
                default:
                    throw new ExceptionSensIncorrect();
            }

            noeudchoisi=matrice.getMatrice()[y][x];

            if(nbvoitures>0) {
                for (Voiture autrevoiture:voitures) { //On vérifie pour chaque voiture existante...
                    if (autrevoiture.getNoeud() == noeudchoisi) { //si le noeud choisi est occupé
                        if (senshasardint == 1 || senshasard == 3) { //si on est en haut ou en bas
                            if (matrice.getMatrice()[y][x - 1] != null) { // on le place à gauche si il existe un noeud à gauche
                                noeudchoisi = matrice.getMatrice()[x - 1][y];
                            } else if (matrice.getMatrice()[y][x + 1] != null) { // sinon on le place à droite si le noeud à droite existe
                                noeudchoisi = matrice.getMatrice()[x + 1][y];
                            } else {//si il y a plus de place
                                noeudchoisi = null;
                            }
                        } else if (senshasardint == 2 || senshasardint == 4) { //si on est à gauche ou à droite
                            if (matrice.getMatrice()[y - 1][x] != null) { // on le place en haut si il exite un noeud en haut
                                noeudchoisi = matrice.getMatrice()[y - 1][x];
                            } else if (matrice.getMatrice()[y + 1][x] != null) { // sinon on le place en bas si le noeud en bas existe
                                noeudchoisi = matrice.getMatrice()[y + 1][x];
                            } else {//si il y a plus de place
                                noeudchoisi = null;
                            }
                        } else {
                            throw new ExceptionSensIncorrect();
                        }
                    }
                }
            }

            if(noeudchoisi!=null) {//si il y a de la place
                //On prend le sousnoeud au bord de la map
                Sousnoeud sousnoeudchoisi;
                switch (senshasardint){ //On va prendre le sousnoeud correspondant où faire apparaitre la voiture
                    case 1: //le véhicule va de bas en haut
                        sousnoeudchoisi=noeudchoisi.getBas().getSousnoeud2();
                        break;
                    case 2://le véhicule va de gauche à droite
                        sousnoeudchoisi=noeudchoisi.getGauche();
                        break;
                    case 3://le véhicule va de haut en bas
                        sousnoeudchoisi=noeudchoisi.getHaut();
                        break;
                    case 4://le véhicule va de droite à gauche
                        sousnoeudchoisi=noeudchoisi.getDroite();
                        break;
                    default:
                        throw new ExceptionSensIncorrect();
                }
                while(sousnoeudchoisi.getSousnoeud2()!=null){
                    sousnoeudchoisi=sousnoeudchoisi.getSousnoeud2();
                }
                //On crée la voiture
                Voiture nouveau = new Voiture(sousnoeudchoisi,senshasard, acceleration, freinage,vitessemax);
                voitures.add(nouveau);
                nbvoitures++;
            }
        }

        //Dessin et Affichage des voitures
        float largeurvoiture=zoom/12;
        float longueurvoiture=zoom/8;
        Rectangle dessinvoiture;
        for (Voiture voiture:voitures) {
            Sousnoeud sousnoeudvoiture=voiture.getSousnoeud();
            byte sens = voiture.getDirection();
            float x,y;
            switch (sens){
                case 1: //bas en haut
                    x=sousnoeudvoiture.getX()*zoom+zoom/20;
                    y=sousnoeudvoiture.getY()*zoom;
                    break;
                case 2://gauche à droite
                    x=sousnoeudvoiture.getX()*zoom;
                    y=sousnoeudvoiture.getY()*zoom+zoom/20;
                    break;
                case 3://haut en bas
                    x=sousnoeudvoiture.getX()*zoom-zoom/20;
                    y=sousnoeudvoiture.getY()*zoom;
                    break;
                case 4://droite à gauche
                    x=sousnoeudvoiture.getX()*zoom;
                    y=sousnoeudvoiture.getY()*zoom-zoom/20;
                    break;
                default:
                    throw new ExceptionSensIncorrect();
            }
            if(sens == 1 || sens == 3){
                dessinvoiture = new Rectangle(x,y,longueurvoiture, largeurvoiture);
            }else if (sens == 2 || sens == 4){
                dessinvoiture = new Rectangle(x,y,largeurvoiture,longueurvoiture);
            }else{
                throw new ExceptionSensIncorrect();
            }
            dessinvoiture.setFill(Color.RED);
            root.getChildren().add(dessinvoiture);
            voituredessins.add(dessinvoiture);
        }

        //DEFINITION DE LA TIMELINE
        Timeline chronologie = new Timeline();
        chronologie.setCycleCount(1);

        //Déplacement des voitures
        for (Voiture voiture:voitures) {
            voiture.run();

        }

        primaryStage.show();

        }

        public static void main (String[]args){
        launch(args);
    }


}
