package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementEpee.Pattern.ComportementAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class PatternDeplacementEpee implements Pattern {

    private ComportementAttaqueEpee comportementAttaqueEpee;

    public PatternDeplacementEpee(ComportementAttaqueEpee comportementAttaqueEpee){
        this.comportementAttaqueEpee = comportementAttaqueEpee;
    }


    @Override
    public void effectue() {
        double x = comportementAttaqueEpee.getDirection().getX() ;
        double y = comportementAttaqueEpee.getDirection().getY() ;

        Position position = comportementAttaqueEpee.getPosition();

        position.setX(position.getX() + x * comportementAttaqueEpee.getVitesse() * 1);
        position.setY(position.getY() + y * comportementAttaqueEpee.getVitesse() * 1);
    }
}
