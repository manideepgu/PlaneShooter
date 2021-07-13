package com.example.planeshooter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PS_GameView extends View {

    Bitmap player_plane,enemy_plane,missile_bitmap,cloud_bitmap;
    int screen_width, screen_height,bg_width,bg_height,scaled_bg_width,scaled_bg_height,bgpixel;
    static int player_planex,player_planey,player_plane_movement;
    static float movex,missile_downx,missile_upx;
    Runnable runnable;
    PS_Missiles missile = new PS_Missiles();
    PS_EnemyPlanes enemyPlanes = new PS_EnemyPlanes();
    PS_ScoreBoard scoreboard = new PS_ScoreBoard();
    PS_GameOver gameOver = new PS_GameOver();
    PS_Clouds cloud=new PS_Clouds();

    Paint paint=new Paint();

    public PS_GameView(Context context) {
        super(context);

        player_plane= BitmapFactory.decodeResource(getResources(),R.drawable.plane1);
        enemy_plane= BitmapFactory.decodeResource(getResources(),R.drawable.plane2);
        player_plane=Bitmap.createScaledBitmap(player_plane,player_plane.getWidth()/2,player_plane.getHeight()/2,false);
        enemy_plane=Bitmap.createScaledBitmap(enemy_plane,enemy_plane.getWidth()/2,enemy_plane.getHeight()/2,false);
        missile_bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.missile);
        missile_bitmap=Bitmap.createScaledBitmap(missile_bitmap,missile_bitmap.getWidth()/8,missile_bitmap.getHeight()/8,false);
        cloud_bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.cloud1);

        screen_width= Resources.getSystem().getDisplayMetrics().widthPixels;
        screen_height = Resources.getSystem().getDisplayMetrics().heightPixels;

        Log.i("TAG","String indicator......");
        /*bg_width=bg.getWidth();
        bg_height=bg.getHeight();
        scaled_bg_width=bg_width*screen_height/bg_height;
        scaled_bg_height=screen_height-100;
        bg=Bitmap.createScaledBitmap(bg,scaled_bg_width,scaled_bg_height,false);
*/
        player_planex=screen_width/2-player_plane.getWidth()/2;
        movex=screen_width/2-player_plane.getWidth()/2;
        player_planey=screen_height-600;
        player_plane_movement=10;



        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                invalidate();
            }
        };

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.i("TAG","String indicator");
        paint.setColor(Color.rgb(151,252,249));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        if(missile.missilex.size()>0){
            for(int i=0;i<missile.missilex.size();i++) {
                canvas.drawBitmap(missile_bitmap, missile.missilex.get(i), missile.missiley.get(i), null);
            }
        }

        if(player_planex-movex>=player_plane_movement){
            player_planex=player_planex-player_plane_movement;
        }
        else if(movex-player_planex>=player_plane_movement){
            player_planex=player_planex+player_plane_movement;
        }
        else{
            player_planex= (int) movex;
        }
        canvas.drawBitmap(player_plane,player_planex,player_planey,null);
        for(int i=0;i<enemyPlanes.enemy_planex.size();i++){
            canvas.drawBitmap(enemy_plane,enemyPlanes.enemy_planex.get(i),enemyPlanes.enemy_planey.get(i),null);
        }

        if((missile_downx==missile_upx)&&(missile_downx!=0)){
            missile.new_missile(player_planex+player_plane.getWidth()/2-10,player_planey);
            missile_downx=0;
            missile_upx=0;
        }
        missile.missile_pos();
        enemyPlanes.enemy_plane_pos(enemy_plane,screen_width,screen_height);
        enemyPlanes.enemy_hit(enemy_plane,missile.missilex,missile.missiley);

        cloud.update_cloud(screen_width,screen_height);
        if(cloud.cloudx.size()>0) {
            for (int i = 0; i < cloud.cloudx.size(); i++) {
                canvas.drawBitmap(cloud_bitmap, cloud.cloudx.get(i), cloud.cloudy.get(i), null);
            }
        }

        scoreboard.update_scoreboard(enemyPlanes.enemy_hits,screen_width);

        canvas.drawRect(scoreboard.rect,scoreboard.paint2);

        canvas.drawText(scoreboard.scorecard,0,100,scoreboard.paint1);

        if(gameOver.game_over(enemy_plane,enemyPlanes.enemy_planex,enemyPlanes.enemy_planey,player_plane,player_planex,player_planey,screen_width,screen_height,movex,missile.missilex,missile.missiley,enemyPlanes.enemy_hits,getContext())) {
            runnable.run();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            movex= event.getX();

            if(movex>(screen_width-player_plane.getWidth())){
                movex=(screen_width-player_plane.getWidth());
            }
        }
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            missile_downx=event.getX();
        }
        if(event.getAction()==MotionEvent.ACTION_UP){
            missile_upx=event.getX();
        }

        return true;
    }
}
