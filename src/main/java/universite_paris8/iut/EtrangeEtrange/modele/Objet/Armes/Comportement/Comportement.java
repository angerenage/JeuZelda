package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Comportement;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;

public interface Comportement
{
    void finit();
    void lancer(Entite utilisateur);
}
