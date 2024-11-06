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


    public Projectile(double vitesse,Hitbox hitbox)
    {
        this.vitesse = vitesse;
        this.hitbox = hitbox;
    }


    public double getVitesse()
    {
        return vitesse;
    }

    public Hitbox getHitbox(){

        for (int i = 0; i <100;i++)
            System.out.println(hitbox);

        return hitbox;
    }

    public abstract Comportement getComportement();

    @Override
    public boolean utiliseePar(Entite entite)
    {
        this.getComportement().lancer(entite);

        return durabilitee() <= 0;
    }
    

}