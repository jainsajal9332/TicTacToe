package com.example.sajaljain.tictactoe;

import android.graphics.*;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,ne;
    char ch[][]=new char[4][4];
    int vis[][]=new int[4][4];
    char ch0,ch1,ch2;
    int turn,count1=0,count2=0,gameover,move_number;
    static int computer_playing;

    MediaPlayer player;
    String p1,p2;
    EditText e1,e2;


    TextView tv,tv1;
    Computer_move obj=new Computer_move();
    private Paint paint = new Paint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);









        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(1f);
        player = MediaPlayer.create(MainActivity.this, R.raw.r1);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        ne = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView);
        tv1 = (TextView) findViewById(R.id.textView2);

        init();

        ch0=' ';
        ch1='0';
        ch2='X';
        move_number=1;





        ne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                init();
                player = MediaPlayer.create(MainActivity.this, R.raw.r1);
                player.start();


                if(computer_playing==1)
                    if(move_number==1)
                    {
                        turn=1;
                        move_number^=1;
                        Random rand=new Random();
                        update(rand.nextInt(9)+1);
                    }
                    else {

                        move_number ^= 1;
                    }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vis[1][1]==0) {
                    man(1);

                    if(computer_playing==1)
                        computer();
                }

                player.start();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vis[1][2]==0) {
                    man(2);
                    if(computer_playing==1)
                        computer();

                }
                player.start();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vis[1][3]==0) {
                    man(3);
                    if(computer_playing==1)
                        computer();

                }
                player.start();

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vis[2][1]==0) {
                    man(4);
                    if(computer_playing==1)
                        computer();

                }
                player.start();

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vis[2][2]==0) {
                    man(5);
                    if(computer_playing==1)
                        computer();

                }
                player.start();

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vis[2][3]==0) {

                    man(6);

                    if(computer_playing==1)
                        computer();

                }
                player.start();

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vis[3][1]==0) {
                    man(7);
                    if(computer_playing==1)
                        computer();

                }
                player.start();

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vis[3][2]==0) {
                    man(8);
                    if(computer_playing==1)
                        computer();

                }
                player.start();

            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vis[3][3]==0) {

                    man(9);
                    if(computer_playing==1)
                        computer();

                }
                player.start();

            }
        });
    }



    public void man(int ans)
    {
        if (turn == 1) {
            turn = 2;
            updatex(ans);
        }else if (turn == 2) {
            turn=1;
            update(ans);

        }
    }
    public void computer()
    {

        if(gameover==0&&computer_playing==1)
            if(turn==1)
            {
                turn=2;
                updatex(obj.solve(ch,ch2));
            }
            else if(turn==2)
            {
                turn=1;
                update(obj.solve(ch,ch1));
            }

    }
    public void winning_move(int ans)
    {
        switch (ans)
        {
            case 1: b1.setBackgroundResource(R.drawable.match); break;
            case 2: b2.setBackgroundResource(R.drawable.match); break;
            case 3: b3.setBackgroundResource(R.drawable.match); break;
            case 4: b4.setBackgroundResource(R.drawable.match); break;
            case 5: b5.setBackgroundResource(R.drawable.match); break;
            case 6: b6.setBackgroundResource(R.drawable.match); break;
            case 7: b7.setBackgroundResource(R.drawable.match); break;
            case 8: b8.setBackgroundResource(R.drawable.match); break;
            case 9: b9.setBackgroundResource(R.drawable.match); break;
            default: break;
        }
    }
    public void init()
    {
        for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) ch[i][j] = ' ';
        for(int i=1;i<=3;i++)for(int j=1;j<=3;j++)vis[i][j]=0;
        gameover=0;
        turn = 1;
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        b9.setText("");


        b1.setBackgroundResource(R.drawable.b);
        b2.setBackgroundResource(R.drawable.b);
        b3.setBackgroundResource(R.drawable.b);
        b4.setBackgroundResource(R.drawable.b);
        b5.setBackgroundResource(R.drawable.b);
        b6.setBackgroundResource(R.drawable.b);
        b7.setBackgroundResource(R.drawable.b);
        b8.setBackgroundResource(R.drawable.b);
        b9.setBackgroundResource(R.drawable.b);
        return ;
    }
    public void updatex(int ans)
    {
        switch (ans) {
            case 1:
                b1.setText("X");
                ch[1][1]='X';
                vis[1][1]=1;
                endGame(ch2,1,1);
                break;
            case 2:
                b2.setText("X");
                ch[1][2]='X';
                vis[1][2]=1;
                endGame(ch2,1,2);
                break;
            case 3:
                b3.setText("X");
                ch[1][3]='X';
                vis[1][3]=1;
                endGame(ch2,1,3);
                break;
            case 4:
                b4.setText("X");
                ch[2][1]='X';
                vis[2][1]=1;
                endGame(ch2,2,1);
                break;
            case 5:
                b5.setText("X");
                ch[2][2]='X';
                vis[2][2]=1;
                endGame(ch2,2,2);
                break;
            case 6:
                b6.setText("X");
                ch[2][3]='X';
                vis[2][3]=1;
                endGame(ch2,2,3);
                break;
            case 7:
                b7.setText("X");
                ch[3][1]='X';
                vis[3][1]=1;
                endGame(ch2,3,1);
                break;
            case 8:
                b8.setText("X");
                ch[3][2]='X';
                vis[3][2]=1;
                endGame(ch2,3,2);
                break;
            case 9:
                b9.setText("X");
                ch[3][3]='X';
                vis[3][3]=1;
                endGame(ch2,3,3);
                break;
            default:
                ;

        }
    }
    public void update ( int ans)
    {
        switch (ans) {
            case 1:
                b1.setText("0");
                ch[1][1]='0';
                vis[1][1]=1;
                endGame(ch1,1,1);
                break;
            case 2:
                b2.setText("0");
                ch[1][2]='0';
                vis[1][2]=1;
                endGame(ch1,1,2);
                break;
            case 3:
                b3.setText("0");
                ch[1][3]='0';
                vis[1][3]=1;
                endGame(ch1,1,3);
                break;
            case 4:
                b4.setText("0");
                ch[2][1]='0';
                vis[2][1]=1;
                endGame(ch1,2,1);
                break;
            case 5:
                b5.setText("0");
                ch[2][2]='0';
                vis[2][2]=1;
                endGame(ch1,2,2);
                break;
            case 6:
                b6.setText("0");
                ch[2][3]='0';
                vis[2][3]=1;
                endGame(ch1,2,3);
                break;
            case 7:
                b7.setText("0");
                ch[3][1]='0';
                vis[3][1]=1;
                endGame(ch1,3,1);
                break;
            case 8:
                b8.setText("0");
                ch[3][2]='0';
                vis[3][2]=1;
                endGame(ch1,3,2);
                break;
            case 9:
                b9.setText("0");
                ch[3][3]='0';
                vis[3][3]=1;
                endGame(ch1,3,3);
                break;
            default:
                ;

        }
    }
    public void endGame (char chh,int i0,int j0) {
        boolean end=false;
        int b0=1,b1=1,b2=1,b3=1,bb=0;


        for(int k=1;k<=3;k++)
            if(ch[i0][k]!=chh)
            {b0=0;break;}


        for (int k = 1; k <= 3 && (b0 == 0); k++)
            if (ch[k][j0] != chh) {
                b1 = 0;
                break;
            }


        for (int k = 1; k <= 3 && (b0 == 0 && b1 == 0); k++)
            if (ch[k][k] != chh) {
                b2 = 0;
                break;
            }

        for (int k = 1; k <= 3 && (b0 == 0 && b1 == 0 && b2 == 0); k++)
            if (ch[k][3 - k + 1] != chh) {
                b3 = 0;
                break;
            }

        for(int i=1;i<=3;i++)
            for(int j=1;j<=3;j++)
                if(vis[i][j]==0)
                    bb=1;


        if((b0|b1|b2|b3)==1) {

            for (int i = 1; i <= 3; i++) for (int j = 1; j <= 3; j++) vis[i][j] = 1;
            if(b0==1)
            {
                i0=(i0-1)*3+1;
                winning_move(i0);winning_move(i0+1);winning_move(i0+2);
            }
            else if(b1==1)
            {

                winning_move(j0);winning_move(j0+3);winning_move(j0+6);
            }
            else if(b2==1)
            {
                winning_move(1);winning_move(5);winning_move(9);
            }

            else if(b3==1)
            {
                gameover=1;
                winning_move(3);winning_move(5);winning_move(7);
            }

            if(chh==ch1)
                 count1+=2;
            else count2+=2;

            if(chh==ch1)
            Toast.makeText(MainActivity.this,"Player 0 won" ,Toast.LENGTH_LONG).show();
            else Toast.makeText(MainActivity.this,"Player X won" ,Toast.LENGTH_LONG).show();
            player= MediaPlayer.create(MainActivity.this,R.raw.w);
            player.start();
        }
        else if(bb==0)
        {
            Toast.makeText(MainActivity.this,"Match Draw" ,Toast.LENGTH_LONG).show();

        }



        tv.setText("Player X : " + Integer.toString(count2));
        tv1.setText("Player 0 : " + Integer.toString(count1));
    }
}
