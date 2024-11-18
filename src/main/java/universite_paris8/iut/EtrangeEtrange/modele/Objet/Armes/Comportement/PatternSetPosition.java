package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class PatternSetPosition implements Pattern
{
    private ComportementDynamique comportement;
    private double xD,yD;



    public PatternSetPosition(ComportementDynamique comportement)
    {
        this.comportement = comportement;
        this.xD = 0.1;
        this.yD = 0.1;
    }

    @Override
    public void effectue() {

        Position position = comportement.getUtilisateur().getPosition();
        Direction direction = comportement.getUtilisateur().getDirection();
        comportement.setDirection(direction);
        Hitbox hitbox = comportement.getHitbox();
        System.out.println(comportement.getDirection());
        System.out.println(comportement.getUtilisateur().getPosition());

        double x = position.getX();
        double y = position.getY();

        double posX = 0;
        double posY = 0;

        switch (direction)
        {
            case HAUT:
                posX = x;
                posY = y - hitbox.getHauteur() - xD;
                break;
            case BAS:
                posX = x;
                posY = y + hitbox.getHauteur() + xD;
                break;
            case DROITE:
                posX = x + hitbox.getLargeur() + yD;
                posY = y;
                break;
            case GAUCHE:
                posX = x - hitbox.getLargeur() - yD  ;
                posY = y;
                break;

        }

        comportement.setPosition(posX , posY);

    }
}
