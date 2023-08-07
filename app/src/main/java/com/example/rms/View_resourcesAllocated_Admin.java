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
import Data_classes.View_allocated_resources_admin;
import Data_classes.View_resources_model;

public class View_resourcesAllocated_Admin extends AppCompatActivity {

    RecyclerView rcv;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref= database.getReference();
    View_ResourcesAllocated_Admin_Adapter view_resourcesAllocated_admin_adapter;
    ArrayList<View_allocated_resources_admin> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_allocated_resource_admin);

        rcv=findViewById(R.id.rcl_table2);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        final int[] i = {1};
        data=new ArrayList<>();
        rcv.setAdapter(view_resourcesAllocated_admin_adapter);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        ref.child("Allocated Resources").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
//                    System.out.println(ds);
                    View_allocated_resources_admin ham= ds.getValue(View_allocated_resources_admin.class);
                    ham.setId(ds.getKey());
//                    if(ds.child("email").getValue().toString().equals(user.getEmail()))
//                    {
                        data.add(ham);
//                        ham.setQunatity(ds.child("quantity").getValue().toString());
//                        ham.setCategory(ds.child("cat_name").getValue().toString());
//                        ham.setItem(ds.child("name").getValue().toString());
                        //ham.setId(i[0]++);
//                    }
                    view_resourcesAllocated_admin_adapter =new View_ResourcesAllocated_Admin_Adapter(View_resourcesAllocated_Admin.this,data);
                    rcv.setAdapter(view_resourcesAllocated_admin_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void onBackPressed() {
        Intent intent = new Intent(View_resourcesAllocated_Admin.this,AdminHomepage.class);
        startActivity(intent);
        finishAffinity();
        super.onBackPressed();
    }

}