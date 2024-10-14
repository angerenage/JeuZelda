package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.ConditionsDecorateur;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;

import java.util.ArrayList;

public class PatternCompositeStrategie implements Pattern
{
    private ArrayList<ConditionPatternDecorateur> patterns;
    private Pattern patternElse;


    public PatternCompositeStrategie(ArrayList<ConditionPatternDecorateur> patterns, Pattern patternElse)
    {
        this.patterns = new ArrayList<>(patterns);
        this.patternElse = patternElse;
    }





    @Override
    public void effectue()
    {
        if (!patterns.isEmpty())
        {
            boolean flag = false;

            for (int i = 0; i < patterns.size() && !flag; i++)
            {
                ConditionPatternDecorateur pattern = patterns.get(i);
                flag = pattern.conditionRespecter();

                if (flag)
                    pattern.effectue();
            }

            if (!flag)
                patternElse.effectue();
        }
    }



}
