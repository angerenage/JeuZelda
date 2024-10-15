package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Inventaire;

public abstract class ObjetConteneur<T extends Objet> extends Inventaire<T> implements Objet
{
    public ObjetConteneur(int taille)
    {
        super(taille);
    }


    public void echangerEmplacement(Joueur joueur, int caseVerouille, int caseSurvole)
    {
        int tailleInventaire = joueur.getSac().getTailleMax();
        Objet o1;
        Objet o2;

        if(caseSurvole==tailleInventaire)
            o1 = joueur.lacherObjetMainDroite();
        else if (caseSurvole==tailleInventaire+1)
            o1 = joueur.lacherObjetMainGauche();
        else
            o1 = retourneObjet(caseSurvole);
        if(caseVerouille==tailleInventaire)
            o2 = joueur.lacherObjetMainDroite();
        else if (caseVerouille==tailleInventaire+1)
            o2 = joueur.lacherObjetMainGauche();
        else
            o2 = retourneObjet(caseVerouille);

        if(o2!=null) {
            if (caseSurvole == tailleInventaire)
                joueur.setObjetMainDroite(o2);
            else if (caseSurvole == tailleInventaire + 1)
                joueur.setObjetMainGauche(o2);
            else
                getEmplacement(caseSurvole).ajoutObjet((T) o2);
        }
        if(o1!=null){
            if(caseVerouille==tailleInventaire)
                joueur.setObjetMainDroite(o1);
            else if(caseVerouille==tailleInventaire+1)
                joueur.setObjetMainGauche(o1);
            else
                getEmplacement(caseVerouille).ajoutObjet((T)o1);
        }

    }
}
