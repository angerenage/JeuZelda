package universite_paris8.iut.EtrangeEtrange.modele.interaction.condition;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

public class ConditionJoueurBlesse implements Condition {
    private final Joueur joueur;

    public ConditionJoueurBlesse(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public boolean estRemplie() {
        return joueur.estBlesse();
    }
}
