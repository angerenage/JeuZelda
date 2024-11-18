package universite_paris8.iut.EtrangeEtrange.modele.stockage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventaire<T extends Objet> implements Conteneur<T> {

    private final IntegerProperty taille;
    private final Emplacement<T>[] inventaire;

    @SuppressWarnings("unchecked")
    public Inventaire(int taille) {
        this.taille = new SimpleIntegerProperty(taille);
        this.inventaire = (Emplacement<T>[]) new Emplacement[taille];
        Arrays.setAll(inventaire, i -> new Emplacement<>());
    }

    public boolean ajoutItem(T objet) {
        Emplacement<T> emplacement = (objet.stackMax() > 1)
                ? chercheEmplacement(Emplacement::peutEncoreStacker, objet.getClass())
                : chercheEmplacement(Emplacement::estVide);
        if (emplacement != null) {
            emplacement.ajoutItem(objet);
            return true;
        }
        return false;
    }

    public void vider() {
        Arrays.stream(inventaire).forEach(Emplacement::vider);
    }

    public boolean estPlein() {
        return Arrays.stream(inventaire).noneMatch(Emplacement::estVide);
    }

    public boolean estVide() {
        return Arrays.stream(inventaire).allMatch(Emplacement::estVide);
    }

    public int nombresObjets() {
        return Arrays.stream(inventaire).mapToInt(Emplacement::quantiteObjet).sum();
    }

    public int getTailleMax() {
        return taille.get();
    }

    @Override
    public boolean supprimeObjet(T objet) {
        Emplacement<T> emplacement = chercheEmplacement(
                empl -> empl.estDuMemeType(objet.getClass()) && empl.contientObjet(objet)
        );

        if (emplacement != null) {
            return emplacement.supprimeObjet(objet);
        }
        return false;
    }


    public IntegerProperty getTailleMaxProperty() {
        return taille;
    }

    public ArrayList<T> enleverObjet(int index) {
        return isValidIndex(index) ? inventaire[index].enleverToutLesObjets() : new ArrayList<>();
    }

    public T objetALemplacement(int index) {
        return isValidIndex(index) ? inventaire[index].getObjet() : null;
    }

    public T retourneObjet(int index) {
        return isValidIndex(index) ? inventaire[index].enleveObjet() : null;
    }

    public <U extends Objet> U trouveObjet(Class<U> typeObjet) {
        return Arrays.stream(inventaire)
                .filter(emplacement -> emplacement.estDuMemeType(typeObjet))
                .map(emplacement -> typeObjet.cast(emplacement.enleveObjet()))
                .findFirst()
                .orElse(null);
    }

    public Emplacement<T> getEmplacement(int index) {
        return isValidIndex(index) ? inventaire[index] : null;
    }

    public Emplacement<T>[] getInventaire() {
        return inventaire;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < inventaire.length;
    }

    private Emplacement<T> chercheEmplacement(CheckCondition<Emplacement<T>> condition) {
        return Arrays.stream(inventaire).filter(condition::check).findFirst().orElse(null);
    }

    private Emplacement<T> chercheEmplacement(CheckCondition<Emplacement<T>> condition, Class<? extends Objet> type) {
        return Arrays.stream(inventaire)
                .filter(emplacement -> emplacement.estDuMemeType(type) && condition.check(emplacement))
                .findFirst()
                .orElse(null);
    }

    @FunctionalInterface
    private interface CheckCondition<T> {
        boolean check(T t);
    }
}
