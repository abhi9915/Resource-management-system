package com.example.rms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Data_classes.Request_resource_model;

public class Request_status_admin extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    RequestStatusAdminAdapter request_resource_adapter;
    ArrayList<Request_resource_model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status_admin);

        recyclerView=findViewById(R.id.rclview);
        database= FirebaseDatabase.getInstance().getReference("Requested Resources");

//        System.out.println(database+"******************************************");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();

        getRequest();

//        recyclerView.setAdapter(request_resource_adapter);


//        database.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot:snapshot.getChildren())
//                {
//                    System.out.println(dataSnapshot);
//                    Request_resource_model rrm= dataSnapshot.getValue(Request_resource_model.class);
//
//
////                    if(rrm!=null && rrm.editor==0 && articles.reviewer==1)
//                        list.add(rrm);
//                    System.out.println("++++++++++++++++++++++++"+dataSnapshot.getKey());
//                        rrm.setKey(dataSnapshot.getKey());
//
//                    request_resource_adapter =new Request_resource_adapter(Request_status_admin.this,list);
//                    recyclerView.setAdapter(request_resource_adapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    void getRequest()
    {
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Request_resource_model rrm= snapshot.getValue(Request_resource_model.class);


//                    if(rrm!=null && rrm.editor==0 && articles.reviewer==1)
                list.add(rrm);
                System.out.println("++++++++++++++++++++++++"+snapshot.getKey());
                rrm.setKey(snapshot.getKey());
                request_resource_adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Request_resource_model rrm= snapshot.getValue(Request_resource_model.class);


//                    if(rrm!=null && rrm.editor==0 && articles.reviewer==1)
                list.add(rrm);
                System.out.println("++++++++++++++++++++++++"+snapshot.getKey());
                rrm.setKey(snapshot.getKey());
                request_resource_adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        request_resource_adapter =new RequestStatusAdminAdapter(Request_status_admin.this,list);
        recyclerView.setAdapter(request_resource_adapter);
    }
}