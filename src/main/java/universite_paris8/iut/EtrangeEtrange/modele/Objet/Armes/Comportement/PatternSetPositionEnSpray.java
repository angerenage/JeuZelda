package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class PatternSetPositionEnSpray implements Pattern
{
    private final ComportementDynamique acteur;

    public PatternSetPositionEnSpray(ComportementDynamique acteur) {
        this.acteur = acteur;
    }


    @Override
    public void effectue() {
        acteur.setPosition(positionAleaAutourDe());
    }


    private Position positionAleaAutourDe() {
        final double dispersionSurLesCotes = 3;
        final double dispersionFace = 3;

        Position position = acteur.getUtilisateur().getPosition();
        Direction direction = acteur.getUtilisateur().getDirection();
        acteur.setDirection(direction);

        double newX = position.getX();
        double newY = position.getY();

        if (direction == Direction.DROITE || direction == Direction.GAUCHE) {
            newX += ((Math.random() * dispersionFace) * direction.getX());
            newY += (Math.random() * dispersionSurLesCotes - dispersionSurLesCotes / 2);
        } else {
            newX += (Math.random() * dispersionSurLesCotes - dispersionSurLesCotes / 2);
            newY += ((Math.random() * dispersionFace) * direction.getY());
        }

        return new Position(newX, newY);
    }
}
