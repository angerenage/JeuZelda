package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.Pattern.PatternFlechePositionSpray;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

public class ComportementFlechePositionSpray extends ComportementFleche{
    public ComportementFlechePositionSpray(Fleche fleche) {
        super(fleche);
    }

    @Override
    public Pattern getPattern(){
        return new PatternFlechePositionSpray(this);
    }
}
