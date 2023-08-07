package com.example.rms;

import static com.example.rms.R.array.spinner_array_furniture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Data_classes.Complaint_Attributes;

public class Add_complaint extends AppCompatActivity {


    TextView t1;
    EditText email,dept,description;
    Spinner spinner;
    ArrayAdapter adapter,adapter2;
    String item;
    RadioGroup group;
    RadioButton r1,r2;
    Button complaint;

    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference ref=db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);
        //t1=findViewById(R.id.textView6);
        email=findViewById(R.id.editTextTextPersonName);
        dept=findViewById(R.id.editTextTextPersonName2);
        description=findViewById(R.id.editTextTextMultiLine);
        spinner=findViewById(R.id.spinner_complaint);
        t1=findViewById(R.id.textView6);
        r1=findViewById(R.id.electricbutton);
        r2=findViewById(R.id.furniturebutton);
        complaint=findViewById(R.id.sendcomplaint);


        String mail=auth.getCurrentUser().getEmail();
        email.setText(mail);

        String str[]=mail.split("hod");
        String upper=str[0].toUpperCase();
        dept.setText(upper);
        dept.setEnabled(false);
        email.setEnabled(false);
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = ArrayAdapter.createFromResource(Add_complaint.this,R.array.spinner_array_electric, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter2 = ArrayAdapter.createFromResource(Add_complaint.this, spinner_array_furniture, android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter2);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item=adapterView.getItemAtPosition(i).toString();
                System.out.println(item);
                setItem(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cat=findcat();
                String dep=dept.getText().toString();
                String des=description.getText().toString();

                final int[] flag = {0};

                if (dep.equals("") || cat == null || item == "Select" || des.equals(""))
                {
                    Toast.makeText(Add_complaint.this, "Invalid Request", Toast.LENGTH_SHORT).show();
                }
                else{

                    ref.child("Allocated Resources").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds:snapshot.getChildren())
                            {
                                if(mail.equals(ds.child("email").getValue().toString())
                                    && item.equals(ds.child("item").getValue().toString())) {

                                    String key = ref.child("Complaint").push().getKey();
                                    System.out.println(key + "**************************************");

                                    Complaint_Attributes ob = new Complaint_Attributes(des, cat, item, mail, dep, 0, 0, 0, 0,key);
                                    ref.child("Complaint").child(key).setValue(ob);

                                    Toast.makeText(Add_complaint.this, "Complaint Send", Toast.LENGTH_SHORT).show();
                                    flag[0] =1;

                                    //dept.setText("");


                                    break;
                                }
                            }
                            if(flag[0]==0)
                            {
                                Toast.makeText(Add_complaint.this, "Item not allocated", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


                description.setText("");
                r1.setChecked(false);
                r2.setChecked(false);
            }
        });

    }
    String findcat()
    {
        if(r1.isChecked())
            return "electric";
        if(r2.isChecked())
            return "furniture";

        return null;
    }
    void setItem(String x)
    {
        item = x;
    }
}