package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegeOrbe;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegePluitDeFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Support.SortilegeDeSoins;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;


import java.util.ArrayList;

public class LivreMagique implements Arme, Rechargeable {

    private ArrayList<Sortilege> sortileges;
    private long derniereApelle;
    private boolean peutTirer;

    public LivreMagique() {
        this.sortileges = new ArrayList<>();
        this.sortileges.add(new SortilegeOrbe());
        this.sortileges.add(new SortilegePluitDeFleche());
        this.sortileges.add(new SortilegeDeSoins());


        this.peutTirer = true;
        this.derniereApelle = 0;
    }


    @Override
    public void utilise(Entite entite) {
        if (peutTirer) {
            Sortilege sortilege = this.sortileges.get(0);
            sortilege.utilise(entite);
            this.derniereApelle = System.currentTimeMillis();
            entite.getMonde().ajoutRechargeable(this);
            peutTirer = false;
        }
    }

    public void ajoutSortilege(Sortilege sortilege) {
        if (sortileges.size() + 1 < ConstanteObjet.SORT_MAXIMUM_LIVRE_MAGIQUE)
            sortileges.add(sortilege);
    }

    public Sortilege getSortilege(int num) {
        Sortilege sortilege = null;

        if (num >= 0 && num < sortileges.size())
            sortilege = sortileges.get(num);

        return sortilege;
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

    @Override
    public boolean cooldown() {
        boolean actionFait = false;
        long apelle = System.currentTimeMillis();

        if (apelle - derniereApelle >= delaie()) {
            System.out.println("refait");
            this.derniereApelle = 0;
            this.peutTirer = true;
            actionFait = true;
        }
        return actionFait;
    }
}
