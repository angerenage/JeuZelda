package universite_paris8.iut.EtrangeEtrange.modele.interaction.action;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Emplacement;
import java.util.ArrayList;


public class ActionVendre implements Action {
    private final Marchand marchand;

    public ActionVendre(Marchand marchand) {
        this.marchand = marchand;
    }

    @Override
    public void execute() {
        for (Emplacement<Objet> emplacement : marchand.getMarchandise().getInventaire()) {
            ArrayList<Objet> objets = emplacement.enleverToutLesObjets();
            for (Objet objet : objets) {
                System.out.println(objet.getNom() + "     [" + objet.prixAchat() + "]");
            }
        }
    }
}

