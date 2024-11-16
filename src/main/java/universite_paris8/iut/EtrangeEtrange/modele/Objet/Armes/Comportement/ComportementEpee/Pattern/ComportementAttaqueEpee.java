package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementEpee.Pattern;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Offensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementDynamique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.PatternCoupEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;

public class ComportementAttaqueEpee extends ComportementDynamique
{

    private final Epee epee;

    public ComportementAttaqueEpee(Epee epee) {
        super(-1, -1, null, 1, epee.getVitesse(), epee.getHitbox());
        this.epee = epee;
    }


    @Override
    public Pattern getPattern() {
        return new PatternCoupEpee(this);

    }



    @Override
    public void subitCollision(Acteur acteur) {
        if (acteur != getUtilisateur()) {
            getStatsPv().enleveToutPv();
            monde.ajoutActeurAsupprimer(this);
        }
    }

    @Override
    public void causeCollision(Acteur acteur) {
        acteur.subitAttaque(epee,(Offensif) getUtilisateur());
        monde.ajoutActeurAsupprimer(this);
    }

    @Override
    public void subitAttaque(Dommageable causeDegat, Offensif entiteOffensif) {
        getStatsPv().enleveToutPv();
        monde.ajoutActeurAsupprimer(this);

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
