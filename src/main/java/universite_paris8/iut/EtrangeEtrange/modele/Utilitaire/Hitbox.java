package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

public class Hitbox {

    private double hauteur;
    private double largeur;

    public Hitbox(double hauteur, double largeur) {
        setHitbox(hauteur, largeur);
    }

    public void setHitbox(double hauteur, double largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

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
