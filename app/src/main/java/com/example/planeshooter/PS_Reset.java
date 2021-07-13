package com.example.planeshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PS_Reset extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps_reset);

        Button br=findViewById(R.id.ResetButton);
        TextView tv=findViewById(R.id.ScoreBoard);
        Log.i("TAG","Inside Reset-----------------"+String.valueOf(PS_EnemyPlanes.enemy_hits));


        String text="SCORE: "+String.valueOf(getIntent().getIntExtra("score",0))+" hits";

        tv.setText(text);

        br.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        this.finish();
    }
}