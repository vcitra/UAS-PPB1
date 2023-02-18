package com.example.uas.Game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas.AboutDeveloper;
import com.example.uas.MainActivity;
import com.example.uas.Menu.MenuActivity;
import com.example.uas.R;

public class SuitPlayer extends AppCompatActivity {
    ImageView batuPemain;
    ImageView guntingPemain;
    ImageView kertasPemain;
    ImageView batuLawan;
    ImageView guntingLawan;
    ImageView kertasLawan;
    TextView hasilSuit;
    ImageView ulang;
    ImageView imgClose;
    TextView userGame;
    int pilihSuit = 0;
    int hasil = 1;
    int pilihSuitLawan = 0;
    MediaPlayer game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suit_player);

        batuPemain = findViewById(R.id.batu1);
        guntingPemain = findViewById(R.id.gunting1);
        kertasPemain = findViewById(R.id.kertas1);
        batuLawan = findViewById(R.id.batu2);
        guntingLawan = findViewById(R.id.gunting2);
        kertasLawan = findViewById(R.id.kertas2);
        hasilSuit = findViewById(R.id.hasil);
        ulang = findViewById(R.id.refresh);
        imgClose = findViewById(R.id.img_close);
        userGame = findViewById(R.id.user);
        game = MediaPlayer.create(this, R.raw.game);
        game.start();

        batuPemain.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
              Log.d("MainActivity", "Pemain 1 memilih batu");
              pilihSuit = 1;
              selectSuit();
              batuPemain.setBackgroundColor(Color.parseColor("#FF709EB3"));
              Toast.makeText(SuitPlayer.this, "Pemain 1 memilih batu", Toast.LENGTH_SHORT).show();
          }
        });

        guntingPemain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Log.d("MainActivity",  "Pemain 1 memilih gunting");
            pilihSuit = 2;
           selectSuit();
            guntingPemain.setBackgroundColor(Color.parseColor("#FF709EB3"));
            Toast.makeText(SuitPlayer.this,"Pemain 1 memilih gunting",Toast.LENGTH_SHORT).show();
            }
        });

        kertasPemain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity",  "Pemain 1 memilih kertas");
                pilihSuit = 3;
                selectSuit();
                kertasPemain.setBackgroundColor(Color.parseColor("#FF709EB3"));
                Toast.makeText(SuitPlayer.this,"Pemain 1 memilih kertas",Toast.LENGTH_SHORT).show();
            }
        });

        batuLawan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity", "Pemain 2 memilih batu");
                pilihSuitLawan = 1;
                selectSuit();
                batuLawan.setBackgroundColor(Color.parseColor("#FF709EB3"));
                Toast.makeText(SuitPlayer.this, "Pemain 2 memilih batu", Toast.LENGTH_SHORT).show();
            }
        });

        guntingLawan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity", "Pemain 2 memilih batu");
                pilihSuitLawan = 2;
                selectSuit();
                guntingLawan.setBackgroundColor(Color.parseColor("#FF709EB3"));
                Toast.makeText(SuitPlayer.this, "Pemain 2 memilih gunting", Toast.LENGTH_SHORT).show();
            }
        });

        kertasLawan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity", "Pemain 2 memilih kertas");
                pilihSuitLawan = 3;
                selectSuit();
                guntingLawan.setBackgroundColor(Color.parseColor("#FF709EB3"));
                Toast.makeText(SuitPlayer.this, "Pemain 2 memilih kertas", Toast.LENGTH_SHORT).show();
            }
        });


        ulang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                closeDialog();
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.stop();
                finish();
            }
        });


    }

    private void closeDialog(){
        hapusPemain();
        hapusLawan();
        reset();
        pilihSuit = 0;
        pilihSuitLawan = 0;
    }

    // mengembalikan hasil suit textview ke awal
    private void reset(){
        hasilSuit.setTextColor(Color.parseColor("#F62415"));
        hasilSuit.setText("VS");
        hasilSuit.setBackgroundColor(0);
        hasilSuit.setTextSize(40F);
    }

    private void selectSuit(){
        final Handler handler = new Handler(Looper.getMainLooper());
        if(pilihSuitLawan==0) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hapusPemain();
                }
            }, 500);
        }

       // hapusLawan();
        if(pilihSuitLawan>0){
            switch(pilihSuit){
                case 1 : batuPemain.setBackgroundColor(Color.parseColor("#FF709EB3"));
                case 2 : guntingPemain.setBackgroundColor(Color.parseColor("#FF709EB3"));
                case 3 : kertasPemain.setBackgroundColor(Color.parseColor("#FF709EB3"));
            }
        }
        suit();
        //hapusPemain();
    }

    @Override
    public void onBackPressed() {
        game.stop();
    }





    private void showDialog(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View viewCustome = LayoutInflater.from(this).inflate(R.layout.dialog_winner,null,false);

        dialogBuilder.setView(viewCustome);


        final AlertDialog dialog = dialogBuilder.create();


        Button btnRefresh = viewCustome.findViewById(R.id.btn_refresh);
        Button btnMenu = viewCustome.findViewById(R.id.btn_menu);

        TextView txtResult = viewCustome.findViewById(R.id.txt_result);
        //TextView namaUser = intent.getStringExtra("DATA_USER_NAME")

        if(hasil==1) txtResult.setText("SERI");
        if(hasil==2)txtResult.setText("Pemain 1 MENANG!");
        if(hasil==3) txtResult.setText("Pemain 2 MENANG!");


        btnRefresh.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
              closeDialog();
              dialog.dismiss();
          }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.stop();
                finish();
        }
        });

        dialog.show();

    }

    // algoritma suit
    private void suit(){
        if((pilihSuit == 1 && pilihSuitLawan == 1)||(pilihSuit==2 && pilihSuitLawan==2) || (pilihSuit == 3 && pilihSuitLawan == 3)){
            Log.d("MainActivity", "DRAW");
            hasil = 1;
            hasilSuit.setBackgroundColor(Color.parseColor("#FF3F51B5"));
            hasilSuit.setTextColor(Color.parseColor("#FFE9EFE9"));
            hasilSuit.setTextSize(40F);
            hasilSuit.setText("DRAW");
            showDialog();
        }
        if((pilihSuit == 1 && pilihSuitLawan == 2)||(pilihSuit==2 && pilihSuitLawan==3) || (pilihSuit == 3 && pilihSuitLawan == 1)){
            Log.d("MainActivity", "Pemain 1 Menang !!!");
            hasil = 2;
            ubahText();
            hasilSuit.setText("Pemain 1 MENANG");
            showDialog();
        }
        if((pilihSuit == 1 && pilihSuitLawan == 3)||(pilihSuit==2 && pilihSuitLawan==1) || (pilihSuit == 3 && pilihSuitLawan == 2)){
            Log.d("MainActivity", "Pemain 2 Menang !!!");
            hasil = 3;
            ubahText();
            hasilSuit.setText("Pemain 2 MENANG");
            showDialog();
        }
    }

    // mengubah text vs kembali ke bentuk awal
    private void ubahText(){
        hasilSuit.setBackgroundColor(Color.parseColor("#FF4CAF50"));
        hasilSuit.setTextColor(Color.parseColor("#FFE9EFE9"));
        hasilSuit.setTextSize(15F);
    }

    // menghapus backgorund CardView pemain 1
    private void hapusPemain(){
        batuPemain.setBackgroundColor(0);
        guntingPemain.setBackgroundColor(0);
        kertasPemain.setBackgroundColor(0);
    }

    // menghapus backgorund CardView musuh
    private void hapusLawan(){
        batuLawan.setBackgroundColor(0);
        guntingLawan.setBackgroundColor(0);
        kertasLawan.setBackgroundColor(0);
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