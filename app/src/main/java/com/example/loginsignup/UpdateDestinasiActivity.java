package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDestinasiActivity extends AppCompatActivity {

    private Button backButton ;
    private Button submitButton ;
    private EditText nameDestination, locationDestination, photosDestinastion, descriptionDestinastion ;
    private DAODestinasi DAODestinasi = new DAODestinasi();

    private Destinasi destinasi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(view -> {
            Intent gotoHome = new Intent(UpdateDestinasiActivity.this, HomeActivity.class);
            startActivity(gotoHome);
            finish();
        });

        initComponent();
        String key = getIntent().getStringExtra("KEY");
        getDestinasi(key);
        submitButton.setOnClickListener(view -> update(key));
    }

    @SuppressLint("SetTextI18n")
    private void initComponent() {
        nameDestination = findViewById(R.id.addDestinastionName);
        locationDestination = findViewById(R.id.addLocation);
        photosDestinastion = findViewById(R.id.addPhoto);
        descriptionDestinastion = findViewById(R.id.addDescription);
        submitButton = findViewById(R.id.submitButton);
    }

    private void getDestinasi(String key) {
        DAODestinasi.getByKey(key).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) return;
            destinasi = task.getResult().getValue(Destinasi.class);
            nameDestination.setText(destinasi.getName());
            locationDestination.setText(destinasi.getLocation());
            photosDestinastion.setText(destinasi.getPhotos());
            descriptionDestinastion.setText(destinasi.getDesc());
        });
    }
    private void update(String key) {
        String namadestinasi = nameDestination.getText().toString();
        String lokasidestinasi = locationDestination.getText().toString();
        String fotodestinasi = photosDestinastion.getText().toString();
        String deskripsidestinasi = descriptionDestinastion.getText().toString();

        destinasi.setName(namadestinasi);
        destinasi.setLocation(lokasidestinasi);
        destinasi.setPhotos(fotodestinasi);
        destinasi.setDesc(deskripsidestinasi);

        DAODestinasi.update(key, destinasi).addOnSuccessListener(res -> {
            Toast.makeText(this, "Update Succeed", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomeActivity.class));
        }).addOnFailureListener(error ->
                Toast.makeText(this, "Update Failed: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show());
    }
}