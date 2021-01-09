package TPRoutes.Structures;

import TPRoutes.Vehicules.Voiture;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ThreadGarbageCollector extends Thread{
    ArrayList<Voiture> voitures;
    ArrayList<Rectangle> dessinvoitures;
    Group root;
    public ThreadGarbageCollector(ArrayList<Voiture> voitures, ArrayList<Rectangle> dessinvoitures, Group root){
        this.voitures=voitures;
        this.dessinvoitures=dessinvoitures;
        this.root=root;
    }

    @Override
    public void run() {
        super.run();
        int i=0;
        for(Voiture voiture: voitures){
            if(voiture.isExit()){
                voitures.remove(i);
                root.getChildren().remove(dessinvoitures.get(i));
                dessinvoitures.remove(i);
            }
            i++;
        }
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
