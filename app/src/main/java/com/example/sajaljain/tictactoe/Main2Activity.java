package com.example.sajaljain.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    MediaPlayer player;
    Button btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player= MediaPlayer.create(Main2Activity.this,R.raw.r1);
                        player.start();
                        MainActivity.computer_playing=0;
                        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
        btn3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player= MediaPlayer.create(Main2Activity.this,R.raw.r1);
                        player.start();
                        MainActivity.computer_playing=1;
                        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}

