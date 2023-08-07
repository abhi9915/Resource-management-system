package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter_classes.Complaint_Adapter;
import Data_classes.Complaint_card_model;

public class View_complaint_admin extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    Complaint_Adapter complaint_adapter;
    ArrayList<Complaint_card_model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaint_admin);

        recyclerView=findViewById(R.id.rclview_complaint);
        database= FirebaseDatabase.getInstance().getReference("Complaint");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();


        recyclerView.setAdapter(complaint_adapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    System.out.println(dataSnapshot);
                    Complaint_card_model ccm= dataSnapshot.getValue(Complaint_card_model.class);

                    if(ccm.getW()==0) {
                        list.add(ccm);
                        ccm.setKey(dataSnapshot.getKey());
                    }

                    Complaint_Adapter complaint_adapter=new Complaint_Adapter(View_complaint_admin.this,list);
                    recyclerView.setAdapter(complaint_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}