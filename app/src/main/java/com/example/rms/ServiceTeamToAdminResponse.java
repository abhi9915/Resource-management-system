package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter_classes.Adapter_ServiceTeamToAdminResponse;
import Data_classes.Complaint_card_model;

public class ServiceTeamToAdminResponse extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    Adapter_ServiceTeamToAdminResponse adapter_ServiceTeamToAdminResponse;
    ArrayList<Complaint_card_model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serviceteamtoadminresponse);

        recyclerView = findViewById(R.id.rclview_complaint_ST);
        database = FirebaseDatabase.getInstance().getReference("Complaint");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();


        recyclerView.setAdapter(adapter_ServiceTeamToAdminResponse);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    System.out.println(dataSnapshot);
                    Complaint_card_model ccm = dataSnapshot.getValue(Complaint_card_model.class);

                    if ((ccm.getX() == 1 || ccm.getX() == 2) && ccm.getY() == 0) {
                        list.add(ccm);
                        ccm.setKey(dataSnapshot.getKey());
                    }

                    Adapter_ServiceTeamToAdminResponse complaint_adapter = new Adapter_ServiceTeamToAdminResponse(ServiceTeamToAdminResponse.this, list);
                    recyclerView.setAdapter(complaint_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    public void onBackPressed() {
        Intent intent = new Intent(ServiceTeamToAdminResponse.this,AdminHomepage.class);
        startActivity(intent);
        finishAffinity();
        super.onBackPressed();
    }
}