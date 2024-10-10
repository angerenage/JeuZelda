package universite_paris8.iut.EtrangeEtrange.modele.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Bloc.Bloc;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Boss.RoiSquelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Interagisable.Marchand;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Slime;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.DropAuSol.gestionAffichageSpriteDropAuSol;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.Entite.GestionAffichageSpriteEntite;
import universite_paris8.iut.EtrangeEtrange.controller.GestionActeur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Monde {
    private static final int sizeMondeHauteur = 70;
    private static final int sizeMondeLargeur = 70;
    private static final double xPointDeDepart = 7;
    private static final double yPointDeDepart = 7;

    private Carte carte;  // Nouvelle classe pour gérer les couches de la carte
    private Joueur joueur;
    private ObservableList<DropAuSol> dropsAuSol;
    private ObservableList<Acteur> acteurs = FXCollections.observableArrayList();
    private ArrayList<Acteur> acteursAsupprimer = new ArrayList<>();
    private ArrayList<Rechargeable> rechargeables = new ArrayList<>();

    public Monde(String chemin, String nommap, int hauteur, int largeur) {
        this.carte = new Carte(chemin, nommap, hauteur, largeur);
        this.dropsAuSol = FXCollections.observableArrayList();
    }

    public void creationMonstre(String chemin, String nommap, int hauteur) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(chemin + "/" + nommap + "_monstres.csv"));
            String ligne;
            int ligneIndex = 0;

            while ((ligne = reader.readLine()) != null && ligneIndex < hauteur) {
                String[] blocks = ligne.split(",");
                for (int j = 0; j < blocks.length; j++) {
                    Acteur acteur = null;

                    int monstre = Integer.parseInt(blocks[j]);
                    if (monstre != -1) {
                        switch (monstre) {
                            case 4:
                                acteur = new Marchand(this, j + 0.5, ligneIndex + 0.5, Direction.BAS);
                                break;
                            case 2:
                                acteur = new Slime(this, j + 0.5, ligneIndex + 0.5, Direction.BAS, new Hitbox(0.25, 0.5));
                                break;
                            case 3:
                                acteur = new Squelette(this, j + 0.5, ligneIndex + 0.5, Direction.BAS, new Hitbox(0.5, 0.5), joueur, new Aetoile(this));
                                break;
                            case 1:
                                acteur = new RoiSquelette(this, j + 0.5, ligneIndex + 0.5, Direction.BAS);
                                break;
                            case 0:
                                acteur = new Bloc(this, j + 0.5, ligneIndex + 0.5, Direction.BAS, 1, 1, new Hitbox(1, 1));
                                break;
                        }

                        if (acteur != null) {
                            ajoutActeur(acteur);
                        }
                    }
                }
                ligneIndex++;
            }
        }
        catch (IOException | NumberFormatException e) {
            System.err.println("Erreur lors de la lecture du fichier de monstres : " + e.getMessage());
        }
    }

    public void unTour() {
        joueur.unTour();
        acteurs.forEach(Acteur::unTour);
        acteursAsupprimer.forEach(this::enleveActeur);
        acteursAsupprimer.clear();
        rechargeables.removeIf(Rechargeable::cooldown);
    }

    public void ajoutActeur(Acteur acteur) {
        acteurs.add(acteur);
    }

    public void verifCollision(Acteur acteur) {
        GestionCollisions.verifierCollisions(this, acteur);
    }

    public boolean collision(Acteur acteur) {
        return GestionCollisions.collisionAvecMap(this, acteur) || GestionCollisions.collisionAvecActeurs(this, acteur);
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

    public boolean joueurDansRayon(Position positionCentre, double rayon){
        return getJoueur().getPosition().distance(positionCentre) <= rayon;
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

        if (directionJoueur == Direction.BAS ||directionJoueur == Direction.HAUT) {
            dX = Math.abs(acteur.getPosition().getX() - joueur.getPosition().getX());
            dY = Math.abs(acteur.getPosition().getY() - joueur.getPosition().getY());
        }
        else {
            dY = Math.abs(acteur.getPosition().getX() - joueur.getPosition().getX());
            dX = Math.abs(acteur.getPosition().getY() - joueur.getPosition().getY());
        }

        return dX <= xDistanceMax && dY <= yDistanceMax ? dX+dY : -1;
    }

    public Acteur chercheEnemie() {
        return acteurs.stream().filter(Acteur::estUnEnemie).findFirst().orElse(null);
    }

    public void ajoutActeurAsupprimer(Acteur acteur) {
        this.acteursAsupprimer.add(acteur);
    }

    public void enleveActeur(Acteur acteur) {
        acteur.dropApresMort();
        this.acteurs.remove(acteur);
    }

    public void ajouterDropAuSol(DropAuSol dropAuSol) {
        this.dropsAuSol.add(dropAuSol);
    }

    public void ajoutRechargeable(Rechargeable rechargeable) {
        this.rechargeables.add(rechargeable);
    }

    public ArrayList<Acteur> getEntites() {
        ArrayList<Acteur> entites = new ArrayList<>();
        acteurs.stream().filter(Objects::nonNull).forEach(entites::add);
        return entites;
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



    // Getters et setters
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

    public Carte getCarte() {
        return carte;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public ArrayList<DropAuSol> getDropAuSol() {
        return new ArrayList<>(this.dropsAuSol);
    }

    public void enleverDropAuSol(DropAuSol dropAuSol) {
        this.dropsAuSol.remove(dropAuSol);
    }

    public ArrayList<int[][]> getToutesLesCouches() {
        return carte.getToutesLesCouches();
    }
}
