package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementProjectile.ComportementFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public abstract class ComportementDynamique extends Acteur implements Comportement
{
    private Pattern comportement;
    private Entite utilisateur;
    private long tourFait;

    public ComportementDynamique(double x, double y, Direction direction, double pv, double vitesse, Hitbox hitbox) {
        super(x, y, direction, pv, vitesse, hitbox);
        this.tourFait = 0;
    }


    @Override
    public void agit()
    {
        if (this instanceof ComportementFleche)
            System.out.println("Je bougeeeeeeeee");
        comportement.effectue();
    }

    public long getTourFait(){
        return tourFait;
    }

    public void finit()
    {
        this.tourFait = 0;
        Monde.getMonde().enleveActeur(this);
    }

    public Entite getUtilisateur(){
        return utilisateur;
    }
    public abstract Pattern initComportement();

    public void lancer(Entite entite)
    {
        this.utilisateur = entite;
        comportement = initComportement();
        new PatternSetPosition(this).effectue();
        Monde.getMonde().ajoutActeur(this);


    }
}
