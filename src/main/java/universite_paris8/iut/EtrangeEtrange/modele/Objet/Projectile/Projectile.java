package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Offensif;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

public abstract class Projectile implements Dommageable,Objet
{
    private double vitesse;
    private Hitbox hitbox;
    private int durabilite;

    public Projectile(double vitesse,int durabilite,Hitbox hitbox)
    {
        this.vitesse = vitesse;
        this.hitbox = hitbox;
        this.durabilite = durabilite;
    }


    public double getVitesse()
    {
        return vitesse;
    }

    public Hitbox getHitbox(){
        return hitbox;
    }

    public abstract Comportement getComportement();


    @Override
    public double durabilitee(){ return durabilite; }

    @Override
    public boolean utiliseePar(Entite entite)
    {
        boolean estCasser = durabilite <= 0;

        if (!estCasser) {
            this.getComportement().lancer(entite);
        }

        return estCasser;
    }
    

}