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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Data_classes.Hod_alloted_model;
import Data_classes.Request_resource_model;

public class View_resources_Hod extends AppCompatActivity {

    RecyclerView rcv;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref= database.getReference();
    View_ResourcesHod_Adapter view_resourcesHod_adapter;
    ArrayList<Hod_alloted_model> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resource_hod);

        rcv=findViewById(R.id.rcl_table2);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        final int[] i = {1};
        data=new ArrayList<>();
        rcv.setAdapter(view_resourcesHod_adapter);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        ref.child("resources").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
//                    System.out.println(ds);
                    Hod_alloted_model ham= ds.getValue(Hod_alloted_model.class);
//                    if(ds.child("email").getValue().toString().equals(user.getEmail()))
//                    {
                        data.add(ham);
                        ham.setQunatity(ds.child("quantity").getValue().toString());
                        ham.setCategory(ds.child("cat_name").getValue().toString());
                        ham.setItem(ds.child("name").getValue().toString());
                        ham.setId(i[0]++);
//                    }
                    view_resourcesHod_adapter =new View_ResourcesHod_Adapter(View_resources_Hod.this,data);
                    rcv.setAdapter(view_resourcesHod_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}