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

import Adapter_classes.Adapter_RequestStatusHOD;
import Data_classes.AllResourcesAttribute;

public class RequestStatusHOD extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    Adapter_RequestStatusHOD adapter_requestStatusHOD;
    ArrayList<AllResourcesAttribute> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status_hod);

        recyclerView=findViewById(R.id.recl_view_hod);
        database= FirebaseDatabase.getInstance().getReference("All Resources");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        recyclerView.setAdapter(adapter_requestStatusHOD);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    System.out.println(dataSnapshot);
                    AllResourcesAttribute ccm = dataSnapshot.getValue(AllResourcesAttribute.class);
//                    System.out.println(dataSnapshot.getKey());
                    if(dataSnapshot.child("email").getValue().toString().equals(user.getEmail()))
                    {
//                    if (ccm.getY() == 1){
                        list.add(ccm);

                    }

                    Adapter_RequestStatusHOD complaint_adapter=new Adapter_RequestStatusHOD(RequestStatusHOD.this,list);
                    recyclerView.setAdapter(complaint_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}