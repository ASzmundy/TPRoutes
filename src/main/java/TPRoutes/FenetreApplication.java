package TPRoutes;

import TPRoutes.Structures.Matrice;
import TPRoutes.Structures.Noeud;
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
        int zoom=1; //Zoom


        Matrice matrice= new Matrice(taillematrice,concentration_sousnoeuds);
        Group root = new Group();
        Scene scene = new Scene(root,800,600, Color.BLACK);
        primaryStage.setTitle("Vroom vroom");
        primaryStage.setScene(scene);



        for(int i=0;i<taillematrice;i++){
            for(int j=0;j<taillematrice;j++){
                Noeud noeud=matrice.getMatrice()[i][j];
                Rectangle recnode = new Rectangle(noeud.getX()*zoom,noeud.getY()*zoom,5*zoom,5*zoom);
                recnode.setFill(Color.BLUE);
                root.getChildren().add(recnode);
            }
        }


        primaryStage.show();

        }

        public static void main (String[]args){
        launch(args);
    }


}
