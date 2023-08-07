package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_page extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    EditText c_password;
    Button register;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    ProgressBar progressBar;

    //FirebaseDatabase database = FirebaseDatabase.getInstance("https://vax-in-60807-default-rtdb.asia-southeast1.firebasedatabase.app");
   // DatabaseReference reference= database.getReference();

   /*public void onCreate() {
       FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = mAuth.getCurrentUser();
               if (user != null) {
                   // accessing Firebase Database
               }
           }
       };
       mAuth.getInstance().addAuthStateListener(authStateListener);

   }*/
    //FirebaseUser user= mAuth.getCurrentUser();

   // @SuppressLint("MissingInflatedId")
    @Override


   /* public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

       // mAuth= FirebaseAuth.getInstance();
        name=findViewById(R.id.editTextTextPersonName);
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        c_password=findViewById(R.id.editTextTextPassword2);
        register=findViewById(R.id.button);
        progressBar=findViewById(R.id.ProgressBar);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag=0;
                String Name=String.valueOf(name.getText()); //name.getText().toString();
                String Email=email.getText().toString();
                String Password=password.getText().toString();
                String C_password=c_password.getText().toString();

                progressBar.setVisibility(View.VISIBLE);

                if(TextUtils.isEmpty(Name)) {
                    Toast.makeText(Signup_page.this, "Name can't be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
               else if(TextUtils.isEmpty(Email)) {
                    Toast.makeText(Signup_page.this, "Email  can't be Empty", Toast.LENGTH_SHORT).show();
                    return;

                }
              else  if(TextUtils.isEmpty(Password)) {
                    Toast.makeText(Signup_page.this, "Password can't be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(C_password)) {
                    Toast.makeText(Signup_page.this, "Re-enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(TextUtils.isEmpty(Email)))
                {
                    String[] emailid = Email.split("@");
                    if(!(emailid[1].equals("nitc.ac.in"))) {
                        Toast.makeText(Signup_page.this, "Enter nitc Email ID", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }

                if(Password.compareTo(C_password)!=0) {
                    Toast.makeText(Signup_page.this, "Password and Confirm Password should be same", Toast.LENGTH_SHORT).show();
                    return;
                }




                mAuth.createUserWithEmailAndPassword(Email,Password)    //WithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(Signup_page.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                   // Log.d(TAG, "signInWithEmail:success");
                                  //  FirebaseUser user = mAuth.getCurrentUser();
                                   // updateUI(user);
                                    //Agar Successfull hogaya to Login Page pr Redirect Ho jayega

                                    Toast.makeText(Signup_page.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                                    UserDetails user = new UserDetails(Name, Email, Password,"0");

                                    FirebaseDatabase.getInstance().getReference().child("ServiceTeam").child(mAuth.getCurrentUser().getUid()).setValue(user);

                                    Intent i = new Intent(Signup_page.this,Loginpage.class);
                                    startActivity(i);
                                    finish();
                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                    {
                                        Toast.makeText(Signup_page.this, "This email id is already present", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(Signup_page.this, "Registration Unsuccessful.",
                                                Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    //updateUI(null);
                                }
                            }
                        });



            }
        });






        };
    }
