package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Offensif;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementDynamique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.Pattern.PatternFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class ComportementFleche extends ComportementDynamique
{

    private final Fleche fleche;

    public ComportementFleche(Fleche fleche) {
        super(-1, -1, null, 1, fleche.getVitesse(), fleche.getHitbox());
        this.fleche = fleche;
    }

    @Override
    public Pattern getPattern() {
        return new PatternFleche(this);
    }


    @Override
    public boolean peutSeDeplacer()
    {
        return !monde.estHorsMap(this) && !monde.collisionMap(this);
    }

    @Override
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public void causeCollision(Acteur acteur) {
        if (acteur != getUtilisateur())
        {
            acteur.subitAttaque(fleche, (Offensif) getUtilisateur());
            enleveToutPv();
        }
    }

    @Override
    public void subitAttaque(Dommageable causeDegat, Offensif entiteOffensif) {

    }

    @Override
    public String typeActeur() {
        return "fleche";
    }

    @Override
    public void dropApresMort() {

    }

    @Override
    public void seFaitPousser(Acteur acteur) {

    }

    @Override
    public boolean estUnEnemie() {
        return false;
    }
}
