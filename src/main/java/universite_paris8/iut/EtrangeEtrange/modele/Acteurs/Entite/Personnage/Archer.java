package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstantesPersonnages;

public class Archer extends Joueur
{
    public Archer(double x, double y, Direction direction) {
        super(ConstantesPersonnages.ARCHER_PV,
              ConstantesPersonnages.ARCHER_ATTAQUE,
              ConstantesPersonnages.ARCHER_DEFENSE,
              ConstantesPersonnages.ARCHER_ATTAQUE_SPECIAL,
              ConstantesPersonnages.ARCHER_DEFENSE_SEPCIAL,
              ConstantesPersonnages.ARCHER_VITESSE,
              null,
              x, y,
              direction,
              new Hitbox(ConstantesPersonnages.ARCHER_HITBOX_HAUTEUR, ConstantesPersonnages.ARCHER_HITBOX_LARGEUR)
        );

        this.carquois = new Carquois();
        for (int i = 0; i < 100; i++) {
            carquois.ajoutItem(new Fleche());
        }
    }
}