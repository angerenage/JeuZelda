package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;

public class Carquois extends ObjetConteneur<Fleche> {

    public Carquois() {
        super(1);
    } // le passer en objet non stackable

    public Fleche retourneUneFleche() {
        return retourneObjet(0);
    }

    @Override
    public String getNom() {
        return "Carquois";
    }

    @Override
    public int stackMax() {
        return ConstanteObjet.STACK_MAX_CARQUOIS;
    }

    @Override
    public double durabilitee() {
        return ConstanteObjet.DURABILITE_CARQUOIS;
    }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_CARQUOIS;
    }

    @Override
    public Fleche objetALemplacement(int emplacement) {
        return super.objetALemplacement(emplacement);
    }

    @Override
    public boolean utiliseePar(Entite entite) {
        return false;
    }
}
