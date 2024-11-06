package universite_paris8.iut.EtrangeEtrange.modele.Objet.Monnaie;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;

public class PieceOr extends Piece{

    public PieceOr(){
        super(1);
    }

    @Override
    public String getNom() {
        return "pieceor";
    }

    @Override
    public int stackMax() {
        return 64;
    }

    @Override
    public double durabilitee() {
        return 0;
    }

    @Override
    public int prixAchat() {
        return 0;
    }

    @Override
    public boolean utiliseePar(Entite entite) {
        return false;
    }
}
