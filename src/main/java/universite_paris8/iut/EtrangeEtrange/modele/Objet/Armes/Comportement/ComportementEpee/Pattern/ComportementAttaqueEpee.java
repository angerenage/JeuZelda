package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementEpee.Pattern;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionNombreTour.ComparateurInfEgal;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.ConditionNombreTour.ConditionNombreDeTour;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur.PatternCompositeStrategie;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Offensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementDynamique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.PatternCoupEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.PatternDeplacementEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;

public class ComportementAttaqueEpee extends ComportementDynamique
{

    private Epee epee;

    public ComportementAttaqueEpee(Epee epee) {
        super(-1, -1, null, 1, epee.getVitesse(), epee.getHitbox());
        this.epee = epee;
    }


    @Override
    public Pattern getPattern() {
        return new PatternCompositeStrategie(new ConditionNombreDeTour(() -> seDeplace(1), new ComparateurInfEgal(),10),() -> finit());
    }



    @Override
    public void subitCollision(Acteur acteur) {
        if (acteur != getUtilisateur()) {
            getStatsPv().enleveToutPv();
            monde.ajoutActeurAsupprimer(this);
            System.out.println("bb");
        }
        System.out.println("aa");
    }

    @Override
    public void causeCollision(Acteur acteur) {
        if (acteur != getUtilisateur()) {
            acteur.subitAttaque(epee,(Offensif) getUtilisateur());
            monde.ajoutActeurAsupprimer(this);
            System.out.println("bb");
        }
        System.out.println("aa");
    }

    @Override
    public void subitAttaque(Dommageable causeDegat, Offensif entiteOffensif) {
        getStatsPv().enleveToutPv();
        monde.ajoutActeurAsupprimer(this);
        System.out.println("aa");

    }

    @Override
    public String typeActeur() {
        return "epee";
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
