package universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Guerrisable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.constantes.ConstanteObjet;

public class Potion implements Guerrisable, Objet {
    private int durabilitee;

    public Potion() {
        this.durabilitee = ConstanteObjet.DURABILITE_POTION;
    }

    @Override
    public double restoration() {
        return ConstanteObjet.RESTORATION;
    }

    @Override
    public String getNom() {
        return "potion";
    }

    @Override
    public int stackMax() {
        return ConstanteObjet.STACK_MAX_POTION;
    }

    @Override
    public double durabilitee() {
        System.out.println(durabilitee);
        return durabilitee;
    }

    @Override
    public int prixAchat() {
        return ConstanteObjet.PRIX_ACHAT_POTION;
    }

    @Override
    public boolean utiliseePar(Entite entite) {
        if (durabilitee > 0) {
            entite.soigner(restoration());
            this.durabilitee--;
        }

        return durabilitee <= 0;
    }
}
