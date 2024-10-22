package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.BFS;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Orbe extends Projectile implements Utilisable, Rechargeable {

    private final BFS bfs;
    private Position positionAsuivre;
    private int nombreUtilisationRestant;
    private long derniereApelle;
    private Acteur acteurAsuivre;

    public Orbe() {
        super(ConstanteObjet.PV_ORBE, ConstanteObjet.VITESSE_ORBE, ConstanteObjet.HITBOX_ORBE);
        this.positionAsuivre = null;
        this.nombreUtilisationRestant = ConstanteObjet.NOMBRE_UTLISATION_ORBE;
        this.derniereApelle = 0;
        this.bfs = new BFS();
    }

    @Override
    public void utilise(Entite entite) {
        if (nombreUtilisationRestant > 0) {
            setMonde(entite.getMonde());
            setNewPosition(entite.getPosition().getX(), entite.getPosition().getY());

            if (acteurAsuivre == null)
                this.acteurAsuivre = monde.chercheEnemie();

            if (acteurAsuivre != null) {
                setUtilisateur(entite);
                this.bfs.chercherChemin(monde, getPosition(), acteurAsuivre.getPosition());

                entite.getMonde().ajoutActeur(this);
                this.positionAsuivre = this.bfs.prochainePosition();
                this.nombreUtilisationRestant--;
                this.monde.ajoutRechargeable(this);
            }
        }
    }

    @Override
    public void unTour() {
        long apelle = System.currentTimeMillis();


        if (apelle - derniereApelle >= delaie())
            cooldown();


        if (positionAsuivre != null) {
            double deltaX = positionAsuivre.getX() - getPosition().getX();
            double deltaY = positionAsuivre.getY() - getPosition().getY();

            if (Math.abs(deltaX) > Math.abs(deltaY))
                setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
            else
                setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);


            setSeDeplace(true);

            if (peutSeDeplacer())
                seDeplace(1);
            else
                enleveToutPv();

            if (positionAtteinte(positionAsuivre))
                this.positionAsuivre = this.bfs.prochainePosition();

        }

    }

    private boolean positionAtteinte(Position position) {
        return this.position != null
                && Math.abs(getPosition().getX() - position.getX()) < 0.1
                && Math.abs(getPosition().getY() - position.getY()) < 0.1;
    }

    @Override
    public String typeActeur() {
        return "orbe";
    }

    @Override
    public void dropApresMort() {

    }

    @Override
    public boolean estUnEnemie() {
        return false;
    }

    @Override
    public double degatPhysique() {
        return ConstanteObjet.DEGAT_PHYSIQUE_ORBE;
    }

    @Override
    public double degatSpecial() {
        return ConstanteObjet.DEGAT_SPECIAL_ORBE;
    }

    @Override
    public String getNom() {
        return "orbe";
    }

    @Override
    public int stackMax() {
        return ConstanteObjet.STACK_MAX_ORBE;
    }

    @Override
    public double durabilitee() {
        return nombreUtilisationRestant;
    }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_ORBE;
    }

    @Override
    public long delaie() {
        return ConstanteObjet.DELAI_CHERCHE_POSITION_ORBE;
    }

    @Override
    public boolean peutSeDeplacer() {
        return !monde.estHorsMap(this);
    }

    @Override
    public boolean cooldown() {
        this.derniereApelle = System.currentTimeMillis();
        this.bfs.chercherChemin(monde, getPosition(), acteurAsuivre.getPosition());
        this.positionAsuivre = bfs.prochainePosition();

        return true;
    }
}
