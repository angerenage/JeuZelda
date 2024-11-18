package universite_paris8.iut.EtrangeEtrange.modele.Stockage;

import javafx.beans.property.IntegerProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Conteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

import java.util.ArrayList;

public class Emplacement <T extends Objet> implements Conteneur<T> {
    private int stackPossible;
    private ArrayList<T> objets;

    public Emplacement() {
        this.objets = new ArrayList<>();
        this.stackPossible = 1;
    }

    @Override
    public boolean ajoutItem(T objet) {
        boolean ajoutReussi;

        if (this.objets.isEmpty()) {
            stackPossible = objet.stackMax();
            this.objets.add(objet);
            ajoutReussi = true;
        }
        else if (this.objets.get(0).getClass().equals(objet.getClass())) {
            if (this.objets.size()+1 < stackPossible) this.objets.add(objet);
            ajoutReussi = true;
        }
        else {
            ajoutReussi = false;
        }

        return ajoutReussi;
    }

    public boolean supprimeObjet(T objet) {
        boolean aSupprimer = false;

        for (int i = this.objets.size() -1; i >= 0; i--) {
            if (objet == this.objets.get(i)) {
                this.objets.remove(i);
                aSupprimer = true;
            }
        }

        return aSupprimer;
    }

    /**
     * La méthode enlève l'objet de l'inventaire
     * @return
     */
    public T enleveObjet() {
        return this.objets.remove(0);
    }

    public ArrayList<T> enleverToutLesObjets() {
        ArrayList<T> nvList = new ArrayList<>(this.objets);
        vider();
        return nvList;
    }

    public void vider() {
        this.objets.clear();
    }

    @Override
    public boolean estPlein() {
        return false;
    }

    public int quantiteObjet() {
        return this.objets.size();
    }

    public boolean estVide() {
        return this.objets.isEmpty();
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

    public boolean estDeMemeClass(Objet objet) {
        return this.objets.get(0).getClass().equals(objet.getClass());
    }

    public boolean peuEncoreStacker() {
        return quantiteObjet() + 1 < this.stackPossible;
    }

    public <U extends Objet> boolean estDuMemeType(Class<U> typeObjet) {
        boolean estDuMemeType = false;

        if (!objets.isEmpty()) estDuMemeType = typeObjet.isInstance(objets.get(0));

        return estDuMemeType;
    }

    public Object getObjet() {
        if (this.objets.isEmpty())
            return null;
        else
            return this.objets.get(0);
    }

    @Override
    public String toString() {
        String nom = "";

        if (!this.objets.isEmpty()) nom = this.objets.get(0).getNom();

        return nom;
    }

    public T objetDansLemplacement() {
        return this.objets.get(0);
    }
}
