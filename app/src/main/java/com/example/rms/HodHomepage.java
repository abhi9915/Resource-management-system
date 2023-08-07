package com.example.rms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HodHomepage extends AppCompatActivity {

    CardView complaintStatus,addresource,addcomplaint,viewresource,myresources,logout;

    TextView welcome;

//    Spinner spinner;
//    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_homepage);
        welcome=findViewById(R.id.Welcomeid);

        LinearLayout l1=findViewById(R.id.mainLayout1);
        LinearLayout l2=findViewById(R.id.mainLayout2);
        LinearLayout l3=findViewById(R.id.mainLayout3);
        LinearLayout l4=findViewById(R.id.mainLayout4);
        LinearLayout l5=findViewById(R.id.mainLayout5);
        LinearLayout l6=findViewById(R.id.mainLayout6);


        AnimationDrawable animationDrawable1= (AnimationDrawable) l1.getBackground();
        AnimationDrawable animationDrawable2= (AnimationDrawable) l2.getBackground();
        AnimationDrawable animationDrawable3= (AnimationDrawable) l3.getBackground();
        AnimationDrawable animationDrawable4= (AnimationDrawable) l4.getBackground();
        AnimationDrawable animationDrawable5= (AnimationDrawable) l5.getBackground();
        AnimationDrawable animationDrawable6= (AnimationDrawable) l6.getBackground();

        animationDrawable1.setEnterFadeDuration(2500);
        animationDrawable1.setExitFadeDuration(5000);
        animationDrawable1.start();

        animationDrawable2.setEnterFadeDuration(2500);
        animationDrawable2.setExitFadeDuration(5000);
        animationDrawable2.start();

        animationDrawable3.setEnterFadeDuration(2500);
        animationDrawable3.setExitFadeDuration(5000);
        animationDrawable3.start();

        animationDrawable4.setEnterFadeDuration(2500);
        animationDrawable4.setExitFadeDuration(5000);
        animationDrawable4.start();

        animationDrawable5.setEnterFadeDuration(2500);
        animationDrawable5.setExitFadeDuration(5000);
        animationDrawable5.start();

        animationDrawable6.setEnterFadeDuration(2500);
        animationDrawable6.setExitFadeDuration(5000);
        animationDrawable6.start();







        logout=findViewById(R.id.log);
        addcomplaint=findViewById(R.id.add_complaint_card);
        addresource=findViewById(R.id.request_card);
        viewresource=findViewById(R.id.view_resource_card);
        complaintStatus=findViewById(R.id.logout_card);
        myresources=findViewById(R.id.Alloted);

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String email= user.getEmail();
        String str[]=email.split("hod");
        String str2=""+"Welcome,"+str[0].toUpperCase()+" "+"Hod!";
        welcome.setText(str2);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(HodHomepage.this,RequestStatusHOD.class);
                startActivity(i);

            }
        });

        complaintStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HodHomepage.this,HodComplaintStatus.class);
                startActivity(i);
            }
        });
        addresource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HodHomepage.this,Request_resource.class);
                startActivity(i);
//                View view1=getLayoutInflater().inflate(R.layout.activity_request_resource, null, false);
//                Button okay=view1.findViewById(R.id.button_request);
//                spinner=findViewById(R.id.spinner_request);
//
//                adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.spinner_array, android.R.layout.simple_spinner_item);
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinner.setAdapter(adapter);
////
//                AlertDialog alertDialog=new AlertDialog.Builder(HodHomepage.this).setView(view1).create();
//                alertDialog.show();
//
//
//                okay.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        alertDialog.cancel();
//                    }
//                });

            }
        });
        addcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HodHomepage.this,Add_complaint.class);
                startActivity(i);

            }
        });

        viewresource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HodHomepage.this,View_resources_Admin.class);
                startActivity(i);

            }
        });
        myresources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HodHomepage.this,My_alloted_hod.class);
                startActivity(i);
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
                Intent i=new Intent(HodHomepage.this,Loginpage.class);
                startActivity(i);
                finishAffinity();
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}