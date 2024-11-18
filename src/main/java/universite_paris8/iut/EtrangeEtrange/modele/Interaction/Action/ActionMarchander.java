package universite_paris8.iut.EtrangeEtrange.modele.Interaction.Action;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.stockage.Emplacement;

import java.util.ArrayList;

public class ActionMarchander extends Action {
    private Marchand marchand;

    public ActionMarchander(Marchand marchand) {
        this.marchand = marchand;
    }

    @Override
    public Prompt execute() {
        Prompt racine = new Prompt("Voici ce que j'ai", null);

        for (Emplacement<Objet> objets : marchand.getMarchandise().getInventaire()) {
            ArrayList<Objet> obs = objets.enleverToutLesObjets();

            for (Objet objet : obs) {
                racine.ajoutPrompt(new Prompt("Ta fais une bonne affaire !", new ActionAchat(marchand, objet, marchand.getMonde().getJoueur(), this)), objet.getNom() + "     [" + objet.prixAchat() + "]");
                System.out.println("nv objet");
            }
        }

        return racine;
    }
}
