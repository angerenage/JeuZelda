package universite_paris8.iut.EtrangeEtrange.modele.interaction.condition;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

public class ConditionJoueurBlesse implements Condition {
    private final Joueur joueur;

    public ConditionJoueurBlesse() {
        this.joueur = Monde.getMonde().getJoueur();
    }

    @Override
    public boolean estRemplie() {
        return joueur.estBlesse();
    }
}
