package com.example.uas.Memo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uas.AboutDeveloper;
import com.example.uas.MainActivity;
import com.example.uas.Menu.MenuActivity;
import com.example.uas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataUsers extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private MahasiswaAdapter adapter;
    private ArrayList<Komentar> mahasiswaList;
    private Button btnAdd;

    DatabaseReference dbKomen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_users);

        dbKomen = FirebaseDatabase.getInstance().getReference("comment");

        listView = findViewById(R.id.lv_list);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

        //list mahasiswa
        mahasiswaList = new ArrayList<>();

        //kode yang ditambahkan
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DataUsers.this, UpdateActivity.class);
                intent.putExtra(UpdateActivity.EXTRA_MAHASISWA, mahasiswaList.get(i));

                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        dbKomen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mahasiswaList.clear();

                for (DataSnapshot mahasiswaSnapshot : dataSnapshot.getChildren()) {
                    Komentar mahasiswa = mahasiswaSnapshot.getValue(Komentar.class);
                    mahasiswaList.add(mahasiswa);
                }

                MahasiswaAdapter adapter = new MahasiswaAdapter(DataUsers.this);
                adapter.setMahasiswaList(mahasiswaList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DataUsers.this, "Terjadi kesalahan.", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(DataUsers.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(DataUsers.this, MainActivity.class));
        }

        return true;

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add) {
            Intent intent = new Intent(DataUsers.this, CreateMemo.class);
            startActivity(intent);
        }
    }
}