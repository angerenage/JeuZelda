package universite_paris8.iut.EtrangeEtrange.modele.interaction.action;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

public class ActionSoigner implements Action {
    private final Joueur joueur;

    public ActionSoigner() {
        this.joueur = Monde.getMonde().getJoueur();
    }

    @Override
    public void execute() {
        joueur.getStatsPv().ajoutPv(joueur.getStatsPv().getPvMaximum());
    }
}
