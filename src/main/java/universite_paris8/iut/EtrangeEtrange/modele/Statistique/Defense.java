package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Defense {
    private DoubleProperty defenseMaximum;
    private DoubleProperty defense;

    public Defense(double defense) {
        this.defenseMaximum = new SimpleDoubleProperty();
        this.defense = new SimpleDoubleProperty();

        setDefense(defense);
        setDefenseMaximum(defense);
    }

    public void setDefense(double defenseActuelle) {
        this.defense.set(defenseActuelle);
    }

    public void setDefenseMaximum(double defenseMaximum) {
        this.defenseMaximum.set(defenseMaximum);
    }

    public double getDefense() {
        return this.defense.get();
    }

}