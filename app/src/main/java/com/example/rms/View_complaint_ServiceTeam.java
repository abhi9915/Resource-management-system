package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter_classes.Complaint_Adapter_ServiceTeam;
import Data_classes.Complaint_card_model;

public class View_complaint_ServiceTeam extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    Complaint_Adapter_ServiceTeam complaint_adapter_serviceTeam;
    ArrayList<Complaint_card_model> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaint_serviceteam);

        recyclerView=findViewById(R.id.rclview_complaint_ST);
        database= FirebaseDatabase.getInstance().getReference("Complaint");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();



        recyclerView.setAdapter(complaint_adapter_serviceTeam);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    System.out.println(dataSnapshot);
                    Complaint_card_model ccm = dataSnapshot.getValue(Complaint_card_model.class);

                    if (ccm.getW() == 1 && ccm.getX()==0 && ccm.getY()==0){
                        list.add(ccm);
                    ccm.setKey(dataSnapshot.getKey());
                    }

                    Complaint_Adapter_ServiceTeam complaint_adapter=new Complaint_Adapter_ServiceTeam(View_complaint_ServiceTeam.this,list);
                    recyclerView.setAdapter(complaint_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.hod_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.logoutid:
                FirebaseAuth auth=FirebaseAuth.getInstance();
                auth.signOut();
                Intent i=new Intent(View_complaint_ServiceTeam.this,Loginpage.class);
                startActivity(i);
                finishAffinity();
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}