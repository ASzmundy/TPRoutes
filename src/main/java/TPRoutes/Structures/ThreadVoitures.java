package TPRoutes.Structures;

import TPRoutes.Exceptions.ExceptionSensIncorrect;
import TPRoutes.Vehicules.Voiture;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class ThreadVoitures extends Thread{
    ArrayList<Voiture> voitures;
    int zoom;

    public ThreadVoitures(ArrayList<Voiture> voitures, int zoom) {
        this.voitures = voitures;
        this.zoom=zoom;
    }

    private void changerCoordonnees(Voiture voiture, float x, float y){
        voiture.setX(x);
        voiture.setY(y);
    }

    private void changerDessin(Voiture voiture, double x, double y){
        voiture.getDessinvoiture().setX(x);
        voiture.getDessinvoiture().setY(y);
    }



    //méthode run
    @Override
    public void run() {
        super.run();
        Timeline chronologie = new Timeline(new KeyFrame(Duration.millis(60), // définition du temps du cycle
                action -> {

        for (Voiture voiture:voitures) { //Répéter sur toutes les voitures
            //Si la voiture est dans un noeud
            if(voiture.getNoeud()!=null) {
                voiture.accelerer();
                switch (voiture.getDirection()){ //Va prendre le bon sousnoeud
                    case 1: //bas en haut
                        voiture.setSousnoeud(voiture.getNoeud().getHaut());
                        voiture.setNoeud(null);
                        break;
                    case 2: //gauche à droite
                        voiture.setSousnoeud(voiture.getNoeud().getGauche());
                        voiture.setNoeud(null);
                        break;
                    case 3: //haut en bas
                        voiture.setSousnoeud(voiture.getNoeud().getBas());
                        voiture.setNoeud(null);
                        break;
                    case 4: //droite à gauche
                        voiture.setSousnoeud(voiture.getNoeud().getDroite());
                        voiture.setNoeud(null);
                        break;
                    default:
                        voiture.setExit(true);
                }
            }


            //Si la voiture est dans un sousnoeud
            else if(voiture.getSousnoeud()!=null){
                if (voiture.getSousnoeud().getSousnoeud1() != null) { // Si elle va dans un autre sousnoeud
                    //Remonter le sousnoeud
                    voiture.accelerer();
                    if(voiture.getDistanceprochainnoeud() <= voiture.getVitesse()) { //Si il va transiter dans le prochain sousnoeud
                        voiture.setSousnoeud(voiture.getSousnoeud().getSousnoeud1()); //Va dans le prochain sousnoeud
                        voiture.setDistanceprochainnoeud(voiture.getDistanceprochainnoeud()%100); //Ajoute la progression restante via un modulo
                    }else{//Sinon va avancer dans le sous-noeud
                        voiture.setDistanceprochainnoeud(voiture.getDistanceprochainnoeud()-voiture.getVitesse());
                    }
                    Path path=new Path();
                    if (voiture.getSousnoeud().getSousnoeud1() != null) {
                        switch (voiture.getDirection()) { //Va ajouter la progression du sous-noeud dans les coordonnées
                            case 1: //bas en haut
                                voiture.setY(voiture.getSousnoeud().getSousnoeud1().getY() - (voiture.getDistanceprochainnoeud()) / 100);
                                voiture.getDessinvoiture().setY(voiture.getSousnoeud().getY()*zoom - (voiture.getDistanceprochainnoeud()) / 100);
                                path.getElements().add(new MoveTo(voiture.getDessinvoiture().getX() + zoom / 8, voiture.getY()));
                                break;
                            case 2: //gauche à droite
                                voiture.setX(voiture.getSousnoeud().getSousnoeud1().getX() + (voiture.getDistanceprochainnoeud()) / 100);
                                voiture.getDessinvoiture().setX(voiture.getSousnoeud().getX()*zoom + (voiture.getDistanceprochainnoeud()) / 100);
                                path.getElements().add(new MoveTo(voiture.getDessinvoiture().getX(), voiture.getY() + zoom / 8));
                                break;
                            case 3: //haut en bas
                                changerCoordonnees(voiture,voiture.getX(),voiture.getSousnoeud().getSousnoeud1().getY());
                                changerDessin(voiture,voiture.getDessinvoiture().getX(),voiture.getSousnoeud().getY()*zoom + (voiture.getDistanceprochainnoeud()) / 100);
                                path.getElements().add(new MoveTo(voiture.getDessinvoiture().getX(), voiture.getY()));
                                break;
                            case 4: //droite à gauche
                                voiture.setX(voiture.getSousnoeud().getSousnoeud1().getX() - (voiture.getDistanceprochainnoeud()) / 100);
                                voiture.getDessinvoiture().setX(voiture.getSousnoeud().getX()*zoom - (voiture.getDistanceprochainnoeud()) / 100);
                                changerDessin(voiture,voiture.getSousnoeud().getX()*zoom - (voiture.getDistanceprochainnoeud()) / 100,voiture.getDessinvoiture().getY());
                                path.getElements().add(new MoveTo(voiture.getDessinvoiture().getX(), voiture.getY()));
                                break;
                            default:
                                voiture.setExit(true);
                        }//Sortie switch
                    }else if(voiture.getSousnoeud().getNoeud()!=null){//Si elle entre dans un sousnoeud
                        changerCoordonnees(voiture,voiture.getSousnoeud().getNoeud().getX(),voiture.getSousnoeud().getNoeud().getY());
                    }else voiture.setExit(true);

                    //Anime le déplacement
                    PathTransition pathTransition = new PathTransition(Duration.millis(10),path,voiture.getDessinvoiture());
                    pathTransition.setCycleCount(1);
                    pathTransition.setAutoReverse(false);
                    pathTransition.play();
                }

                //Si elle va dans un noeud
                else if (voiture.getSousnoeud().getNoeud() != null) {
                    //Prendre le noeud du sousnoeud
                    if(voiture.getDirection()==1||voiture.getDirection()==3){ //gestion des feux
                        if(voiture.getSousnoeud().getNoeud().isFeu()){
                            //feu rouge
                            voiture.freiner();
                        }else{
                            //feu vert
                            voiture.accelerer();
                            voiture.setNoeud(voiture.getSousnoeud().getNoeud());
                            voiture.setSousnoeud(null);
                        }
                    }else{
                        if(voiture.getSousnoeud().getNoeud().isFeu()){
                            //feu vert
                            voiture.accelerer();
                            voiture.setNoeud(voiture.getSousnoeud().getNoeud());
                            voiture.setSousnoeud(null);
                        }else{
                            //feu rouge
                            voiture.freiner();
                        }
                    }
                } else {
                    //A atteint la fin de la map
                    voiture.setSousnoeud(null);
                    voiture.setExit(true);
                }
            }else{
                //A atteint la fin de la map
                voiture.setExit(true);
            }
        }
        }));
        chronologie.setCycleCount(Timeline.INDEFINITE); //pour dire que l'appli tourne indéfiniment
        chronologie.play(); //pour lancer l'action du timeline
    }

}


