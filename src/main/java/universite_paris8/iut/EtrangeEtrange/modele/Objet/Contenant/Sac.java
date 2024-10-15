package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;

public class Sac extends ObjetConteneur<Objet> {

    public Sac() {
        super(ConstanteObjet.TAILLE_SAC);
    }

    @Override
    public int stackMax() {
        return ConstanteObjet.STACK_MAX_SAC;
    }

    @Override
    public String getNom() {
        return "sac";
    }

    @Override
    public double durabilitee() {
        return ConstanteObjet.DURABILITE_SAC;
    }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_SAC;
    }

}
