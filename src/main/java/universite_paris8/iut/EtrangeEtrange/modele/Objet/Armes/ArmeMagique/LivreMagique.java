package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegeOrbe;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegePluitDeFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Comportement;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Support.SortilegeDeSoins;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;

import java.util.ArrayList;

public class LivreMagique extends Arme {
    private final ArrayList<Sortilege> sortileges;

    public LivreMagique() {
        this.sortileges = new ArrayList<>();
        this.sortileges.add(new SortilegeOrbe());
        this.sortileges.add(new SortilegePluitDeFleche());
        this.sortileges.add(new SortilegeDeSoins());
    }

    @Override
    public Comportement initComportement() {
        return null;
    }

    @Override
    public boolean utiliseePar(Entite entite) {
        if (peuUtiliser()) {
            Sortilege sortilege = this.sortileges.get(0);
            sortilege.utiliseePar(entite);
            startCooldown();
            peutEtreUtiliser(false);
        }
        return false;
    }

    public void ajoutSortilege(Sortilege sortilege) {
        if (sortileges.size() + 1 < ConstanteObjet.SORT_MAXIMUM_LIVRE_MAGIQUE) {
            sortileges.add(sortilege);
        }
    }

    public Sortilege getSortilege(int num) {
        if (num >= 0 && num < sortileges.size()) {
            return sortileges.get(num);
        }
        return null;
    }

    @Override
    public String getNom() {
        return "livremagique";
    }

    @Override
    public int stackMax() {
        return ConstanteObjet.STACK_MAX_LIVRE_MAGIQUE;
    }

    @Override
    public double durabilitee() {
        return ConstanteObjet.DURABILITE_LIVRE_MAGIQUE;
    }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_LIVRE_MAGIQUE;
    }

    @Override
    public long delaie() {
        return this.getSortilege(0).delaie();
    }
}
