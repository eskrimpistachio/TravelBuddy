package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FormActivity extends AppCompatActivity {

    private Button backButton ;
    private Button submitButton ;
    private EditText nameDestination, locationDestination, photosDestinastion, descriptionDestinastion ;
    private DAODestinasi DAODestinasi = new DAODestinasi();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(view -> {
            Intent gotoHome = new Intent(FormActivity.this, HomeActivity.class);
            startActivity(gotoHome);
            finish();
        });
        initComponent();
        submitButton.setOnClickListener(view -> add());
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        nameDestination = findViewById(R.id.addDestinastionName);
        locationDestination = findViewById(R.id.addLocation);
        photosDestinastion = findViewById(R.id.addPhoto);
        descriptionDestinastion = findViewById(R.id.addDescription);
        submitButton = findViewById(R.id.submitButton);
    }
    private void add() {
        String namadestinasi = nameDestination.getText().toString();
        String lokasidestinasi = locationDestination.getText().toString();
        String fotodestinasi = photosDestinastion.getText().toString();
        String deskripsidestinasi = descriptionDestinastion.getText().toString();

        Destinasi destinasi = new Destinasi(namadestinasi, lokasidestinasi, fotodestinasi, deskripsidestinasi);
        DAODestinasi.insert(destinasi).addOnSuccessListener(success-> {
            Toast.makeText(this, "Destinasi telah di tambahkan", Toast.LENGTH_LONG).show();
            startActivity(new Intent(FormActivity.this, HomeActivity.class));
        }).addOnFailureListener(error ->
                Toast.makeText(this, "Destinasi gagal ditambahkan : " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show());
    }
}