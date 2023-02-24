package be.kdg.yahtzeefx.model.scorings;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Scoring;

import java.util.Map;

public class SmallStreet extends Scoring {
    @Override
    public int getScore(Dice[] dice) {
        return 30;
    }

    @Override
    public boolean isValid(Dice[] dice) {
        Map<Integer, Integer> frequenty = getFrequenty(dice);
        if(frequenty.size() >=4){
            for (int i = 1; i<3; i++){
                if(frequenty.get(i)!=null&&frequenty.get(i+1)!=null&&frequenty.get(i+2)!=null&&frequenty.get(i+3)!=null){
                    return true;
                }
            }
        }
        return false;
    }
}
