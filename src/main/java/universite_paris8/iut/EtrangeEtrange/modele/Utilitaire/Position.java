package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

public class Position {
    private final DoubleProperty x;
    private final DoubleProperty y;

    public Position(double x, double y) {
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();

        setX(x);
        setY(y);
    }

    public double getX() {
        return x.get();
    }

    public double getY() {
        return y.get();
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public DoubleProperty getXProperty() {
        return x;
    }

    public DoubleProperty getYProperty() {
        return y;
    }

    @Override
    public String toString() {
        return x.get() + " - " + y.get();
    }
}
