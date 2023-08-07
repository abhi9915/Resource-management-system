package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Loginpage extends AppCompatActivity {

    TextView greet,tv;
    EditText email,password;
    Button signin,signup;

    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference=database.getReference();

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        greet = findViewById(R.id.greeting);
        tv = findViewById(R.id.textview2);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        signin = findViewById(R.id.buttonSignin);
        signup = findViewById(R.id.buttonSignup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Loginpage.this,Signup_page.class);
                startActivity(i);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = email.getText().toString();
                String pass = password.getText().toString();
                auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


//                            if (mail.contains("hod")) {
//                                Intent i = new Intent(Loginpage.this, HodHomepage.class);
//                                startActivity(i);
//                                finish();
//                            } else {
//                                Intent i = new Intent(Loginpage.this, AdminHomepage.class);
//                                startActivity(i);
//                                finish();
//                            }

                            String user = auth.getCurrentUser().getUid();
                            reference.child("Hod").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot x: snapshot.getChildren() )
                                    {
                                        if(x.getKey().equals(user) )
                                        {
                                            Intent intent = new Intent(Loginpage.this,HodHomepage.class);
                                            startActivity(intent);
                                            finishAffinity();
                                            Toast.makeText(Loginpage.this, "Sign In Successfull", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            reference.child("ServiceTeam").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot x: snapshot.getChildren() )
                                    {
                                        if(x.getKey().equals(user) && x.child("approved").getValue().toString().equals("1"))
                                        {
                                            Intent intent = new Intent(Loginpage.this,View_complaint_ServiceTeam.class);
                                            startActivity(intent);
                                            finishAffinity();
                                            Toast.makeText(Loginpage.this, "Sign In Successfull", Toast.LENGTH_SHORT).show();
                                        }
                                        if(x.getKey().equals(user) && x.child("approved").getValue().toString().equals("0"))
                                        {
                                            Toast.makeText(Loginpage.this, "Not Approved", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            reference.child("Admin").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot x: snapshot.getChildren() )
                                    {
                                        if(x.getKey().equals(user))
                                        {
                                            Intent intent = new Intent(Loginpage.this,AdminHomepage.class);
                                            startActivity(intent);
                                            finishAffinity();
                                            Toast.makeText(Loginpage.this, "Sign In Successfull", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        } else {
                            Toast.makeText(Loginpage.this, "Invalid Email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        });





    }





}