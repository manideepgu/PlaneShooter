package com.example.planeshooter;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class PS_ScoreBoard {
    RectF rect;
    String scorecard;
    Paint paint1 = new Paint();
    Paint paint2 = new Paint();

    public void update_scoreboard(int score, int screen_width) {
        rect = new RectF(0,0,screen_width,100);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.YELLOW);

        scorecard="HITS: "+String.valueOf(score)+"/Hi Score: "+String.valueOf(PS_GameOver.hiscore);

        paint1.setColor(Color.rgb(151,0,0));
        paint1.setTextSize(100);
        paint1.setStrokeWidth(20);

    }


}
