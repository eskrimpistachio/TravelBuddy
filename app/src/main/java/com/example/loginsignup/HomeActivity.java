package com.example.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private  FloatingActionButton fab_form;
    private  FloatingActionButton fab_profile;
    private ImageButton ib_logout;
    private RecyclerView recyclerView;
    private DestinasiAdapter destinasiAdapter;

    private DAODestinasi DAODestinasi = new DAODestinasi();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        fab_form = findViewById(R.id.btn_form);
        fab_profile = findViewById(R.id.btn_profile);
        ib_logout = findViewById(R.id.btn_logOut);

        initComponent();
        initRecyclerView();
        loadData();

        fab_form.setOnClickListener(view -> {
            Intent gotoForm = new Intent(HomeActivity.this, FormActivity.class);
            startActivity(gotoForm);
            finish();
        });

        fab_profile.setOnClickListener(view -> {
            Intent gotoProfile = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(gotoProfile);
            finish();
        });

        ib_logout.setOnClickListener(view -> {
            Intent logout = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(logout);
            finish();
        });
    }

    private void initComponent() {
        recyclerView = findViewById(R.id.list_home);
    }
    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        destinasiAdapter = new DestinasiAdapter(this, DAODestinasi);
        recyclerView.setAdapter(destinasiAdapter);
    }
    private void loadData() {
        DAODestinasi.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot
                                             snapshot) {
                ArrayList<Destinasi> storage = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Destinasi destinasi = data.getValue(Destinasi.class);
                    destinasi.setKey(data.getKey());
                    storage.add(destinasi);
                }
                destinasiAdapter.setData(storage);
                destinasiAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}