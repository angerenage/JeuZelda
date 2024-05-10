package universite_paris8.iut.EtrangeEtrange.modele.Map;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Monde {
    /**
     * Taille du monde (généré aléatoirement)
     * Ces valeurs ne servent que pour tester le fonctionnement de la scrolling map, elles seront supprimées lorsque les tests seront finis.
     */
    private static final int sizeMondeHauteur = 10;
    private static final int sizeMondeLargeur = 10;
    private static final double xPointDeDepart = 2;
    private static final double yPointDeDepart = 2;
    /**
     * Ici sont stocké les informations des sols du monde (ex : sol)
     */
    private int[][] fondMonde;
    /**
     * Ici sont stocké les informations des éléments du monde traversables (ex : buissons, fleurs, hautes herbes, etc.)
     */

    private ArrayList<Entite> entites;

    /**
     * Liste des identifiants des éléments du structureMonde :
     */

    public Monde(){
        this.fondMonde = new int[sizeMondeHauteur][sizeMondeLargeur];
        this.entites = new ArrayList<>();
    }

    /**
     * Permet de créer la map en récupérant les données dans un fichier qui a pour chemin d'accès le paramètre "nom"
     * @param nom
     */
    public Monde(String nom)
    {
        this.entites = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(nom));

            String ligneY = reader.readLine();
            String ligneX = reader.readLine();
            int y = Integer.parseInt(ligneY);
            int x = Integer.parseInt(ligneX);

            this.fondMonde = new int[y][x];

            String ligne;
            int ligneIndex = 0;

            while ((ligne = reader.readLine()) != null && ligneIndex < y)
            {
                String[] block = ligne.split(" ");

                for (int i = 0; i < x && i < block.length; i++)
                    this.fondMonde[ligneIndex][i] = Integer.parseInt(block[i]);

                ligneIndex++;
            }

        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            System.err.println("Erreur de format dans le fichier : " + e.getMessage());
        }
    }







    public int[][] getFondMonde() {
        return fondMonde;
    }

    /**
     * Génération totalement aléatoire d'un monde (pour les tests).
     */
    public void generationAléatoire(){
        for(int i = 0 ; i < this.fondMonde.length ; i++){
            for(int j = 0 ; j < this.fondMonde[i].length ; j++){
                this.fondMonde[i][j] = (int)(Math.random()*3)+1;
            }
        }
    }

    /**
     * Génération d'un monde à la main
     */
    public void generationManuelle(){
        // Attention aux dimensions du tableau (sizeMondeHauteur, sizeMondeLargeur)
        int[][] generationManuelle = {
                {1,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,3,3,3,3,1,3,1,1,1,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,3,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,3,3,3,1,3,1,3,1,1,1,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,1,1,1,3,1,3,1,3,3,3,3,1,3,3,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,1,1,3,1,3,1,1,3,1,3,3,3,3,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,1,1,1,1,1,3,1,1,3,1,3,1,1,3,1,1,1,1,3,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,3,1,3,3,3,3,3,3,1,3,3,3,3,3,3,3,3,3,3,3,1,3,3,3,3,3,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,3,3,3,3,1,3,1,1,1,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,3,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,3,3,3,1,3,1,3,1,1,1,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,1,1,1,3,1,3,1,3,3,3,3,1,3,3,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,1,1,3,1,3,1,1,3,1,3,3,3,3,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,1,1,1,1,1,3,1,1,3,1,3,1,1,3,1,1,1,1,3,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,3,3,3,3,3,3,3,3,1,3,3,3,3,3,3,3,3,3,3,3,1,3,3,3,3,3,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,3,3,3,3,1,3,1,1,1,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,3,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,3,3,3,1,3,1,3,1,1,1,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,1,1,1,3,1,3,1,3,3,3,3,1,3,3,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,1,1,3,1,3,1,1,3,1,3,3,3,3,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,1,1,1,1,1,3,1,1,3,1,3,1,1,3,1,1,1,1,3,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,3,3,3,3,3,3,3,3,1,3,3,3,3,3,3,3,3,3,3,3,1,3,3,3,3,3,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,3,3,3,3,1,3,1,1,1,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,3,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,3,3,3,1,3,1,3,1,1,1,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,1,1,1,3,1,3,1,3,3,3,3,1,3,3,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,1,1,3,1,3,1,1,3,1,3,3,3,3,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,1,1,1,1,1,3,1,1,3,1,3,1,1,3,1,1,1,1,3,1,3,1,3,1,1,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,3,3,3,3,3,3,3,3,1,3,3,3,3,3,3,3,3,3,3,3,1,3,3,3,3,3,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,3,1,3,1,1,1,1,1,1,3,1,3,3,1,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3},
                {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,1,3,3,3,3,3,3,3,3,1,3,3,1,3}
        };
        this.fondMonde = generationManuelle;
    }

    public ArrayList<Entite> getEntites(Position pos, double rayon)
    {
        ArrayList<Entite> entitesDansRayon = new ArrayList<>();

        for (Entite entite : this.entites)
        {
            if (calculerDistance(entite.getPosition(), pos) <= rayon) {
                entitesDansRayon.add(entite);
            }
        }

        return entitesDansRayon;
    }


    private double calculerDistance(Position pos1, Position pos2)
    {
        double dx = pos2.getX() - pos1.getX();
        double dy = pos2.getY() - pos1.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    public void enleveEntite(Entite entite)
    {
        this.entites.remove(entite);
    }

    public static double getxPointDeDepart(){
        return xPointDeDepart;
    }

    public static double getyPointDeDepart() {
        return yPointDeDepart;
    }

    public static int getSizeMondeHauteur(){
        return sizeMondeHauteur;
    }

    public static int getSizeMondeLargeur() {
        return sizeMondeLargeur;
    }
    public void ajoutEntite(Entite entite)
    {
        this.entites.add(entite);
    }

    public ArrayList<Entite> getEntities()
    {
        return new ArrayList<>(entites);
    }

    public int getTileType(int x, int y) {
        if (x >= 0 && x < fondMonde[0].length && y >= 0 && y < fondMonde.length) {
            return fondMonde[y][x];
        } else {
            return -1;
        }
    }
}
