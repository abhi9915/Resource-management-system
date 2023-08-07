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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Data_classes.Resources_Attributes;

public class Add_resources extends AppCompatActivity {

    EditText quan;
    TextView t1,t2;
    Button add;
    Spinner spinner;
    RelativeLayout radio;
    ArrayAdapter adapter,adapter2;
    RadioButton r1,r2;
    RadioGroup group;
    String item;

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference reference=db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resources);

        quan = findViewById(R.id.editTextTextPersonName12);
        t1 = findViewById(R.id.textView8);
        add = findViewById(R.id.button);
        t2 = findViewById(R.id.textView10);
        group = findViewById(R.id.radioGroup);
        r1 = findViewById(R.id.electricbutton);
        r2 = findViewById(R.id.furniturebutton);
        spinner = findViewById(R.id.spinner);
        radio = findViewById(R.id.radioGroupLayout);

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = ArrayAdapter.createFromResource(Add_resources.this, R.array.spinner_array_electric, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter2 = ArrayAdapter.createFromResource(Add_resources.this, spinner_array_furniture, android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter2);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString();
                System.out.println(item);
                setItem(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // ref.child("resources");

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference ref2 = database.getReference();
//
//                String cat = findcat();
//                String q = quan.getText().toString();
//               // int q2 = Integer.parseInt(q);
////                 String key=ref.child("resources").child(item).push().getKey();
//                System.out.println(q + "______________________________________________________________________");
//                if (item == null || cat == null) {
//                    Toast.makeText(Add_resources.this, "Invalid Request", Toast.LENGTH_SHORT).show();
//                }
//                else if(item.isEmpty()==true || q.isEmpty()==true || cat.isEmpty()==true)
//                {
//                    Toast.makeText(Add_resources.this, "Invalid Request", Toast.LENGTH_SHORT).show();
//                }
//                else if( Integer.parseInt(q) < 0)
//                {
//                    Toast.makeText(Add_resources.this, "Invalid Request", Toast.LENGTH_SHORT).show();
//                }
//                else {
//
//                    System.out.println("Welcome"+"__________________________________________________________________");
//                    final int[] f = {0};
//                    ref2.child("resources").child(item).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            for(DataSnapshot snap : snapshot.getChildren()) {
//                                System.out.println(snap);
//                                Resources_Attributes resources_attributes=new Resources_Attributes(item,q,cat);
//                                try {
//                                    resources_attributes = snap.getValue(Resources_Attributes.class);
//                                }
//                                catch(Exception e)
//                                {
//                                    System.out.println("******************************************");
//                                }
//
//                                System.out.println(item+" "+resources_attributes.getName());
//                                if(resources_attributes.getName().equals(item)) {
//
//                                    f[0] = 1;
//                                }
//                            }
//
//                            if(f[0]==1) {
//                                ref2.child("resources").child(item).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                                        if (task.isSuccessful()) {
//                                            DataSnapshot dataSnapshot = task.getResult();
//                                            HashMap mp = (HashMap) dataSnapshot.getValue();
//                                            assert mp != null;
//                                            int q1 = Integer.parseInt(Objects.requireNonNull(mp.get("quantity")).toString());
//                                            int q2 = Integer.parseInt(q);
//
//                                            Resources_Attributes ob = new Resources_Attributes(item, q, cat);
//                                            ref2.child("resources").child(item).setValue(ob);
//
//                                            Map<String, Object> mp1 = new HashMap<>();
//                                            String s = "";
//                                            mp1.put("quantity", s += (q1 + q2));
//                                            ref2.child("resources").child(item).setValue(mp1);
//
//
//                                            Toast.makeText(Add_resources.this, "Adding Resource", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//                            } else {
//                                HashMap<String, String>mp = new HashMap<>();
//                                mp.put("cat_name",cat);
//                                mp.put("name",item);
//                                mp.put("quantity",q);
//                                ref2.child("resources").child(item).setValue(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        Toast.makeText(Add_resources.this, "Added Resource", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//                }
//            }
//        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cat = findcat();
                String q = quan.getText().toString();
                if (cat == null || item.equals("Select") || q.length()==0) {
                    Toast.makeText(Add_resources.this, "Invalid Request", Toast.LENGTH_SHORT).show();
                }
                else {

                    int quantity = Integer.parseInt(q);
                    for (int i = 0; i < quantity; i++)
                    {


                        HashMap<String , String > mp = new HashMap<>();
                        mp.put("cat_name",cat);
                        mp.put("alloted","No");
                        reference.child("resources").child(item).push().setValue(mp);
                    }
//                    String str=reference.child("resources").child(item).toString();
//
//                    reference.child("resources").child(item).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DataSnapshot> task) {
//                            if(task.isSuccessful()) {
//                                DataSnapshot dataSnapshot= task.getResult();
//                                if(dataSnapshot.exists()) {
//                                    Map<String, String> mp = (HashMap) dataSnapshot.getValue();
//                                    int q1 = Integer.parseInt(mp.get("quantity"));
//                                    int q2=Integer.parseInt(q);
//                                    Map<String, Object> mp1=new HashMap<>();
//                                    String s="";
//                                    mp1.put("quantity",s+=(q1+q2));
//                                    reference.child("resources").child(item).updateChildren(mp1);
//                                    Toast.makeText(Add_resources.this, "item exists, value updated", Toast.LENGTH_SHORT).show();
//                                }
//                                else {
//                                    Resources_Attributes ob = new Resources_Attributes(item, q, cat);
//                                    reference.child("resources").child(item).setValue(ob);
//                                    // Toast.makeText(Add_resources.this, "Adding Resource", Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(Add_resources.this, "item added", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
//                    });
                }


                Toast.makeText(Add_resources.this, "Resource added", Toast.LENGTH_SHORT).show();
                quan.setText("");
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
    void finditem()
    {
        final String[] item = new String[1];

    }

    void setItem(String x)
    {
        item = x;
    }
}