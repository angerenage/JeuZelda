package universite_paris8.iut.EtrangeEtrange.modele.stockage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

import java.util.ArrayList;

public class Inventaire<T extends Objet> implements Conteneur<T> {

    private final IntegerProperty taille;
    private final Emplacement<T>[] inventaire;

    public Inventaire(int taille) {
        this.taille = new SimpleIntegerProperty(taille);
        this.inventaire = (Emplacement<T>[]) new Emplacement[taille];
        for (int i = 0; i < this.inventaire.length; i++) {
            this.inventaire[i] = new Emplacement<>();
        }
    }

    public boolean ajoutItem(T objet) {
        Emplacement<T> emplacement = objet.stackMax() > 1 ? chercheEmplacementStackable(objet) : null;
        if (emplacement == null) {
            emplacement = chercheEmplacementVide();
        }
        if (emplacement != null) {
            emplacement.ajoutItem(objet);
            return true;
        }
        return false;
    }

    private ArrayList<Emplacement<T>> chercheEmplacementMemeItem(Class<? extends Objet> typeObjet) {
        ArrayList<Emplacement<T>> emplacements = new ArrayList<>();
        for (Emplacement<T> emplacement : inventaire) {
            if (emplacement.estDuMemeType(typeObjet)) {
                emplacements.add(emplacement);
            }
        }
        return emplacements;
    }

    public Emplacement<T> chercheEmplacementStackable(T objet) {
        return chercheEmplacementMemeItem(objet.getClass())
                .stream()
                .filter(Emplacement::peuEncoreStacker)
                .findFirst()
                .orElse(null);
    }

    public Emplacement<T> chercheEmplacementVide() {
        for (Emplacement<T> emplacement : inventaire) {
            if (emplacement.estVide()) {
                return emplacement;
            }
        }
        return null;
    }

    public void vider() {
        for (Emplacement<T> emplacement : inventaire) {
            emplacement.vider();
        }
    }

    public boolean estPlein() {
        for (Emplacement<T> emplacement : inventaire) {
            if (emplacement.estVide()) {
                return false;
            }
        }
        return true;
    }

    public boolean estVide() {
        for (Emplacement<T> emplacement : inventaire) {
            if (!emplacement.estVide()) {
                return false;
            }
        }
        return true;
    }

    public int nombresObjets() {
        int total = 0;
        for (Emplacement<T> emplacement : inventaire) {
            total += emplacement.quantiteObjet();
        }
        return total;
    }

    public int getTailleMax() {
        return this.taille.get();
    }

    @Override
    public boolean supprimeObjet(T objet) {
        for (Emplacement<T> emplacement : chercheEmplacementMemeItem(objet.getClass())) {
            if (emplacement.supprimeObjet(objet)) {
                return true;
            }
        }
        return false;
    }

    public IntegerProperty getTailleMaxProperty() {
        return this.taille;
    }

    public ArrayList<T> enleverObjet(int emplacement) {
        if (isValidIndex(emplacement) && !this.inventaire[emplacement].estVide()) {
            return new ArrayList<>(this.inventaire[emplacement].enleverToutLesObjets());
        }
        return new ArrayList<>();
    }

    public T objetALemplacement(int emplacement) {
        return isValidIndex(emplacement) && !this.inventaire[emplacement].estVide()
                ? this.inventaire[emplacement].objetDansLemplacement()
                : null;
    }

    public T retourneObjet(int emplacement) {
        return isValidIndex(emplacement) && !this.inventaire[emplacement].estVide()
                ? this.inventaire[emplacement].enleveObjet()
                : null;
    }

    public <U extends Objet> U trouveObjet(Class<U> typeObjet) {
        for (Emplacement<T> emplacement : inventaire) {
            if (emplacement.estDuMemeType(typeObjet)) {
                return typeObjet.cast(emplacement.enleveObjet());
            }
        }
        return null;
    }

    public Emplacement<T> getEmplacement(int emplacement) {
        return isValidIndex(emplacement) ? inventaire[emplacement] : null;
    }

    public Emplacement<T>[] getInventaire() {
        return this.inventaire;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < this.inventaire.length;
    }
}
