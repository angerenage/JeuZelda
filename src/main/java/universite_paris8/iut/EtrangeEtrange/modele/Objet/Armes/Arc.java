package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement.ComportementArc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;

public class Arc extends Arme {
    private final static int DURABILITE = ConstanteObjet.DURABILITE_ARC;
    private final static int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_ARC;
    private final static long DELAIE_UTILISATION = ConstanteObjet.DELAI_UTILISATION_ARC;
    private final static int STACK_MAX = ConstanteObjet.STACK_MAX_ARC;

    private int durabilitee;
    private Fleche fleche;

    public Arc() {
        this.durabilitee = DURABILITE;
        this.fleche = null;
    }

    @Override
    public Comportement initComportement() {
        return new ComportementArc(this);
    }

    @Override
    public boolean utiliseePar(Entite entite) {
        if (peuUtiliser() && fleche != null) {
            this.durabilitee--;

            startCooldown();

            peutEtreUtiliser(false);

            initComportement().lancer(entite);
        }

        return durabilitee <= 0;
    }

    public void setFleche(Fleche fleche) {
        this.fleche = fleche;
    }

    public Fleche getFleche() {
        return fleche;
    }

    @Override
    public long delaie() {
        return DELAIE_UTILISATION;
    }

    @Override
    public String getNom() {
        return "arc";
    }

    @Override
    public int stackMax() {
        return STACK_MAX;
    }

    @Override
    public double durabilitee() {
        return durabilitee;
    }

    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }
}
