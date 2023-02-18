package com.example.uas.Menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.uas.Game.SuitNPC;
import com.example.uas.Game.SuitPlayer;
import com.example.uas.MainActivity;
import com.example.uas.Memo.DataUsers;
import com.example.uas.R;
import com.google.firebase.auth.FirebaseAuth;

public class PilihSuit extends AppCompatActivity {

    TextView txtPemain;
    CardView cardPemain;
    CardView cardNPC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_suit);

        cardPemain = findViewById(R.id.cardPlayer);
        cardNPC = findViewById(R.id.cardNPC);

        cardPemain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent player = new Intent(getBaseContext(), SuitPlayer.class);
                startActivity(player);
            }
        });

        cardNPC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent NPC = new Intent(getBaseContext(), SuitNPC.class);
                startActivity(NPC);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, AboutDeveloper.class));
        } else if (item.getItemId() == R.id.setting) {
            startActivity(new Intent(this, DataUsers.class));
        }else if (item.getItemId() == R.id.home) {
            startActivity(new Intent(this, MenuActivity.class));
        } else if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(PilihSuit.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(PilihSuit.this, MainActivity.class));
        }

        return true;

    }


}