package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementEpee.Pattern.ComportementAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class PatternDeplacementEpee implements Pattern {

    private ComportementAttaqueEpee comportementAttaqueEpee;

    public PatternDeplacementEpee(ComportementAttaqueEpee comportementAttaqueEpee){
        this.comportementAttaqueEpee = comportementAttaqueEpee;
    }

    @Override
    public void effectue() {
        Direction direction = comportementAttaqueEpee.getDirection();
        Position position = comportementAttaqueEpee.getPosition();

        if (direction == Direction.DROITE) {
            position.setX(position.getX() + comportementAttaqueEpee.getVitesse());
        } else if (direction == Direction.GAUCHE) {
            position.setX(position.getX() - comportementAttaqueEpee.getVitesse());
        }
    }

}
