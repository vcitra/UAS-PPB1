package com.example.uas.Game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import com.example.uas.Menu.MenuActivity;
import com.example.uas.Profile.EditProfilePage;
import com.example.uas.R;

import java.util.Random;

public class SuitNPC extends AppCompatActivity {

    MediaPlayer game;
    ImageView batuPemain;
    ImageView guntingPemain;
    ImageView kertasPemain;
    ImageView batuCom;
    ImageView guntingCom;
    ImageView kertasCom;
    TextView hasilSuit;
    ImageView ulang;
    ImageView imgClose;
    TextView namaPemain;
    int pilihSuit = 0;
    int hasil = 1;
    int pilihCom = new Random().nextInt(3 - 1 + 1) + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suit_npc);

        batuPemain = findViewById(R.id.batu1);
        guntingPemain = findViewById(R.id.gunting1);
        kertasPemain = findViewById(R.id.kertas1);
        batuCom = findViewById(R.id.batu2);
        guntingCom = findViewById(R.id.gunting2);
        kertasCom = findViewById(R.id.kertas2);
        hasilSuit = findViewById(R.id.hasil);
        ulang = findViewById(R.id.refresh);
        imgClose = findViewById(R.id.img_close);
        namaPemain = findViewById(R.id.user);

        game = MediaPlayer.create(this, R.raw.game);
        game.start();

        imgClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                game.stop();
                finish();
            }
        });

        batuPemain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity", "Pemain 1 memilih batu");
                selectSuit(batuPemain, 1);
                batuPemain.setBackgroundColor(Color.parseColor("#FF709EB3"));
                Toast.makeText(SuitNPC.this, "Pemain 1 memilih batu", Toast.LENGTH_SHORT).show();
            }
        });

        guntingPemain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity",  "Pemain 1 memilih gunting");
                selectSuit(guntingPemain, 2);
                guntingPemain.setBackgroundColor(Color.parseColor("#FF709EB3"));
                Toast.makeText(SuitNPC.this,"Pemain 1 memilih gunting",Toast.LENGTH_SHORT).show();
            }
        });

        kertasPemain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity",  "Pemain 1 memilih kertas");
                selectSuit(kertasPemain, 3);
                kertasPemain.setBackgroundColor(Color.parseColor("#FF709EB3"));
                Toast.makeText(SuitNPC.this,"Pemain 1 memilih kertas",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        game.stop();
    }

    private void closeDialog(){
        hapusPemain();
        hapusCom();
        reset();
        pilihSuit = 0;
        pilihCom = 0;
    }

    // mengembalikan hasil suit textview ke awal
    private void reset(){
        hasilSuit.setTextColor(Color.parseColor("#F62415"));
        hasilSuit.setText("VS");
        hasilSuit.setBackgroundColor(0);
        hasilSuit.setTextSize(40F);
    }

    // menampilkan pilihan hasil acak com
    private void com(){
        if(pilihSuit>0) {
            if (pilihCom == 1){
                Log.d("MainActivity", "Com memilih batu");
                batuCom.setBackgroundColor(Color.parseColor("#FF709EB3"));
                Toast.makeText(this,"CPU memilih batu", Toast.LENGTH_SHORT).show();
            }
            if (pilihCom == 2){
                Log.d("MainActivity", "Com memilih gunting");
                guntingCom.setBackgroundColor(Color.parseColor("#FF709EB3"));
                Toast.makeText(this,"CPU memilih gunting", Toast.LENGTH_SHORT).show();
            }
            if (pilihCom == 3){
                Log.d("MainActivity", "Com memilih kertas");
                kertasCom.setBackgroundColor(Color.parseColor("#FF709EB3"));
                Toast.makeText(this,"CPU memilih kertas", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void selectSuit(ImageView view, int pilihan){
        pilihSuit = pilihan;
        hapusCom();
        com();
        suit();
        hapusPemain();
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
        if(hasil==2)txtResult.setText("Player 1 MENANG!");
        if(hasil==3) txtResult.setText("CPU MENANG!");


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
        if((pilihSuit == 1 && pilihCom == 1)||(pilihSuit==2 && pilihCom==2) || (pilihSuit == 3 && pilihCom == 3)){
            Log.d("MainActivity", "DRAW");
            hasil = 1;
            hasilSuit.setBackgroundColor(Color.parseColor("#FF3F51B5"));
            hasilSuit.setTextColor(Color.parseColor("#FFE9EFE9"));
            hasilSuit.setTextSize(40F);
            hasilSuit.setText("DRAW");
            showDialog();
        }
        if((pilihSuit == 1 && pilihCom == 2)||(pilihSuit==2 && pilihCom==3) || (pilihSuit == 3 && pilihCom == 1)){
            Log.d("MainActivity", "Pemain 1 Menang !!!");
            hasil = 2;
            ubahText();
            hasilSuit.setText("Pemain 1 MENANG");
            showDialog();
        }
        if((pilihSuit == 1 && pilihCom == 3)||(pilihSuit==2 && pilihCom==1) || (pilihSuit == 3 && pilihCom == 2)){
            Log.d("MainActivity", "Pemain 2 Menang !!!");
            hasil = 3;
            ubahText();
            hasilSuit.setText("Pemain 2 MENANG");
            showDialog();
        }
    }

    private void ubahText(){
        hasilSuit.setBackgroundColor(Color.parseColor("#FF4CAF50"));
        hasilSuit.setTextColor(Color.parseColor("#FFE9EFE9"));
        hasilSuit.setTextSize(15F);
    }

    private void hapusPemain(){
        batuPemain.setBackgroundColor(0);
        guntingPemain.setBackgroundColor(0);
        kertasPemain.setBackgroundColor(0);
    }

    private void hapusCom(){
        batuCom.setBackgroundColor(0);
        guntingCom.setBackgroundColor(0);
        kertasCom.setBackgroundColor(0);
        pilihCom = new Random().nextInt(3 - 1 + 1) + 1;
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