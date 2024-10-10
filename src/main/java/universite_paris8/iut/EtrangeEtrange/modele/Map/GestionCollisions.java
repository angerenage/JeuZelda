package universite_paris8.iut.EtrangeEtrange.modele.Map;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public class GestionCollisions {
    public static void verifierCollisions(Monde monde, Acteur acteur) {
        for (Acteur acteur2 : monde.getEntites()) {
            if (acteur != acteur2 && collisionEntreActeur(acteur, acteur2)) {
                acteur2.subitCollision(acteur);
            }
        }

        if (acteur != monde.getJoueur() && collisionEntreActeur(acteur, monde.getJoueur())) {
            monde.getJoueur().subitCollision(acteur);
        }
    }

    public static boolean collisionAvecMap(Monde monde, Acteur acteur) {
        Position position = acteur.getPosition();
        Direction direction = acteur.getDirection();
        Hitbox hitbox = acteur.getHitbox();
        double vitesse = acteur.getVitesse();

        double x = position.getX() + vitesse * direction.getX();
        double y = position.getY() + vitesse * direction.getY();

        double extremite1, extremite2;

        if (direction == Direction.BAS || direction == Direction.HAUT) {
            extremite1 = hitbox.getPointLePlusAGauche(x);
            extremite2 = hitbox.getPointLePlusADroite(x);
        }
        else {
            extremite1 = hitbox.getPointLePlusEnHaut(y);
            extremite2 = hitbox.getPointLePlusEnBas(y);
        }

        boolean collision = false;
        int cpt = (int) extremite1;

        while (cpt <= extremite2 && !collision) {
            Carte carte = monde.getCarte();
            switch (direction) {
                case BAS:
                    collision = carte.estNonTraversable(cpt, (int) hitbox.getPointLePlusEnBas(y));
                    break;
                case HAUT:
                    collision = carte.estNonTraversable(cpt, (int) hitbox.getPointLePlusEnHaut(y));
                    break;
                case DROITE:
                    collision = carte.estNonTraversable((int) hitbox.getPointLePlusADroite(x), cpt);
                    break;
                case GAUCHE:
                    collision = carte.estNonTraversable((int) hitbox.getPointLePlusAGauche(x), cpt);
                    break;
            }
            cpt++;
        }

        return collision;
    }

    public static boolean collisionAvecActeurs(Monde monde, Acteur acteur1) {
        Acteur joueur = monde.getJoueur();
        ArrayList<Acteur> acteurs = monde.getEntites();
        boolean aCollision = false;

        for (int i = 0; i < acteurs.size() && !aCollision; i++) {
            Acteur acteur2 = acteurs.get(i);
            aCollision = collisionEntreActeur(acteur1, acteur2) && acteur2 != acteur1;
        }
        aCollision |= collisionEntreActeur(acteur1, joueur) && joueur != acteur1;

        return aCollision;
    }

    public static boolean collisionEntreActeur(Acteur acteur1, Acteur acteur2) {
        Position pos1 = acteur1.getPosition();
        Hitbox hitbox1 = acteur1.getHitbox();
        Position pos2 = acteur2.getPosition();
        Hitbox hitbox2 = acteur2.getHitbox();

        double x1Min = hitbox1.getPointLePlusAGauche(pos1.getX());
        double y1Min = hitbox1.getPointLePlusEnHaut(pos1.getY());
        double x1Max = hitbox1.getPointLePlusADroite(pos1.getX());
        double y1Max = hitbox1.getPointLePlusEnBas(pos1.getY());

        double x2Min = hitbox2.getPointLePlusAGauche(pos2.getX());
        double y2Min = hitbox2.getPointLePlusEnHaut(pos2.getY());
        double x2Max = hitbox2.getPointLePlusADroite(pos2.getX());
        double y2Max = hitbox2.getPointLePlusEnBas(pos2.getY());

        boolean collisionX = x1Min < x2Max && x1Max > x2Min;
        boolean collisionY = y1Min < y2Max && y1Max > y2Min;

        return collisionX && collisionY;
    }
}
