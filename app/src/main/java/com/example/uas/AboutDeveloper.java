package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.uas.Fragment.ContactFragment;
import com.example.uas.Fragment.MenuFragment;
import com.example.uas.Fragment.PersonalFragment;
import com.example.uas.Fragment.ProfileFragment;
import com.example.uas.Memo.DataUsers;
import com.example.uas.Menu.MenuActivity;
import com.example.uas.Profile.EditProfilePage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AboutDeveloper extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    ContactFragment contactFragment = new ContactFragment();
    PersonalFragment personalFragment = new PersonalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.contact);
    }

    //memanggil fragment berdasark
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, contactFragment).commit();
                return true;

            case R.id.personal:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, personalFragment).commit();
                return true;

        }
        return false;
    }

    //memanggil option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    //intent ketika option menu diklik
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting) {
            startActivity(new Intent(this, DataUsers.class));
        }else if (item.getItemId() == R.id.home) {
            startActivity(new Intent(this, MenuActivity.class));
        }else if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(AboutDeveloper.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(AboutDeveloper.this, MainActivity.class));
        }

        return true;
    }
}