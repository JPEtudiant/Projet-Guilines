import java.util.Random;

public class Bille {

    private int numeroCouleurBille;
    private int nbCouleur = 8;
    private int VIDE = -1;

    public Bille() {
        this.numeroCouleurBille = numeroCouleurBille;

    }

    public int CouleurBille() {
        Random r = new Random();
        int i = 0;
        while (i != 1) {
            numeroCouleurBille = r.nextInt(nbCouleur);
            i++;
        }
        return numeroCouleurBille;
    }

    public int getNumCouleurBille() {
        return numeroCouleurBille;
    }
}