
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
public class MonJeu4 implements IJeuDesBilles {

    // attributs
    public static final int VIDE = -1;

    private int nombreLigne = 9;
    private int nombreColonne = 9;

    private int nombreBallesAjoutees = 3;
    private int ballesDep = 5;
    private int a;
    private int nombreCouleur = 8;
    private int caseTotales = 90;
    private int compteur;

    private int nombreBillesAlignees = 5;

    private List<Point> laListe = new ArrayList<Point>();
    private List<Point> lesPoints = new ArrayList<Point>();

    private int[] tabNouvBoules = new int[nombreBallesAjoutees];
    private int[][] grille;
    private int score;

    /**
     * Constructeur de note Jeu.
     */

    public MonJeu4() {
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
        if ((ligneD != ligneA) && (colonneD != colonneA)) {
            return lesPoints;
        }
        compteur = 0;
        if ((ligneD != ligneA) && (colonneD == colonneA)) {
            if (ligneD > ligneA) {
                for (int t = 0; t < ligneD - ligneA; t++) {
                    if (grille[ligneA + t][colonneA] == VIDE) {
                        compteur++;
                    }
                }
                if (compteur == ligneD - ligneA) {
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
                            compteur++;
                        }
                    }
                    if (compteur == ligneA - ligneD) {
                        Point p3 = new Point(ligneD, colonneD);
                        grille[ligneA][colonneA] = grille[ligneD][colonneD];
                        grille[ligneD][colonneD] = VIDE;
                        Point p4 = new Point(ligneA, colonneA);
                        lesPoints.add(p3);
                        lesPoints.add(p4);
                    }
                }
            }
        }
        compteur = 0;
        if ((ligneD == ligneA) && (colonneD != colonneA)) {
            if (colonneD > colonneA) {
                for (int t = 0; t < colonneD - colonneA; t++) {
                    if (grille[ligneA][colonneA + t] == VIDE) {
                        compteur++;
                    }
                }


                if (compteur == colonneD - ligneA) {
                    Point p5 = new Point(ligneD, colonneD);
                    grille[ligneA][colonneA] = grille[ligneD][colonneD];
                    grille[ligneD][colonneD] = VIDE;
                    Point p6 = new Point(ligneA, colonneA);
                    lesPoints.add(p5);
                    lesPoints.add(p6);

                }


                if (colonneD < colonneA) {
                    for (int t = 0; t < colonneA - colonneD; t++) {
                        if (grille[ligneA][colonneA - t] == VIDE) {
                            compteur++;
                        }
                    }


                    if (compteur == colonneA - colonneD) {
                        Point p7 = new Point(ligneD, colonneD);
                        grille[ligneA][colonneA] = grille[ligneD][colonneD];
                        grille[ligneD][colonneD] = VIDE;
                        Point p8 = new Point(ligneA, colonneA);
                        lesPoints.add(p7);
                        lesPoints.add(p8);
                    }
                }
            }
        }
        compteur = 0;

        ajoutNouvelleBille();


        boulesAlignees();

        return lesPoints;

    }

    public void boulesAlignees() {
        for (int i = 0; i < nombreLigne; i++) {
            for (int j = 0; j < nombreColonne; j++) {
                if (grille[i][j] != VIDE) {
                    if (grille[i - 1][j] == grille[i][j]) {
                        for (int z = 0; z < nombreBillesAlignees - 1; z++) {
                            if (grille[i - 1 + z][j] == grille[i][j]) {
                                compteur++;

                            }
                        }
                        if (compteur == nombreBillesAlignees) {
                            for (int h = 0; h < nombreBillesAlignees; h++) {
                                grille[i - 1 + h][j] = VIDE;
                                score += 2;
                            }
                        }
                        compteur = 0;
                        if (grille[i + 1][j] == grille[i][j]) {
                            for (int z = 0; z < nombreBillesAlignees - 2; z++) {
                                if (grille[i + 1 + z][j] == grille[i][j]) {
                                    compteur++;

                                }
                            }
                            if (compteur == nombreBillesAlignees) {
                                for (int h = 0; h < nombreBillesAlignees; h++) {
                                    grille[i + h][j] = VIDE;
                                    score += 2;
                                }
                            }
                        }
                        compteur = 0;
                        if (grille[i][j - 1] == grille[i][j]) {
                            for (int z = 0; z < nombreBillesAlignees - 1; z++) {
                                if (grille[i][j - 1 + z] == grille[i][j]) {
                                    compteur++;

                                }
                            }
                            if (compteur == nombreBillesAlignees) {
                                for (int h = 0; h < nombreBillesAlignees; h++) {
                                    grille[i][j - 1 + h] = VIDE;
                                    score += 2;
                                }
                            }
                        }
                        if (grille[i][j + 1] == grille[i][j]) {
                            for (int z = 0; z < nombreBillesAlignees + 2; z++) {
                                if (grille[i][j + 1 + z] == grille[i][j]) {
                                    compteur++;

                                }
                            }
                            if (compteur == nombreBillesAlignees) {
                                for (int h = 0; h < nombreBillesAlignees; h++) {
                                    grille[i][j + h] = VIDE;
                                    score += 2;

                                }
                            }
                            compteur = 0;
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
        return Bille.CouleurBille();
    }

    /**
     * Va afficher les 5 billes de depart.
     * Initialise les billes si l'emplacement dans la  grille est bien vide.
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
                    grille[bLig][bCol] = Bille.CouleurBille();
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
     * Prépapre les 3 prochaines boules et les retourne dans un tableau.
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