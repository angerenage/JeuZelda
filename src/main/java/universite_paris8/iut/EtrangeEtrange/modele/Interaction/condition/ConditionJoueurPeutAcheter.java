package universite_paris8.iut.EtrangeEtrange.modele.interaction.condition;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

public class ConditionJoueurPeutAcheter implements Condition {

    private final Objet objet;
    public ConditionJoueurPeutAcheter(Objet objet) {
        this.objet = objet;
    }

    @Override
    public boolean estRemplie() {
        Joueur joueur = Monde.getMonde().getJoueur();
        return (!joueur.getSac().estPlein() && joueur.getPiece() >= objet.prixAchat());
    }
}
