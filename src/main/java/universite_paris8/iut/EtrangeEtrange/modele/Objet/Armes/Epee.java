package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Epee extends Acteur implements Dommageable,Rechargeable,Arme
{
    private boolean peutTaper;
    private short cycle;
    private long derniereApelle;
    private Entite utilisateur;


    public Epee()
    {
        super(ConstanteObjet.DURABILITE_EPEE, ConstanteObjet.VITESSE_EPEE, ConstanteObjet.HITBOX_EPEE);
        this.peutTaper = true;
        this.cycle = 0;
        this.derniereApelle = 0;
    }


    @Override
    public void utilise(Entite entite)
    {
        if (peutTaper)
        {
            utilisateur = entite;
            setPosition(entite.getPosition());
            setMonde(entite.getMonde());
            setDirection(entite.getDirection());

            setPositionAttaque();
            entite.getMonde().ajoutActeur(this);

            this.derniereApelle = System.currentTimeMillis();
            entite.getMonde().ajoutRechargeable(this);

            this.peutTaper = false;
        }
    }


    @Override
    public void unTour()
    {
        if (cycle <= 2)
        {
            seDeplace(1);
            cycle++;
        }
        else
        {
            this.getMonde().enleveActeur(this);
            cycle = 0;
        }
    }

    private void setPositionAttaque()
    {
        double x = position.getX();
        double y = position.getY();

        double posX = 0;
        double posY = 0;

        switch (direction)
        {
            case HAUT:
                x = hitbox.getPointLePlusADroite(x);
                y = hitbox.getPointLePlusEnHaut(y);
                posX = 0;
                posY = -hitbox.getHauteur();
                break;
            case BAS:
                x = hitbox.getPointLePlusADroite(x);
                y = hitbox.getPointLePlusEnBas(y);
                posX = 0;
                posY = hitbox.getHauteur();
                break;
            case DROITE:
                x = hitbox.getPointLePlusEnBas(x);
                y = hitbox.getPointLePlusADroite(y);
                posX = hitbox.getLargeur();
                posY = 0;
                break;
            case GAUCHE:
                posX = -hitbox.getLargeur();
                posY = 0;
                break;
        }

        this.position = new Position(x+posX,y+posY);
    }


    @Override
    public void seDeplace(double coeff)
    {
        double x = this.direction.getX() ;
        double y = this.direction.getY() ;


        position.setX(position.getX() + x * ConstanteObjet.VITESSE_EPEE * coeff);
        position.setY(position.getY() + y * ConstanteObjet.VITESSE_EPEE * coeff);

    }


    @Override
    public void causeCollision(Acteur acteur)
    {
        acteur.subitAttaque(this,(EntiteOffensif) utilisateur);
        monde.ajoutActeurAsupprimer(this);
    }

    @Override
    public void subitAttaque(Dommageable causeDegat, EntiteOffensif entiteOffensif) {  /*  NE FAIT RIEN */ }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_EPEE;
    }
    @Override
    public boolean peutSeDeplacer() { return true; }

    @Override
    public String typeActeur() {
        return "epee";
    }

    @Override
    public void dropApresMort() {
        
    }

    @Override
    public long delaie() {
        return ConstanteObjet.DELAI_UTILISATION_EPEE;
    }

    @Override
    public boolean cooldown()
    {
        boolean actionFait = false;
        long apelle = System.currentTimeMillis();

        if (apelle - derniereApelle >= delaie())
        {
            this.derniereApelle = -1;
            this.peutTaper = true;
            actionFait = true;
        }

        return actionFait;
    }


    @Override
    public String getNom() {
        return "epee";
    }

    @Override
    public int stackMax() {
        return ConstanteObjet.STACK_MAX_EPEE;
    }

    @Override
    public double durabilitee() {
        return getPv();
    }


    @Override
    public double degatPhysique() {
        return ConstanteObjet.DEGAT_PHYSIQUE_EPEE;
    }

    @Override
    public double degatSpecial() {
        return ConstanteObjet.DEGAT_SPECIAL_EPEE;
    }

    @Override
    public void seFaitPousser(Acteur acteur) {/*NE FAIT RIEN*/}

    @Override
    public boolean estUnEnemie() {
        return false;
    }

    @Override
    public void subitCollision(Acteur acteur) {/*NE FAIT RIEN*/}

}
