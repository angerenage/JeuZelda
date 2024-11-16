package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class PatternSetPosition implements Pattern
{
    private final ComportementDynamique comportement;

    public PatternSetPosition(ComportementDynamique comportement)
    {
        this.comportement = comportement;
    }

    @Override
    public void effectue() {

        Position position = comportement.getUtilisateur().getPosition();
        Direction direction = comportement.getUtilisateur().getDirection();
        comportement.setDirection(direction);
        Hitbox hitbox = comportement.getHitbox();

        double x = position.getX();
        double y = position.getY();

        double posX = 0;
        double posY = 0;

        switch (direction)
        {
            case HAUT:
                posX = x;
                posY = y - hitbox.getHauteur();
                break;
            case BAS:
                posX = x;
                posY = y + hitbox.getHauteur();
                break;
            case DROITE:
                posX = x + hitbox.getLargeur();
                posY = y;
                break;
            case GAUCHE:
                posX = x - hitbox.getLargeur();
                posY = y;
                break;

        }

        comportement.setPosition(posX, posY);

    }
}
