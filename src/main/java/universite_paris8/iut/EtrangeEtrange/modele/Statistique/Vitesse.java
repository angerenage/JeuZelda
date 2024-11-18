package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Vitesse {
    private DoubleProperty vitesseMaximum;
    private DoubleProperty vitesse;

    public Vitesse(double vitesse) {
        this.vitesseMaximum = new SimpleDoubleProperty();
        this.vitesse = new SimpleDoubleProperty();

        setVitesse(vitesse);
        setVitesseMaximum(vitesse);
    }

    public void setVitesse(double vitesse) {
        this.vitesse.set(vitesse);
    }

    public void setVitesseMaximum(double vitesseMaximum) {
        this.vitesseMaximum.set(vitesseMaximum);
    }

    public double getVitesse() {
        return this.vitesse.get();
    }
}