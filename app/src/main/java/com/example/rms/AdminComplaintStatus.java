package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter_classes.AdminComplaintStatusAdapter;
import Data_classes.Complaint_card_model;

public class AdminComplaintStatus extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    AdminComplaintStatusAdapter adapter_hodComplaintStatus;
    ArrayList<Complaint_card_model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complaint_status);

        recyclerView=findViewById(R.id.adminComplaintStatusRecyclerView);
        database= FirebaseDatabase.getInstance().getReference("Complaint");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        recyclerView.setAdapter(adapter_hodComplaintStatus);
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    System.out.println(dataSnapshot);
                    Complaint_card_model ccm = dataSnapshot.getValue(Complaint_card_model.class);

//                    if (ccm.getY() == 1){
                        list.add(ccm);
                        ccm.setKey(dataSnapshot.getKey());



                    AdminComplaintStatusAdapter complaint_adapter=new AdminComplaintStatusAdapter(list,AdminComplaintStatus.this);
                    recyclerView.setAdapter(complaint_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}