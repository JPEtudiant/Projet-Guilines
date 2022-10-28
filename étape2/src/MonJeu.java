import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import guilines.IJeuDesBilles;

/**
 * Classe qui modelise notre jeu
 * @author COQUET Jean-Philippe
 */
public class MonJeu implements IJeuDesBilles {

    public static final int VIDE = -1;

    private int nombreLigne = 9;
    private int nombreColonne = 9;

    private int nombreBallesAjoutees = 3;
    private int ballesDep = 5;
    private int a;
    private int nombreCouleur = 8;
    private int caseTotales = 90;
    private List<Point> laListe = new ArrayList<Point>();
    private List<Point> lesPoints = new ArrayList<Point>();
    private int[] tabNouvBoules = new int[nombreBallesAjoutees];
    private int[][] grille;
    private int score;

    /**
     * Constructeur de note Jeu.
     */

    public MonJeu() {
        grille = new int[nombreLigne][nombreColonne];
        this.reinit();
    }

    /**
     * Les différents accesseurs pour le nombre de :
     * Ligne
     * Colonne
     * Balles ajoutees
     * Score
     * Couleurs
     * 
     * @return : retourne le nombre de :
     *  Ligne
     * Colonne
     * Balles ajoutees
     * Score
     * Couleurs
     */

    public int getNbLignes() {
        return nombreLigne;
    }

    

    public int getNbColonnes() {
        return nombreColonne;
    }

    

    public int getNbBallesAjoutees() {
        return nombreBallesAjoutees;
    }

    
    public int getScore() {
        return score;
    }

    
    public int getNbCouleurs() {
        return nombreCouleur;
    }

   

    public int getCouleur(int ligne, int colonne) {
        return grille[ligne][colonne];
    }

    /**
     * Accesseur sur les prochaines couleurs.
     * 
     * @return : Le tableau retourné a pour taille le nombre de balles ajoutees
     *         après chaque action du joueur. Si le joueur a le droit de rejouer
     *         le tableau retourne est identique au tableau de départ
     *    
     */

    public List<Point> deplace(int ligneD, int colonneD, int ligneA, int colonneA) {
        if ((grille[ligneD][colonneD] != VIDE) && (grille[ligneA][colonneA] == VIDE)) {
            // Change les boules
            Point p1 = new Point(ligneD, colonneD);
            grille[ligneA][colonneA] = grille[ligneD][colonneD];
            grille[ligneD][colonneD] = VIDE;
            Point p2 = new Point(ligneA, colonneA);
            lesPoints.add(p1);
            lesPoints.add(p2);
	    
        return lesPoints;}
    }

     /**
     * Reinitialise une partie dans le cas ou toutes les cases sont vides, et remet le score a 0
     */
    public void reinit() {
        for (int i = 0; i < nombreLigne; i++) {
            for (int j = 0; j < nombreColonne; j++) {
                grille[i][j] = VIDE;
            }
        }
        score = 0;
        billesDepart();
    }

    public int trouveNouvelleCouleur() {
        Random r = new Random();
        int i = 0;
        while (i != 1) {
            a = r.nextInt(nombreCouleur);
            i++;
        }
        return a;
    }

    public void billesDepart() {
        Random r = new Random();
        for (int i = 0; i < ballesDep; i++) {
            boolean val = false;
            while (val == false) {
                int bouleLigne = r.nextInt(nombreLigne);
                int bouleColonne = r.nextInt(nombreColonne);
                if (grille[bouleLigne][bouleColonne] == VIDE) {
                    lesPoints.add(new Point(bouleLigne, bouleColonne));
                    grille[bouleLigne][bouleColonne] = trouveNouvelleCouleur();
                    val = true;
                }
            }
        }
    }

    public void ajoutNouvelleBille() {
        Random r = new Random();
        for (int i = 0; i < nombreBallesAjoutees; i++) {
            boolean val = false;
            while (val == false) {
                int bouleLigne = r.nextInt(nombreLigne);
                int bouleColonne = r.nextInt(nombreColonne);
                if (grille[bouleLigne][bouleColonne] == VIDE) {
                    lesPoints.add(new Point(bouleLigne, bouleColonne));
                    grille[bouleLigne][bouleColonne] = trouveNouvelleCouleur();
                    val = true;
                }
            }
        }
    }

    /**
     * Prépare les 3 prochaines boules et les retourne dans un tableau.
     * 
     * @return : le tableau des 3 prochaines boules.
     */
    public int[] getNouvellesCouleurs() {
        for (int i = 0; i < tabNouvBoules.length; i++) {
            int b = trouveNouvelleCouleur();
            tabNouvBoules[i] = b;
        }
        return tabNouvBoules;
    }

   /**
     * Teste si la partie est finie (si plus aucune case n'est disponible).
     * 
     * @return : boolean.
     */

    public boolean partieFinie() {
        int compteur = 0;
        for (int i = 0; i < nombreLigne; i++) {
            for (int j = 0; j < nombreColonne; j++) {
                if (grille[i][j] == VIDE) {
                    compteur++;
                }
            }
        }
        if (compteur == caseTotales) {
            return false;
        } else {
            return false;
        }

    }

}