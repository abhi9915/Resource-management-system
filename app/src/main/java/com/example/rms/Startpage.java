package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Startpage extends AppCompatActivity {


    Button loginAdmin,loginHod,loginservice;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference=database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);

        loginAdmin=findViewById(R.id.buttonAdmin);
        loginHod=findViewById(R.id.buttonHOD);
        loginservice=findViewById(R.id.buttonService);


        loginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Startpage.this,Loginpage.class);
                startActivity(i);
                finish();
            }
        });

        loginHod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Startpage.this,Loginpage.class);
                startActivity(i);
                finish();
            }
        });

        loginservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Startpage.this,Loginpage.class);
                startActivity(i);
                finish();
            }
        });

    }
}