package com.example.uas.Memo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uas.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateMemo extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNama, edtKomen;
    private Button btnSave;

    private Komentar data;

    DatabaseReference mDatabase;

    public CreateMemo() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memo);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edtNama = findViewById(R.id.edt_nama);
        edtKomen = findViewById(R.id.edt_nim);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);

        data = new Komentar();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_save) {
            saveData();
        }

    }

    private void saveData()
    {
        String nama = edtNama.getText().toString().trim();
        String komen = edtKomen.getText().toString().trim();

        boolean isEmptyFields = false;

        if (TextUtils.isEmpty(nama)) {
            isEmptyFields = true;
            edtNama.setError("Field ini tidak boleh kosong");
        }

        if (TextUtils.isEmpty(komen)) {
            isEmptyFields = true;
            edtKomen.setError("Field ini tidak boleh kosong");
        }

        if (! isEmptyFields) {

            Toast.makeText(CreateMemo.this, "Saving Data...", Toast.LENGTH_SHORT).show();

            DatabaseReference dbKomen = mDatabase.child("comment");

            String id = dbKomen.push().getKey();
            data.setId(id);
            data.setKomen(komen);
            data.setNama(nama);

            //insert data
            dbKomen.child(id).setValue(data);

            finish();

        }
    }
}