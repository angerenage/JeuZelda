package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

public abstract class Tache
{
    private long msCreation,delaie;

    public Tache(long delaie){
        this.msCreation = System.currentTimeMillis();
        this.delaie = delaie;
    }



    public boolean execute()
    {
        boolean estExectuer = delaieRespecter();

        if (estExectuer)
            tache();

        return estExectuer;
    }


    private boolean delaieRespecter(){
        return System.currentTimeMillis() - msCreation > delaie;
    }


    public abstract void tache();




}
