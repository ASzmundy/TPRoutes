package TPRoutes;

import TPRoutes.Structures.Matrice;
import TPRoutes.Structures.Noeud;
import TPRoutes.Structures.Sousnoeud;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FenetreApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        //CHOIX DES VARIABLES
        int taillematrice = 10; //n noeuds * n noeuds
        float concentration_sousnoeuds = 3; //n sous-noeuds entre 2 noeuds (je l'ai mis en float pour enlever les erreurs mais normalement c'est un int)
        int zoom=50; //Zoom


        Matrice matrice= new Matrice(taillematrice,concentration_sousnoeuds);
        Group root = new Group();
        Scene scene = new Scene(root,800,600, Color.BLACK);
        primaryStage.setTitle("Vroom vroom");
        primaryStage.setScene(scene);



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


        primaryStage.show();

        }

        public static void main (String[]args){
        launch(args);
    }


}
