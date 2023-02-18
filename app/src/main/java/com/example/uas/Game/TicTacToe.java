package com.example.uas.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uas.AboutDeveloper;
import com.example.uas.Menu.MenuActivity;
import com.example.uas.R;

public class TicTacToe extends AppCompatActivity {
    boolean gameActive = true;
    MediaPlayer game;


    // Player
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // arti state:
    //    0 - X
    //    1 - O
    //    2 - Null
    // array untuk posisi menang
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        game = MediaPlayer.create(this, R.raw.game);
        game.start();
    }
    @Override
    public void onBackPressed() {
        game.stop();
    }


    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        // memanggil fungsi reset game
        // ketika salah satu player menang atau semua kotak telah penuh
        if (!gameActive) {
            gameReset(view);
        }

        // if untuk klik image kosong
        if (gameState[tappedImage] == 2) {
            // menambah counter
            // disetiap tap
            counter++;

            // mengecek kotak terakhir
            if (counter == 9) {
                // reset game
                gameActive = false;
                gameReset(view);
            }

            // menandai posisi ini
            gameState[tappedImage] = activePlayer;

            // memberi efek gerakan pada image
            img.setTranslationY(-1000f);

            // mengubah active player
            // dari 0 sampai 1 atau 1 sampai 0
            if (activePlayer == 0) {
                // mengatur gambar x
                img.setImageResource(R.drawable.tic_x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);

                // mengubah status
                status.setText("O's Turn - Tap to play");
            } else {
                // mengatur gambar o
                img.setImageResource(R.drawable.tic_o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);

                // mengubah status
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;
        // mengecek apakah ada player yang menang
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2 && gameState[winPosition[1]] != 2) {
                flag = 1;

                // Somebody has won! - Find out who!
                String winnerStr;


                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                } else {
                    winnerStr = "O has won";
                }
                // Mengubah status informasi menang
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

                // memanggil fungsi game reset
                gameActive = false;
            }
        }
        // mengubah status jika match draw
        if (counter == 9 && flag == 0) {
            TextView status = findViewById(R.id.status);
            status.setText("Match Draw");
        }
    }

    // reset the game
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        counter = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        // menghapus semua gambar dari kotak dalam grid
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_option, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.home){
            startActivity(new Intent(this, MenuActivity.class));
            game.stop();
        } else if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, AboutDeveloper.class));
            game.stop();
        }

        return true;
    }
}