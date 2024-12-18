package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

import java.util.ArrayList;

public abstract class ComportementDynamique extends Acteur implements Comportement
{
    private Pattern pattern;
    private Entite utilisateur;
    private long tourFait;

    public ComportementDynamique(double x, double y, Direction direction, double pv, double vitesse, Hitbox hitbox) {
        super(x, y, direction, pv, vitesse, hitbox);
        this.tourFait = 0;
    }


    @Override
    public void agit()
    {
        pattern.effectue();
    }

    public long getTourFait(){
        return tourFait;
    }

    public void finit()
    {
        this.tourFait = 0;
        this.setPosition(-1,-1);
        Monde.getMonde().ajoutActeurAsupprimer(this);
    }

    public Entite getUtilisateur(){
        return utilisateur;
    }
    public abstract Pattern getPattern();

    public void lancer(Entite entite)
    {
        this.utilisateur = entite;
        pattern = getPattern();
        new PatternSetPosition(this).effectue();
        Monde.getMonde().ajoutActeur(this);


    }
}



