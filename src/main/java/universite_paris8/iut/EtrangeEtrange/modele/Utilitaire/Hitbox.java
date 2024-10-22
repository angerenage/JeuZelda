package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import universite_paris8.iut.EtrangeEtrange.modele.Exeptions.HitboxInvalideExeption;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

public class Hitbox {

    private double hauteur;
    private double largeur;

    public Hitbox(double hauteur, double largeur) {
        setHitbox(hauteur, largeur);
    }

    public void setHitbox(double hauteur, double largeur) {
        if (hauteur < 0 || largeur < 0)
            throw new HitboxInvalideExeption("Valeur négatif interdit");

        if (hauteur > Monde.getSizeMondeHauteur() || largeur > Monde.getSizeMondeLargeur())
            throw new HitboxInvalideExeption("Valeur trop grande ");

        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    // Méthodes pour récupérer les points extrêmes de la hitbox fonctionne seulement pour des coordonées positive
    public double getPointLePlusADroite(double x) {
        return x + largeur / 2;
    }

    public double getPointLePlusAGauche(double x) {
        return x - largeur / 2;
    }

    public double getPointLePlusEnHaut(double y) {
        return y - hauteur / 2;
    }

    public double getPointLePlusEnBas(double y) {
        return y + hauteur / 2;
    }


    public double getLargeur() {
        return this.largeur;
    }

    public double getHauteur() {
        return this.hauteur;
    }
}
