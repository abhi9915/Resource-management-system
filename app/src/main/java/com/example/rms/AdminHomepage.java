package com.example.rms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminHomepage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CardView addresource,viewcomplaint,viewresource,request;


    ImageView logo;
    Toolbar toolbar;
    Button l;
    FirebaseAuth auth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_homepage);


        LinearLayout l1=findViewById(R.id.mainLayout1);
        LinearLayout l2=findViewById(R.id.mainLayout2);
        LinearLayout l3=findViewById(R.id.mainLayout3);
        LinearLayout l4=findViewById(R.id.mainLayout4);

        AnimationDrawable animationDrawable1= (AnimationDrawable) l1.getBackground();
        AnimationDrawable animationDrawable2= (AnimationDrawable) l2.getBackground();
        AnimationDrawable animationDrawable3= (AnimationDrawable) l3.getBackground();
        AnimationDrawable animationDrawable4= (AnimationDrawable) l4.getBackground();

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



        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        //toolbar=findViewById(R.id.toolBar);
        logo = findViewById(R.id.hamburgerLogo);
        //l=findViewById(R.id.buttonlogout);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_open,R.string.navigation_close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        viewcomplaint=findViewById(R.id.view_complaint_card);
        addresource=findViewById(R.id.add_resources_card);
        viewresource=findViewById(R.id.view_resource_admin_card);
        request=findViewById(R.id.request_status_card);

        System.out.println("hii");
        addresource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                View view1=getLayoutInflater().inflate(R.layout.add_resource_popup, null, false);
//                Button okay=view1.findViewById(R.id.addResource_okay);
//                AlertDialog alertDialog=new AlertDialog.Builder(AdminHomepage.this).setView(view1).create();
//                alertDialog.show();
//
//
//                okay.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        alertDialog.cancel();
//                    }
//                });
                Intent i=new Intent(AdminHomepage.this,Add_resources.class);
                startActivity(i);

            }
        });
        viewcomplaint.setOnClickListener(view -> {
            //Toast.makeText(AdminHomepage.this, "clicked", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(AdminHomepage.this,View_complaint_admin.class);
            startActivity(i);

        });

        viewresource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminHomepage.this,View_resources_Admin.class);
                startActivity(i);

            }
        });

        request.setOnClickListener(view -> {
            Intent i = new Intent(AdminHomepage.this,Request_status_admin.class);
            startActivity(i);
        });

        Menu menu = navigationView.getMenu();
        menu.getItem(0).setVisible(true);
        menu.getItem(1).setVisible(true);





        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.response_menu:
                        Intent j=new Intent(AdminHomepage.this,ServiceTeamToAdminResponse.class);
                        startActivity(j);
                        return true;

                    case R.id.logout:
                        //Toast.makeText(AdminHomepage.this, "logout clicked", Toast.LENGTH_SHORT).show();
                        FirebaseAuth auth=FirebaseAuth.getInstance();
                        auth.signOut();
                        Intent i=new Intent(AdminHomepage.this,Loginpage.class);
                        startActivity(i);
                        finish();
                        return true;


                    case R.id.allocated:
                        Intent n=new Intent(AdminHomepage.this,View_resourcesAllocated_Admin.class);
                        startActivity(n);
                        return true;

                    case R.id.complaintStatusAdmin:
                        Intent intent = new Intent(AdminHomepage.this, AdminComplaintStatus.class);
                        startActivity(intent);
                        return true;

                }
                return true;
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if(item.getItemId()==R.id.logout)
        {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            FirebaseAuth.getInstance().signOut();

            NavigationView navigationView = findViewById(R.id.navigationView);
            Menu menu = navigationView.getMenu();

            //To refresh the Action menu
            invalidateOptionsMenu();

//            menu.findItem(R.id.nav_signup).setVisible(true);
//            menu.findItem(R.id.nav_signin).setVisible(true);
//
//            menu.findItem(R.id.nav_myArticles).setVisible(false);
//            menu.findItem(R.id.nav_postArticles).setVisible(false);
//            menu.findItem(R.id.nav_notifications).setVisible(false);
//
//            menu.findItem(R.id.nav_reviewerDashboard).setVisible(false);
//            menu.findItem(R.id.nav_editorDashboard).setVisible(false);
//
//
//            View headerView = navigationView.getHeaderView(0);
//            TextView navUsername = (TextView) headerView.findViewById(R.id.editTextViewName);
//            navUsername.setText("Hello, Guest !");
//            TextView navEmail = (TextView) headerView.findViewById(R.id.editTextViewEmail);
//            navEmail.setText("guest@nitc.ac.in");
//
//            ImageView image=(ImageView)headerView.findViewById(R.id.imageView);
//            Picasso.get().load(R.drawable.imgavatar).into(image);

            Toast.makeText(AdminHomepage.this, "logout clicked", Toast.LENGTH_SHORT).show();
            FirebaseAuth auth1=FirebaseAuth.getInstance();
            auth1.signOut();
            Intent l=new Intent(AdminHomepage.this,Loginpage.class);
            startActivity(l);
            finish();
            return true;
        }
        else if(item.getItemId()==R.id.myProfile)
        {
            Toast.makeText(AdminHomepage.this, "My Profile", Toast.LENGTH_SHORT).show();
            Intent k=new Intent(AdminHomepage.this,MyProfile.class);
            startActivity(k);
            return true;
        }
        else {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);
        }
    }




}