package universite_paris8.iut.EtrangeEtrange.modele.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.tache.Tache;
import universite_paris8.iut.EtrangeEtrange.modele.stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import universite_paris8.iut.EtrangeEtrange.modele.constantes.PathRessources;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol.gestionAffichageSpriteDropAuSol;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.GestionAffichageSpriteEntite;
import universite_paris8.iut.EtrangeEtrange.controller.GestionActeur;

import java.util.ArrayList;
import java.util.Objects;

public class Monde {
    private static Monde monde;

    /**
     * Taille du monde (généré aléatoirement)
     * Ces valeurs ne servent que pour tester le fonctionnement de la scrolling map, elles seront supprimées lorsque les tests seront finis.
     */
    private static final int sizeMondeHauteur = 70;
    private static final int sizeMondeLargeur = 70;
    private static final double xPointDeDepart = 7;
    private static final double yPointDeDepart = 7;

    private ArrayList<Tache> taches = new ArrayList<>();

    private Carte carte;  // Nouvelle classe pour gérer les couches de la carte
    private Joueur joueur;
    private ObservableList<DropAuSol> dropsAuSol;

    private ObservableList<Acteur> acteurs = FXCollections.observableArrayList();
    private ArrayList<Acteur> acteursAsupprimer = new ArrayList<>();

    public static Monde getMonde() {
        return monde;
    }

    public static void initMonde(String nommap, int hauteur, int largeur) {
        if (monde == null) monde = new Monde(nommap, hauteur, largeur);
    }
	
	public Monde(String nommap, int hauteur, int largeur) {
        this.carte = new Carte(nommap, hauteur, largeur);
        this.dropsAuSol = FXCollections.observableArrayList();
    }

    public void creationMonstre(String nommap, int hauteur) {
		MonstreReader monstreReader = new MonstreReader(monde);
		monstreReader.readFile(String.format(PathRessources.MONSTRE_BASE_PATH, nommap));
    }

    public void verifCollision(Acteur acteur) {
        GestionCollisions.verifierCollisions(this, acteur);
    }

    public boolean collision(Acteur acteur) {
        return GestionCollisions.collisionAvecMap(this, acteur) || GestionCollisions.collisionAvecActeurs(this, acteur);
    }

    public void ajoutActeur(Acteur acteur) {
        this.acteurs.add(acteur);
    }

    public void ajoutActeurAsupprimer(Acteur acteur) {
        this.acteursAsupprimer.add(acteur);
    }

    public void ajouterDropAuSol(DropAuSol dropAuSol) {
        this.dropsAuSol.add(dropAuSol);
        System.out.println("passage2");
    }

    public static double getxPointDeDepart() {
        return xPointDeDepart;
    }

    public static double getyPointDeDepart() {
        return yPointDeDepart;
    }

    public static int getSizeMondeHauteur() {
        return sizeMondeHauteur;
    }

    public static int getSizeMondeLargeur() {
        return sizeMondeLargeur;
    }

    public ArrayList<DropAuSol> getDropAuSol() {
        return new ArrayList<>(this.dropsAuSol);
    }

    public void enleverDropAuSol(DropAuSol dropAuSol) {
        this.dropsAuSol.remove(dropAuSol);
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void unTour() {
        this.joueur.agit();

        for (int i = acteurs.size() - 1; i >= 0; i--)
            acteurs.get(i).agit();

        for (int i = 0; i < acteursAsupprimer.size(); i++) {
            enleveActeur(acteursAsupprimer.get(i));
        }

        this.acteursAsupprimer.clear();

        for(int i = taches.size() - 1; i >= 0; i--) {
            Tache tache = taches.get(i);

            if (tache.execute()) this.taches.remove(tache);
        }
    }

    public void ajoutTache(Tache rechargeable) {
        this.taches.add(rechargeable);
    }

    public boolean positionHorsMap(int x, int y) {
        return x < 0 || x >= sizeMondeLargeur || y < 0 || y >= sizeMondeHauteur;
    }

    public boolean estHorsMap(Acteur acteur) {
        Position position = acteur.getPosition();
        Hitbox hitbox = acteur.getHitbox();
        double vitesse = acteur.getVitesse();

        return switch (acteur.getDirection()) {
            case BAS -> hitbox.getPointLePlusEnBas(position.getY()) + vitesse >= Monde.getSizeMondeHauteur();
            case HAUT -> hitbox.getPointLePlusEnHaut(position.getY()) - vitesse < 0;
            case DROITE -> hitbox.getPointLePlusADroite(position.getX()) + vitesse >= Monde.getSizeMondeLargeur();
            case GAUCHE -> hitbox.getPointLePlusAGauche(position.getX()) - vitesse < 0;
            default -> false;
        };
    }

    public boolean estDansRayon(Position positionCentre, double rayon) {
        return getJoueur().getPosition().distance(positionCentre) <= rayon;
    }

    public void setListenerActeur(GestionActeur listenerActeur) {
        this.acteurs.addListener(listenerActeur);
    }

    public void setListenerListeDropsAuSol(gestionAffichageSpriteDropAuSol gestionAffichageDropAuSol) {
        this.dropsAuSol.addListener(gestionAffichageDropAuSol);
    }

    public void setListenerListeEntites(GestionAffichageSpriteEntite gestionAffichageSprite) {
        this.acteurs.addListener(gestionAffichageSprite);
    }

    public void enleveActeur(Acteur acteur) {
        acteur.dropApresMort();
        this.acteurs.remove(acteur);
    }

    public Acteur interactionAvecActeur() {
        Acteur act = null;
        double distance = -1;

        for (Acteur acteur : acteurs) {
            double distancePretendant = estEnFace(acteur);

            if (distancePretendant > distance) {
                act = acteur;
                distance = distancePretendant;
            }
        }

        return act;
    }

    private double estEnFace(Acteur acteur) {
        final double yDistanceMax = 2;
        final double xDistanceMax = 2;

        double dX;
        double dY;
        Direction directionJoueur = joueur.getDirection();

        if (directionJoueur == Direction.BAS || directionJoueur == Direction.HAUT) {
            dX = Math.abs(acteur.getPosition().getX() - joueur.getPosition().getX());
            dY = Math.abs(acteur.getPosition().getY() - joueur.getPosition().getY());
        }
        else {
            dY = Math.abs(acteur.getPosition().getX() - joueur.getPosition().getX());
            dX = Math.abs(acteur.getPosition().getY() - joueur.getPosition().getY());
        }

        return dX <= xDistanceMax && dY <= yDistanceMax ? dX + dY : -1;
    }

    public ArrayList<Acteur> getEntites() {
        ArrayList<Acteur> entites = new ArrayList<>();
        acteurs.stream().filter(Objects::nonNull).forEach(entites::add);
        return entites;
    }

	public Carte getCarte() {
        return carte;
    }

	public Acteur chercheEnemie() {
        Acteur acteur = null;

        for (Acteur act : acteurs) {
            if (act.estUnEnemie())
                acteur = act;
        }

        return acteur;
    }
}
