package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.PatternDeplacement.PatternSeDirigerVersCible;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Offensif;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementDynamique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.Pattern.PatternOrbe;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Orbe;


public class ComportementOrbe extends ComportementDynamique
{

    private Orbe orbe;
    private Offensif utilisateur;
    private Acteur cible;

    public ComportementOrbe(Orbe orbe) {
        super(-1, -1, null, 1, orbe.getVitesse(), orbe.getHitbox());
        this.orbe = orbe;
    }

    @Override
    public Pattern getPattern() {
        return new PatternOrbe(this);
    }

    @Override
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public void lancer(Entite entite)
    {
        utilisateur = (Offensif) entite;

        if (entite instanceof Joueur)
            cible = Monde.getMonde().chercheEnemie();
        else
            cible = Monde.getMonde().getJoueur();



        super.lancer(entite);
    }

    public  Acteur getCible() {
        return cible;
    }


    @Override
    public void causeCollision(Acteur acteur) {

        if (acteur != utilisateur)
        {
            acteur.subitAttaque(orbe,utilisateur);
        }
    }

    @Override
    public void subitAttaque(Dommageable causeDegat, Offensif entiteOffensif) {

    }

    @Override
    public String typeActeur() {
        return "orbe";
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
