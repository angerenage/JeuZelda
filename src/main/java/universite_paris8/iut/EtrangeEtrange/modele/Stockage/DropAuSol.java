package universite_paris8.iut.EtrangeEtrange.modele.Stockage;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class DropAuSol {


    private static int idStatic = 0;
    private final Position position;
    private final Objet objet;
    private final int id;

    public DropAuSol(Objet objet, Position position) {
        this.objet = objet;
        this.position = position;
        this.id = idStatic++;
    }

    public Position getPosition() {
        return position;
    }

    public int getId() {
        return this.id;
    }


    public Objet getObjet() {
        return objet;
    }
}
