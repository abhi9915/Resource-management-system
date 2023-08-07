package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter_classes.Hod_alloted_Adapter;
import Data_classes.View_allocated_resources_admin;

public class My_alloted_hod extends AppCompatActivity {

    RecyclerView rcv;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref= database.getReference();
    Hod_alloted_Adapter hod_alloted_adapter;
    ArrayList<View_allocated_resources_admin> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alloted_hod);

        rcv=findViewById(R.id.rcl_table);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        final int[] i = {1};
        data=new ArrayList<>();
        rcv.setAdapter(hod_alloted_adapter);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        ref.child("Allocated Resources").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    System.out.println(ds);
                    View_allocated_resources_admin ham= ds.getValue(View_allocated_resources_admin.class);
                    if(ds.child("email").getValue().toString().equals(user.getEmail()))
                    {
                        data.add(ham);
                        ham.setItem(ds.child("item").getValue().toString());
                        ham.setLocation(ds.child("location").getValue().toString());
                        String str1=ds.child("Id").getValue().toString();
                        System.out.println(str1.charAt(1));


                        ham.setId(str1);
                        //ham.setQunatity(ds.child("quantity").getValue().toString());
//                        ham.setSno(i[0]++);
                    }
                    hod_alloted_adapter =new Hod_alloted_Adapter(My_alloted_hod.this,data);
                    rcv.setAdapter(hod_alloted_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(My_alloted_hod.this,HodHomepage.class);
        startActivity(intent);
        finishAffinity();
        super.onBackPressed();
    }

}