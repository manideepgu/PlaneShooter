package com.example.planeshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button startgame;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startgame=findViewById(R.id.start);
        startgame.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        setContentView(new PS_GameView(this));
    }
}