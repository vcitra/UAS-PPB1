package com.example.uas.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.uas.Game.TicTacToe;
import com.example.uas.Menu.MenuActivity;
import com.example.uas.Menu.PilihSuit;
import com.example.uas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MenuFragment extends Fragment {

    ImageView imgSuit;
    ImageView imgTic;
    TextView txtNama;
    CardView cardSuit;
    CardView cardTic;
    Button btnSuit,btnTic;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    public MenuFragment() {
        // require a empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        cardSuit = view.findViewById(R.id.cardSuit);
        cardTic = view.findViewById(R.id.cardTic);
        imgSuit = view.findViewById(R.id.img_suit);
        btnSuit = view.findViewById(R.id.btnSuit);
        btnTic = view.findViewById(R.id.btnTic);
        txtNama = view.findViewById(R.id.txt_nama);

        firebaseAuth = FirebaseAuth.getInstance();

        // getting current user data
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");
        Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    // Retrieving Data from firebase
                    String name = "" + dataSnapshot1.child("name").getValue(String.class);
                    // setting data to our text view
                    txtNama.setText(name);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        cardSuit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent suit = new Intent(getActivity(), PilihSuit.class);
                startActivity(suit);
            }
        });

        btnSuit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent suit = new Intent(getActivity(), PilihSuit.class);
                startActivity(suit);
            }
        });

        cardTic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent tic = new Intent(getActivity(), TicTacToe.class);
                startActivity(tic);

            }
        });
        btnTic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent tic = new Intent(getActivity(), TicTacToe.class);
                startActivity(tic);

            }
        });

        return view;
    }



}