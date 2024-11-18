package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Position {
    private DoubleProperty x;
    private DoubleProperty y;

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

	public double distance(Position position) {
		return Math.sqrt(Math.pow(position.getX() - getX(), 2) + Math.pow(position.getY() - getY(), 2));
	}

    @Override
    public String toString() {
        return x.get() + " - " + y.get();
    }
}
