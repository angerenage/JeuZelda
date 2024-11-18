package universite_paris8.iut.EtrangeEtrange.modele.stockage;

import javafx.beans.property.IntegerProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

import java.util.ArrayList;

public class Emplacement<T extends Objet> implements Conteneur<T> {

    private int stackPossible = 1;
    private final ArrayList<T> objets = new ArrayList<>();

    @Override
    public boolean ajoutItem(T objet) {
        if (objets.isEmpty()) {
            stackPossible = objet.stackMax();
            objets.add(objet);
            return true;
        }
        if (objets.get(0).getClass().equals(objet.getClass()) && objets.size() + 1 < stackPossible) {
            objets.add(objet);
            return true;
        }
        return false;
    }

    public boolean supprimeObjet(T objet) {
        return objets.removeIf(o -> o == objet);
    }

    public T enleveObjet() {
        return objets.isEmpty() ? null : objets.remove(0);
    }

    public ArrayList<T> enleverToutLesObjets() {
        ArrayList<T> nvList = new ArrayList<>(objets);
        vider();
        return nvList;
    }

    public void vider() {
        objets.clear();
    }

    @Override
    public boolean estPlein() {
        return false;
    }

    public int quantiteObjet() {
        return objets.size();
    }

    public boolean estVide() {
        return objets.isEmpty();
    }

    @Override
    public int nombresObjets() {
        return 0;
    }

    @Override
    public int getTailleMax() {
        return 64;
    }

    @Override
    public IntegerProperty getTailleMaxProperty() {
        return null;
    }

    @Override
    public T objetALemplacement(int emplacement) {
        return null;
    }

    @Override
    public ArrayList<T> enleverObjet(int emplacement) {
        return null;
    }

    @Override
    public T retourneObjet(int emplacement) {
        return null;
    }

    public boolean peutEncoreStacker() {
        return quantiteObjet() + 1 < stackPossible;
    }

    public <U extends Objet> boolean estDuMemeType(Class<U> typeObjet) {
        return !objets.isEmpty() && typeObjet.isInstance(objets.get(0));
    }

    public T getObjet() {
        return objets.isEmpty() ? null : objets.get(0);
    }

    public boolean contientObjet(T objet) {
        return objets.contains(objet);
    }

}
