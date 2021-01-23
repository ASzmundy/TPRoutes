package TPRoutes.Structures;

import TPRoutes.Vehicules.Voiture;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ThreadGarbageCollector extends Thread{
    ArrayList<Voiture> voitures;
    Group root;
    public ThreadGarbageCollector(ArrayList<Voiture> voitures, Group root){
        this.voitures=voitures;
        this.root=root;
    }

    @Override
    public void run() {
        super.run();
        int i=0;
        for(Voiture voiture: voitures){
            if(voiture.isExit()){
                voitures.remove(i);
                root.getChildren().remove(voiture.getDessinvoiture());
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
