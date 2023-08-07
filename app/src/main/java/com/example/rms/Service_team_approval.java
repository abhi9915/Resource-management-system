package com.example.rms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Adapter_classes.Adapter_ServiceTeamApproval;

public class Service_team_approval extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    Adapter_ServiceTeamApproval adapter_serviceTeamApproval;
    ArrayList<UserDetails> list;
    Adapter_ServiceTeamApproval complaint_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_team_approval);

        recyclerView=findViewById(R.id.service_rcl_view);
        database= FirebaseDatabase.getInstance().getReference("ServiceTeam");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        recyclerView.setAdapter(adapter_serviceTeamApproval);

        //**********

        getList();


        //*********
//
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
//                    //System.out.println(dataSnapshot);
//                    UserDetails ccm = dataSnapshot.getValue(UserDetails.class);
//
//                    if(dataSnapshot.child("approved").getValue().toString().equals("0"))
//                    {
//                        list.add(ccm);
//                        ccm.setId(dataSnapshot.getKey());
//                    }
//
//                    Adapter_ServiceTeamApproval complaint_adapter=new Adapter_ServiceTeamApproval(Service_team_approval.this,list);
//                    recyclerView.setAdapter(complaint_adapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    void getList()
    {

        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                UserDetails ccm = snapshot.getValue(UserDetails.class);

                if(snapshot.child("approved").getValue().toString().equals("0"))
                {
                    list.add(ccm);
                    ccm.setId(snapshot.getKey());
                    complaint_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                UserDetails ccm = snapshot.getValue(UserDetails.class);
//
//                if(snapshot.child("approved").getValue().toString().equals("0"))
//                {
//                    list.add(ccm);
//                    ccm.setId(snapshot.getKey());
//                    complaint_adapter.notifyDataSetChanged();
//                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                UserDetails ccm = snapshot.getValue(UserDetails.class);
//
//                if(snapshot.child("approved").getValue().toString().equals("0"))
//                {
//                    list.add(ccm);
//                    ccm.setId(snapshot.getKey());
//                    complaint_adapter.notifyDataSetChanged();
//                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        complaint_adapter=new Adapter_ServiceTeamApproval(Service_team_approval.this,list);
        recyclerView.setAdapter(complaint_adapter);
        System.out.println(list.size());
        list.clear();
        System.out.println(list.size());
    }
}