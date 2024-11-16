package universite_paris8.iut.EtrangeEtrange.modele.interaction.action;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

public class ActionSoigner implements Action {
    private Joueur joueur;

    public ActionSoigner(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public void execute() {
        joueur.getStatsPv().ajoutPv(joueur.getStatsPv().getPvMaximum());
    }
}
