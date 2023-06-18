package com.example.loginsignup;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.DesignTool;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DestinasiAdapter extends RecyclerView.Adapter<DestinasiAdapter.DestinasiViewHolder>{
    private Activity context;
    private ArrayList<Destinasi> localDataSet = new ArrayList<>();
    private DAODestinasi DAODestinasi;
    public DestinasiAdapter(Activity context, DAODestinasi DAODestinasi) {
        this.context = context;
        this.DAODestinasi = DAODestinasi;
    }
    @NonNull
    @Override
    public DestinasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View cardBuku = layoutInflater.inflate(R.layout.item_home, parent, false);
        return new DestinasiViewHolder(cardBuku, context, DAODestinasi);
    }
    public void setData (ArrayList<Destinasi>localDataSet) {
        this.localDataSet = localDataSet;
    }
    @Override
    public void onBindViewHolder(@NonNull DestinasiViewHolder holder, int position) {
        holder.bindView(localDataSet.get(position));
    }
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
    public static class DestinasiViewHolder extends RecyclerView.ViewHolder {
        private Activity context;
        private ImageButton updateButton, deleteButton;
        private TextView item_name, item_location;
        public DAODestinasi DAODestinasi;
        public DestinasiViewHolder(View view, Activity context, DAODestinasi DAODestinasi) {
            super(view);
            this.context = context;
            this.DAODestinasi = DAODestinasi;
            initComponent(view);
        }
        private void initComponent(View view) {
            item_name = view.findViewById(R.id.item_name);
            item_location = view.findViewById(R.id.item_location);
            updateButton = view.findViewById(R.id.btn_edit);
            deleteButton = view.findViewById(R.id.btn_delete);
        }
        public void bindView(Destinasi destinasi) {
            item_name.setText(destinasi.getName());
            item_location.setText(destinasi.getLocation());
            updateButton.setOnClickListener(view -> updateDestinasi(destinasi));
            deleteButton.setOnClickListener(view -> deleteDestinasi(destinasi));
        }
        private void updateDestinasi(Destinasi destinasi)
        {
            Intent intent = new Intent(context, UpdateDestinasiActivity.class);
            intent.putExtra("KEY", destinasi.getKey());
            context.startActivity(intent);
        }
        private void deleteDestinasi(Destinasi destinasi)
        {
            DAODestinasi.delete(destinasi.getKey()).addOnSuccessListener(res ->
                    Toast.makeText(context, "Delete Succeed",
                            Toast.LENGTH_LONG).show()).addOnFailureListener(error->
                    Toast.makeText(context,"Delete Failed: "+
                            error.getLocalizedMessage(),Toast.LENGTH_LONG).show());
        }
    }
}
