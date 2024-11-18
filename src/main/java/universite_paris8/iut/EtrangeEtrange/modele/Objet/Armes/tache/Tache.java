package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.tache;

public abstract class Tache {
    private final long msCreation;
    private final long delaie;

    public Tache(long delaie) {
        this.msCreation = System.currentTimeMillis();
        this.delaie = delaie;
    }


    public boolean execute() {
        boolean estExectuer = delaieRespecter();

        if (estExectuer) tache();

        return estExectuer;
    }


    private boolean delaieRespecter() {
        return System.currentTimeMillis() - msCreation > delaie;
    }


    public abstract void tache();


}
