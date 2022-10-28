
//bibliotheques
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import guilines.IJeuDesBilles;

/**
 * Classe qui modelise notre jeu
 * @author COQUET Jean-Philippe
 */
public class MonJeu3 implements IJeuDesBilles {

    // attributs
    public static final int VIDE = -1;

    private int nombreLigne = 9;
    private int nombreColonne = 9;

    private int nombreBallesAjoutees = 3;
    private int ballesDep = 5;
    private int a;
    private int nombreCouleur = 8;
    private int caseTotales = 90;
    private int cmp;

    private int nombreBillesAlignees = 5;

    private List<Point> laListe = new ArrayList<Point>();
    private List<Point> lesPoints = new ArrayList<Point>();

    private List<Point> lesPointsTrouver = new ArrayList<Point>();

    private int[] tabNouvBoules = new int[nombreBallesAjoutees];
    private int[][] grille;
    private int score;

    /**
     * Constructeur de note Jeu.
     */

    public MonJeu3() {
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
     * fonction deplace qui va prendre en parametre une coordonee i et j 
     * 
     * @return : List<Point>
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
            ajoutNouvelleBille();
            boulesAlignees();
        }
        return lesPoints;
    }

    /**
     * fonction qui verifie si il y a 5 boules alignées et modifie le score si il ya bien 5
     * boules alignées
     * 
     */
    public void boulesAlignees() {
        for (int i = 0; i < nombreLigne; i++) {
            for (int j = 0; j < nombreColonne; j++) {
                if (grille[i][j] != VIDE) {
                    if (grille[i - 1][j] == grille[i][j]) {
                        for (int z = 0; z < nombreBillesAlignees - 1; z++) {
                            if (grille[i - 1 + z][j] == grille[i][j]) {
                                cmp++;

                            }
                        }
                        if (cmp == nombreBillesAlignees) {
                            for (int h = 0; h < nombreBillesAlignees; h++) {
                                grille[i - 1 + h][j] = VIDE;
                                score += 2;
                            }
                        }
                        cmp = 0;
                        if (grille[i + 1][j] == grille[i][j]) {
                            for (int z = 0; z < nombreBillesAlignees - 2; z++) {
                                if (grille[i + 1 + z][j] == grille[i][j]) {
                                    cmp++;

                                }
                            }
                            if (cmp == nombreBillesAlignees) {
                                for (int h = 0; h < nombreBillesAlignees; h++) {
                                    grille[i + h][j] = VIDE;
                                    score += 2;
                                }
                            }
                        }
                        cmp = 0;
                        if (grille[i][j - 1] == grille[i][j]) {
                            for (int z = 0; z < nombreBillesAlignees - 1; z++) {
                                if (grille[i][j - 1 + z] == grille[i][j]) {
                                    cmp++;

                                }
                            }
                            if (cmp == nombreBillesAlignees) {
                                for (int h = 0; h < nombreBillesAlignees; h++) {
                                    grille[i][j - 1 + h] = VIDE;
                                    score += 2;
                                }
                            }
                        }
                        if (grille[i][j + 1] == grille[i][j]) {
                            for (int z = 0; z < nombreBillesAlignees + 2; z++) {
                                if (grille[i][j + 1 + z] == grille[i][j]) {
                                    cmp++;

                                }
                            }
                            if (cmp == nombreBillesAlignees) {
                                for (int h = 0; h < nombreBillesAlignees; h++) {
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

    /*
     * Fonction qui va retourner la couleur de la boule suivante
     *
     * @return : int.
     */


    public int trouveNouvelleCouleur() {
        Random r = new Random();
        int i = 0;
        while (i != 1) {
            a = r.nextInt(nombreCouleur);
            i++;
        }
        return a;
    }

    /**
     * Va afficher les 5 billes de depart.
     * verifie bien avant d'initialiser chaques billes si l'emplacement dans la
     * grille est bien vide.
     */

    public void billesDepart() {
        Random r = new Random();
        for (int i = 0; i < ballesDep; i++) {
            boolean val = false;
            while (val == false) {
                int bLig = r.nextInt(nombreLigne);
                int bCol = r.nextInt(nombreColonne);
                if (grille[bLig][bCol] == VIDE) {
                    Point b = new Point(bLig, bCol);
                    lesPoints.add(b);
                    grille[bLig][bCol] = trouveNouvelleCouleur();
                    val = true;
                }
            }
        }
    }

    /**
     * Prépare les 3 prochaines boules et les affiche.
     * 
     */

    public void ajoutNouvelleBille() {
        Random r = new Random();
        for (int i = 0; i < nombreBallesAjoutees; i++) {
            boolean val = false;
            while (val == false) {
                int bLig = r.nextInt(nombreLigne);
                int bCol = r.nextInt(nombreColonne);
                if (grille[bLig][bCol] == VIDE) {
                    Point b = new Point(bLig, bCol);
                    lesPoints.add(b);
                    grille[bLig][bCol] = trouveNouvelleCouleur();
                    val = true;
                }
            }
        }
    }

    /**
     * "Programme" les 3 prochaines boules et les retourne dans un tableau.
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