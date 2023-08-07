package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference reference=database.getReference();

    private static int SCREEN_SPLASH=3000;
    //variables
    Animation top,bottom;
    ImageView image,image2;
    TextView logo,slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        top= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image=findViewById(R.id.imageView);
        logo=findViewById(R.id.textView);
        slogan=findViewById(R.id.textView3);
        image2=findViewById(R.id.imageView5);

        image.setAnimation(top);
        logo.setAnimation(bottom);
        image2.setAnimation(bottom);
        slogan.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abcd();
                finish();
            }
        },SCREEN_SPLASH);
    }
    protected void abcd()
    {
        System.out.println("on start enter*********************************************");
        super.onStart();
        FirebaseUser users=auth.getCurrentUser();
        if(users!=null)
        {
            String user = auth.getCurrentUser().getUid();
            reference.child("Hod").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot x: snapshot.getChildren() )
                    {
                        if(x.getKey().equals(user))
                        {
                            Intent intent = new Intent(MainActivity.this,HodHomepage.class);
                            startActivity(intent);
                            finish();
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
                        if(x.getKey().equals(user))
                        {
                            Intent intent = new Intent(MainActivity.this,View_complaint_ServiceTeam.class);
                            startActivity(intent);
                            finish();
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
                            Intent intent = new Intent(MainActivity.this,AdminHomepage.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else {
            Intent i=new Intent(MainActivity.this, Loginpage.class);
            startActivity(i);
            finish();
        }



    }
}