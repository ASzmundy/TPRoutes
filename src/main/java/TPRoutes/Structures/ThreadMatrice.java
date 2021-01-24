package TPRoutes.Structures;

import javafx.scene.paint.Color;

public class ThreadMatrice extends Thread{
    Matrice matrice;
    int taillematrice;

    public ThreadMatrice(Matrice matrice, int taillematrice) {
        this.matrice = matrice;
        this.taillematrice=taillematrice;
    }

    @Override
    public void run() {
        super.run();
        while(true) {
            //Orange avant le rouge
            for (int i = 0; i < taillematrice; i++) {
                for (int j = 0; j < taillematrice; j++) {
                    Noeud noeud = matrice.getMatrice()[i][j];
                    if (noeud.isFeu()) {
                        noeud.getDessinfeux().get(1).setFill(Color.ORANGE);
                        noeud.getDessinfeux().get(3).setFill(Color.ORANGE);
                    } else {
                        noeud.getDessinfeux().get(0).setFill(Color.ORANGE);
                        noeud.getDessinfeux().get(2).setFill(Color.ORANGE);
                    }
                }
            }
            try {
                synchronized (this) {
                    wait(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Passage au rouge
            for (int i = 0; i < taillematrice; i++) {
                for (int j = 0; j < taillematrice; j++) {
                    Noeud noeud = matrice.getMatrice()[i][j];
                    noeud.setFeu(!noeud.isFeu());

                    if (noeud.isFeu()) {
                        noeud.getDessinfeux().get(2).setFill(Color.RED);
                        noeud.getDessinfeux().get(0).setFill(Color.RED);
                        noeud.getDessinfeux().get(1).setFill(Color.GREEN);
                        noeud.getDessinfeux().get(3).setFill(Color.GREEN);
                    } else {
                        noeud.getDessinfeux().get(2).setFill(Color.GREEN);
                        noeud.getDessinfeux().get(0).setFill(Color.GREEN);
                        noeud.getDessinfeux().get(1).setFill(Color.RED);
                        noeud.getDessinfeux().get(3).setFill(Color.RED);
                    }
                }
            }
            try {
                synchronized (this) {
                    wait(4000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
