package universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionDeplacement;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class ActionDeplacementGauche extends ActionDeplacement{
    @Override
    public Direction direction() {
        return Direction.GAUCHE;
    }
}
