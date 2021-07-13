package com.example.planeshooter;

import java.util.ArrayList;

public class PS_Missiles {

    int missile_speed=20;

    ArrayList<Float> missilex=new ArrayList<>();
    ArrayList<Float> missiley=new ArrayList<>();

    public void new_missile(float missile_downx,float player_planey){
        missilex.add(missile_downx);
        missiley.add(player_planey);
    }

    public void missile_pos(){

        for(int i=0;i<missiley.size();i++){
            missiley.set(i,missiley.get(i)-missile_speed);
            if(missiley.get(i)<-200){
                missiley.remove(i);
                missilex.remove(i);

            }
        }
    }

}
