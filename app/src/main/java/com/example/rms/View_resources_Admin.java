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

import Data_classes.Hod_alloted_model;
import Data_classes.Request_resource_model;
import Data_classes.View_resources_model;

public class View_resources_Admin extends AppCompatActivity {

    RecyclerView rcv;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref= database.getReference();
    View_ResourcesAdmin_Adapter view_resourcesAdmin_adapter;
    ArrayList<View_resources_model> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resource_admin);

        rcv=findViewById(R.id.rcl_table2);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        final int[] i = {1};

        data=new ArrayList<>();
        rcv.setAdapter(view_resourcesAdmin_adapter);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        ref.child("resources").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for(DataSnapshot ds:snapshot.getChildren())
                {
                   // System.out.println(ds);

                    long count = 0;
                    for(DataSnapshot i:ds.getChildren())
                    {
                         count=ds.getChildrenCount();

                    }
                    //System.out.println(chaircount);
                    //System.out.println(count);
                    View_resources_model ham= ds.getValue(View_resources_model.class);
//                    if(ds.child("email").getValue().toString().equals(user.getEmail()))
//                    {
                        String str=String.valueOf(count);
                        ham.setQuantity(str);
                        ham.setName(ds.getKey().toString());
                        if(ds.getKey().toString().equals("Chair") || ds.getKey().toString().equals("Table"))
                            ham.setCat_name("furniture");
                        else
                            ham.setCat_name("electric");
//

                       // ham.setCat_name(ds.child("cat_name").getValue().toString());
                        data.add(ham);
//                        ham.setQunatity(ds.child("quantity").getValue().toString());
//                        ham.setCategory(ds.child("cat_name").getValue().toString());
//                        ham.setItem(ds.child("name").getValue().toString());
                        ham.setId(i[0]++);
//                    }
                    view_resourcesAdmin_adapter =new View_ResourcesAdmin_Adapter(View_resources_Admin.this,data);
                    rcv.setAdapter(view_resourcesAdmin_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}