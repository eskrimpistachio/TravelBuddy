package com.example.loginsignup;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class DAODestinasi {

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    public DAODestinasi() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public Task<Void> insert(Destinasi destinasi){
        return
                databaseReference.push().setValue(destinasi);
    }
    public Task<Void> update(String key, Destinasi destinasi){
        return databaseReference.child(key).setValue(destinasi);
    }
    public Task<Void> delete(String key){
        return databaseReference.child(key).removeValue();
    }
    public Query get(){
        return databaseReference.orderByKey();
    }
    public Task<DataSnapshot> getByKey(String key){
        return databaseReference.child(key).get();
    }
}
