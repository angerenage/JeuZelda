package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort;

import javafx.application.Platform;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParProjectile;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.FlecheSimple;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.TimerAction;

import java.util.TimerTask;

public class SortilegePluitDeFleche extends Sortilege
{
    private final int NOMBRE_FLECHE = 100;

    @Override
    public void action(EntiteOffensif utilisateur)
    {
        double x = utilisateur.getPosition().getX();
        double y = utilisateur.getPosition().getY();

        Direction direction = utilisateur.getDirection();
        for (int i = 0;i<NOMBRE_FLECHE;i++)
        {
            TimerAction.addAction(new TimerTask()
            {
                @Override
                public void run()
                {
                    Platform.runLater(() -> {
                        FlecheSimple flecheSimple = new FlecheSimple();
                        flecheSimple.setDirection(direction);
                        flecheSimple.setPositionOrigine(positionAleaAutourDe(x,y, direction));
                        utilisateur.getMonde().ajoutCauseDegat(new ActionDegatParProjectile(utilisateur, flecheSimple));
                    });
                }
            }, i * 100);
        }
    }

    private Position positionAleaAutourDe(double x, double y,Direction direction)
    {
        final double dispersionSurLesCotes = 3 ;
        final double dispersionFace = 3 ;

        double newX = x ;
        double newY = y ;

        if (direction == Direction.DROITE || direction == Direction.GAUCHE)
        {
            newX += ((Math.random() * dispersionFace) * direction.getX());
            newY += (Math.random() * dispersionSurLesCotes - dispersionSurLesCotes / 2);
        }
        else
        {
            newX += (Math.random() * dispersionSurLesCotes - dispersionSurLesCotes / 2);
            newY += ((Math.random() * dispersionFace) * direction.getY());
        }

        return new Position(newX,newY);
    }
}
