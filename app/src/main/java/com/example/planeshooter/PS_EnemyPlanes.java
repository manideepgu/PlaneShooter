package com.example.planeshooter;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class PS_EnemyPlanes {
    ArrayList<Integer> enemy_planex=new ArrayList<>();
    ArrayList<Integer> enemy_planey=new ArrayList<>();
    Random random=new Random();
    int enemy_plane_speed=7;
    static int enemy_hits;

    public void update_enemy_plane_speed(){
        switch(enemy_hits){
            case 0:
                enemy_plane_speed=7;
                break;
            case 15:
                enemy_plane_speed=10;
                break;
            case 30:
                enemy_plane_speed=13;
                break;
            case 45:
                enemy_plane_speed=16;
                break;
            case 60:
                enemy_plane_speed=19;
                break;
        }
    }

    public void new_enemy_plane(Bitmap enemy_plane, int screen_width){
        if(enemy_planex.size()==0){
            enemy_planey.add(enemy_plane.getHeight() * (-1));
            enemy_planex.add(random.nextInt(screen_width - enemy_plane.getWidth()));
        }
        else{
            if(random.nextInt(20)==2){

                if(enemy_planey.get(enemy_planey.size()-1)>50) {
                    Log.i("TAG","Inside Enemy");
                    enemy_planey.add(enemy_plane.getHeight() * (-1));
                    enemy_planex.add(random.nextInt(screen_width - enemy_plane.getWidth()));
                }
            }
        }
    }

    public void enemy_plane_pos(Bitmap enemy_plane, int screen_width, int screen_height) {
        new_enemy_plane(enemy_plane, screen_width);
        update_enemy_plane_speed();
        for(int i=0;i<enemy_planex.size();i++){
            enemy_planey.set(i,enemy_planey.get(i)+enemy_plane_speed);
            if(enemy_planey.get(i)>screen_height+enemy_plane.getHeight()){
                enemy_planex.remove(i);
                enemy_planey.remove(i);
            }
        }
    }


    public void enemy_hit(Bitmap enemy_plane,ArrayList<Float> missilex, ArrayList<Float> missiley) {
        if((missilex.size()>0)&&(enemy_planex.size()>0)){
            for(int i=0;i<missilex.size();i++){
                for(int j=0;j<enemy_planex.size();j++){
                    Log.i("TAG",String.valueOf(missilex.size())+String.valueOf(enemy_planex));
                    if(missilex.size()>0){
                        if(missilex.get(i)>enemy_planex.get(j)) {
                            if(missilex.get(i)<(enemy_planex.get(j)+enemy_plane.getWidth())) {
                                if (missiley.get(i) < (enemy_planey.get(j) + enemy_plane.getHeight())) {
                                    missilex.remove(i);
                                    missiley.remove(i);
                                    enemy_planex.remove(j);
                                    enemy_planey.remove(j);
                                    enemy_hits++;
                                    Log.i("TAG", "#############################                      " + String.valueOf(enemy_hits));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
