package universite_paris8.iut.EtrangeEtrange.modele.interaction.action;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie.Piece;

public class ActionAchat implements Action {
    private Objet objet;
    private Joueur joueur;

    public ActionAchat(Objet objet, Joueur joueur) {
        this.joueur = joueur;
        this.objet = objet;
    }

    @Override
    public void execute() {
        if (!joueur.getSac().estPlein() && joueur.getPiece() >= objet.prixAchat()) {
            joueur.getSac().ajoutItem(objet);
            int resteAPayer = objet.prixAchat();

            for (int i = 0; i < joueur.getSac().getTailleMax() && resteAPayer > 0; i++) {
                if (joueur.getSac().getEmplacement(i).getObjet() instanceof Piece) {
                    int quantite = joueur.getSac().getEmplacement(i).quantiteObjet();

                    for (int j = 0; j < quantite && resteAPayer > 0; j++) {
                        joueur.getSac().getEmplacement(i).enleveObjet();
                        resteAPayer--;
                    }
                }
            }
            System.out.println("Achat réussi : " + objet.getNom());
        } else {
            System.out.println("Achat échoué : sac plein ou fonds insuffisants");
        }
    }
}

