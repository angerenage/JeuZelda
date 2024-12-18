package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstantesPersonnages;

public class Guerrier extends Joueur {
    public Guerrier(double x, double y, Direction direction) {
        super(ConstantesPersonnages.GUERRIER_PV,
              ConstantesPersonnages.GUERRIER_ATTAQUE,
              ConstantesPersonnages.GUERRIER_DEFENSE,
              ConstantesPersonnages.GUERRIER_ATTAQUE_SPECIAL,
              ConstantesPersonnages.GUERRIER_DEFENSE_SEPCIAL,
              ConstantesPersonnages.GUERRIER_VITESSE,
              new Epee(),
              x, y,
              direction,
              new Hitbox(0.50, 0.50)
        );

        this.carquois = new Carquois();
        for (int i = 0; i < 100; i++) {
            carquois.ajoutItem(new Fleche());
        }
    }
}
