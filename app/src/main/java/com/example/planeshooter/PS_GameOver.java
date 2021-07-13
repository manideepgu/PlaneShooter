package com.example.planeshooter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PS_GameOver {
    static int hiscore;

    public boolean game_over(Bitmap enemy_plane, ArrayList enemy_planex, ArrayList enemy_planey,
                             Bitmap player_plane, int player_planex, int player_planey,
                             int screen_width, int screen_height, float movex, ArrayList missilex, ArrayList missiley, int enemy_hits, Context context) {

        SharedPreferences shrd = context.getSharedPreferences("hiscore", MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();

        for (int i = 0; i < enemy_planex.size(); i++) {
            if (((int) enemy_planey.get(i) > screen_height) || ((((int) enemy_planey.get(i) + enemy_plane.getHeight()) > player_planey) && ((int) enemy_planex.get(i) + enemy_plane.getWidth() > player_planex) && ((int) enemy_planex.get(i) < (player_planex + player_plane.getWidth())))) {
                Intent reset = new Intent(context,PS_Reset.class);
                Log.i("TAG","Inside Gameover-----------------"+String.valueOf(PS_EnemyPlanes.enemy_hits));

                SharedPreferences getShared = context.getSharedPreferences("hiscore", MODE_PRIVATE);
                hiscore = getShared.getInt("hiscore",0);

                if(PS_EnemyPlanes.enemy_hits>hiscore){
                    editor.putInt("str1", PS_EnemyPlanes.enemy_hits);
                    editor.apply();
                    hiscore=PS_EnemyPlanes.enemy_hits;
                }


                reset.putExtra("score",PS_EnemyPlanes.enemy_hits);
                context.startActivity(reset);

                PS_GameView.player_planex=screen_width/2-player_plane.getWidth()/2;
                PS_GameView.movex=screen_width/2-player_plane.getWidth()/2;
                enemy_planex.clear();
                enemy_planey.clear();
                missilex.clear();
                missiley.clear();
                PS_EnemyPlanes.enemy_hits=0;
            }
        }
        return true;
    }
}
