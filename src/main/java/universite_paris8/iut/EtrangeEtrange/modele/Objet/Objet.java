package universite_paris8.iut.EtrangeEtrange.modele.Objet;

public interface Objet extends Utilisable {
    String getNom();
    int stackMax();
    double durabilitee();
    int prixAchat();
}