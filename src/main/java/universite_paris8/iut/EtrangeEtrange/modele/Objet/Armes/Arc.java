package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;


import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;

public class Arc implements Arme, Rechargeable {
    private long derniereApelle;
    private boolean peutTirer;

    private int durabilitee;
    private Fleche fleche;

    public Arc() {
        this.peutTirer = true;
        this.derniereApelle = -1;
        this.durabilitee = ConstanteObjet.DURABILITE_ARC;
        this.fleche = null;
    }

    @Override
    public void utilise(Entite entite) {
        if (peutTirer && fleche != null) {
            this.durabilitee--;
            this.fleche.setMonde(entite.getMonde());
            this.fleche.setNewPosition(entite.getPosition().getX(), entite.getPosition().getY());
            this.fleche.setDirection(entite.getDirection());
            this.fleche.setUtilisateur(entite);
            entite.getMonde().ajoutActeur(fleche);
            this.derniereApelle = System.currentTimeMillis();
            entite.getMonde().ajoutRechargeable(this);

            this.peutTirer = false;
            this.fleche = null;
        }
    }


    public void setFleche(Fleche fleche) {
        this.fleche = fleche;
    }

    @Override
    public long delaie() {
        return ConstanteObjet.DELAI_UTILISATION_ARC;
    }

    @Override
    public boolean cooldown() {
        boolean actionFait = false;

        long apelle = System.currentTimeMillis();

        if (apelle - derniereApelle >= delaie()) {
            this.derniereApelle = -1;
            this.peutTirer = true;
            actionFait = true;
        }

        return actionFait;
    }

    @Override
    public String getNom() {
        return "arc";
    }

    @Override
    public int stackMax() {
        return ConstanteObjet.STACK_MAX_ARC;
    }

    @Override
    public double durabilitee() {
        return durabilitee;
    }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_ARC;
    }

}