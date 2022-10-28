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
    private List<Point> lesPointTrouver = new ArrayList<Point>(); 
    private int[][] grille;
    private int score;

    /**
     * Constructeur de notre Jeu.
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

    

    public int getnombreBallesAjoutees() {
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
        if ((ligneD != ligneA) && (colonneD != colonneA)) {
            return lesPoints;
        }
        cmp = 0;
        if ((ligneD != ligneA) && (colonneD == colonneA)) {
            if (ligneD > ligneA) {
                for (int t = 0; t < ligneD - ligneA; t++) {
                    if (grille[ligneA + t][colonneA] == VIDE) {
                        cmp++;
                    }
                }
                if (cmp == ligneD - ligneA) {
                    Point p1 = new Point(ligneD, colonneD);
                    grille[ligneA][colonneA] = grille[ligneD][colonneD];
                    grille[ligneD][colonneD] = VIDE;
                    Point p2 = new Point(ligneA, colonneA);
                    lesPoints.add(p1);
                    lesPoints.add(p2);

                }
                if (ligneD < ligneA) {
                    for (int t = 0; t < ligneA - ligneD; t++) {
                        if (grille[ligneA - t][colonneA] == VIDE) {
                            cmp++;
                        }
                    }
                    if (cmp == ligneA - ligneD) {
                        Point p1 = new Point(ligneD, colonneD);
                        grille[ligneA][colonneA] = grille[ligneD][colonneD];
                        grille[ligneD][colonneD] = VIDE;
                        Point p2 = new Point(ligneA, colonneA);
                        lesPoints.add(p1);
                        lesPoints.add(p2);
                    }
                }
            }
        }
        cmp = 0;
        if ((ligneD == ligneA) && (colonneD != colonneA)) {
            if (colonneD > colonneA) {
                for (int t = 0; t < colonneD - colonneA; t++) {
                    if (grille[ligneA][colonneA + t] == VIDE) {
                        cmp++;
                    }
                }
                if (cmp == colonneD - ligneA) {
                    Point p1 = new Point(ligneD, colonneD);
                    grille[ligneA][colonneA] = grille[ligneD][colonneD];
                    grille[ligneD][colonneD] = VIDE;
                    Point p2 = new Point(ligneA, colonneA);
                    lesPoints.add(p1);
                    lesPoints.add(p2);

                }
                if (colonneD < colonneA) {
                    for (int t = 0; t < colonneA - colonneD; t++) {
                        if (grille[ligneA][colonneA - t] == VIDE) {
                            cmp++;
                        }
                    }
                    if (cmp == colonneA - colonneD) {
                        Point p1 = new Point(ligneD, colonneD);
                        grille[ligneA][colonneA] = grille[ligneD][colonneD];
                        grille[ligneD][colonneD] = VIDE;
                        Point p2 = new Point(ligneA, colonneA);
                        lesPoints.add(p1);
                        lesPoints.add(p2);
                    }
                }
            }
        }

        ajoutNouvelleBille();
        boulesAlignees();

        return lesPoints;
    }

    public void boulesAlignees() {
        for (int i = 0; i < nombreLigne; i++) {
            for (int j = 0; j < nombreColonne; j++) {
                if (grille[i][j] != VIDE) {
                    if (grille[i - 1][j] == grille[i][j]) {
                        for (int z = 0; z < nbBillesAlignees - 1; z++) {
                            if (grille[i - 1 + z][j] == grille[i][j]) {
                                cmp++;

                            }
                        }
                        if (cmp == nbBillesAlignees) {
                            for (int h = 0; h < nbBillesAlignees; h++) {
                                grille[i - 1 + h][j] = VIDE;
                                score += 2;
                            }
                        }
                        cmp = 0;
                        if (grille[i + 1][j] == grille[i][j]) {
                            for (int z = 0; z < nbBillesAlignees - 2; z++) {
                                if (grille[i + 1 + z][j] == grille[i][j]) {
                                    cmp++;

                                }
                            }
                            if (cmp == nbBillesAlignees) {
                                for (int h = 0; h < nbBillesAlignees; h++) {
                                    grille[i + h][j] = VIDE;
                                    score += 2;
                                }
                            }
                        }
                        cmp = 0;
                        if (grille[i][j - 1] == grille[i][j]) {
                            for (int z = 0; z < nbBillesAlignees - 1; z++) {
                                if (grille[i][j - 1 + z] == grille[i][j]) {
                                    cmp++;

                                }
                            }
                            if (cmp == nbBillesAlignees) {
                                for (int h = 0; h < nbBillesAlignees; h++) {
                                    grille[i][j - 1 + h] = VIDE;
                                    score += 2;
                                }
                            }
                        }
                        if (grille[i][j + 1] == grille[i][j]) {
                            for (int z = 0; z < nbBillesAlignees + 2; z++) {
                                if (grille[i][j + 1 + z] == grille[i][j]) {
                                    cmp++;

                                }
                            }
                            if (cmp == nbBillesAlignees) {
                                for (int h = 0; h < nbBillesAlignees; h++) {
                                    grille[i][j + h] = VIDE;
                                    score += 2;

                                }
                            }
                            cmp = 0;
                        }
                    }
                }
            }
        }
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
                    Point b = new Point(bouleLigne, bouleColonne);
                    lesPoints.add(b);
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