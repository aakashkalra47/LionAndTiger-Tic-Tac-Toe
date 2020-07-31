package com.example.liionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        enum player {ONE, TWO, NO};

    player currentPlayer = player.ONE;
    player[] playerChoices = new player[9];
    private GridLayout grid;
    boolean gameOver =false;
    int[][] winner = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 9; i++)
            playerChoices[i] = player.NO;

       btn = (Button) findViewById(R.id.btnReset);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(v);
            }
        });
    }
    public void buttonIsTapped(View imageView) {
        ImageView imageTapped = (ImageView) imageView;

        int t = Integer.parseInt(imageTapped.getTag().toString()) - 1;
        if (playerChoices[t] == player.NO && !gameOver) {
            imageTapped.setTranslationX(-2000);
            playerChoices[t] = currentPlayer;
            if (currentPlayer == player.ONE) {
                imageTapped.setImageResource(R.drawable.lion);
                currentPlayer = player.TWO;
            } else {
                imageTapped.setImageResource(R.drawable.tiger);
                currentPlayer = player.ONE;
            }

            imageTapped.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(250);
            Context context;
            //Toast.makeText(this, imageTapped.getTag().toString(), Toast.LENGTH_SHORT).show();
            for (int[] arr : winner) {
                if (playerChoices[arr[0]] == playerChoices[arr[1]] && playerChoices[arr[1]] == playerChoices[arr[2]] && playerChoices[arr[0]] != player.NO) {
                    player win;
                    gameOver = true;
                    if (currentPlayer == player.ONE)
                        win = player.TWO;
                    else
                        win = player.ONE;
                    Toast.makeText(this, "Player " + win.toString() + " wins", Toast.LENGTH_LONG).show();
                }

            }
            int i;
            for (i = 0; i < 9; i++) {
                if (playerChoices[i] == player.NO) {
                    break;
                }
            }
            if (i == 9)
                gameOver = true;
            grid = findViewById(R.id.gridLayout);
            if (gameOver) {
                btn.setVisibility(View.VISIBLE);
            }
        }
    }
    public void reset(View v)
    {

        for(int i=0;i<grid.getChildCount();i++) {
            ImageView iV=(ImageView)grid.getChildAt(i);
            iV.setImageDrawable(null);
            iV.setAlpha(0.2f);
            playerChoices[i] = player.NO;
        }
        btn.setVisibility(View.GONE);
        gameOver=false;
        currentPlayer=player.ONE;
    }
}


