package universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.Piece;

public class ActionAchat extends Action {
    private Objet objet;
    private Joueur joueur;

    private ActionMarchander marchander;

    public ActionAchat(Marchand marchand, Objet objet, Joueur joueur, ActionMarchander marchander) {
        this.joueur = joueur;
        this.objet = objet;
        this.marchander = marchander;
    }

    @Override
    public Prompt execute() {
        if (!joueur.getSac().estPlein()) {
            if (joueur.getPiece() >= objet.prixAchat()) {
                joueur.getSac().ajoutItem(objet);
                int resteAPayer = objet.prixAchat();

                for (int i = 0; i < joueur.getSac().getTailleMax() && resteAPayer != 0; i++) {
                    if (joueur.getSac().getEmplacement(i).getObjet() instanceof Piece) {
                        int quantite = joueur.getSac().getEmplacement(i).quantiteObjet();
                        for (int j = 0; j < quantite && resteAPayer != 0; j++) {
                            joueur.getSac().getEmplacement(i).enleveObjet();
                            resteAPayer--;
                        }
                    }
                }

            }
        }

        return this.marchander.execute();
    }
}
