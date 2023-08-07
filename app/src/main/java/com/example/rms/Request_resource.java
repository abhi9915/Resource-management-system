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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Data_classes.Request_Attributes;

public class Request_resource extends AppCompatActivity {

    EditText email,quantity,location;
    Spinner spinner;
    ArrayAdapter adapter,adapter2;
    Button request;
    RadioButton r1,r2;
    String item;

    List<String > uniqueId;

    RadioGroup group;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference ref=db.getReference();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref2 = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_resource);


        email=findViewById(R.id.editTextTextPersonName3);
        location=findViewById(R.id.editTextTextPersonName7);

        quantity=findViewById(R.id.editTextNumber);
        spinner=findViewById(R.id.spinner_request);
        request=findViewById(R.id.button_request);
        group=findViewById(R.id.radioGroup);
        r1=findViewById(R.id.electricbutton);
        r2=findViewById(R.id.furniturebutton);

        String mail=auth.getCurrentUser().getEmail();
        email.setText(mail);
        email.setEnabled(false);
        String str;
        int i=0;
        while(mail.charAt(i)!='@')
        {
            i++;
        }

        str=mail.substring(0,i);

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = ArrayAdapter.createFromResource(Request_resource.this,R.array.spinner_array_electric, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter2 = ArrayAdapter.createFromResource(Request_resource.this, spinner_array_furniture, android.R.layout.simple_spinner_item);
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
                uniqueId = new ArrayList<>();
                ref2.child("resources").child(item).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren())
                        {
                            if(ds.child("alloted").getValue().toString().equalsIgnoreCase("No"))
                                uniqueId.add(ds.getKey());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ref.child("Requested");
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cat = findcat();
                String loc = location.getText().toString();
                String q = quantity.getText().toString();
                if (loc.equals("") || cat == null || item.equals("Select") || q.length() == 0) {
                    Toast.makeText(Request_resource.this, "Invalid Request", Toast.LENGTH_SHORT).show();
                } else {

                    int q2 = Integer.parseInt(q);
                    if (q2 <= 0) {
                        Toast.makeText(Request_resource.this, "Enter a valid quantity", Toast.LENGTH_SHORT).show();
                    } else {

                        if(q2<=uniqueId.size())
                        {
                            Toast.makeText(Request_resource.this, "Sending request", Toast.LENGTH_SHORT).show();
                            for(int i = 0; i < q2; i++) {

                                ref2.child("resources").child(item).child(uniqueId.get(i)).child("alloted").setValue("pending");

//                                ref2.child("Requested Resources").child(item).child(uniqueId.get(i)).child("alloted").setValue("pending");
//                                ref2.child("Requested Resources").child(item).child(uniqueId.get(i)).child("email").setValue(user.getEmail());
//                                ref2.child("Requested Resources").child(item).child(uniqueId.get(i)).child("location").setValue(loc);
//                                ref2.child("Requested Resources").child(item).child(uniqueId.get(i)).child("quantity").setValue("pending");

                            }

                            String key = ref2.child("Requested Resources").push().getKey();
                            HashMap <String,String> hm = new HashMap<>();

                            hm.put("quantity",q);
                            hm.put("location",loc);
                            hm.put("email",user.getEmail());
                            hm.put("item",item);

                            ref2.child("Requested Resources").child(key).setValue(hm).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    ref2.child("Requested Resources").child(key).removeValue();

                                    Toast.makeText(Request_resource.this, "Fail", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                                    ref.child("All Resources").child(key).setValue(hm).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            ref.child("All Resources").child(key).child("status").setValue("0");
                                        }
                                    });
                                }
                            });






                        }
                        else{
                            Toast.makeText(Request_resource.this, "Invalid", Toast.LENGTH_SHORT).show();
                        }


//                **********************************************************************************************


//                        ref2.child("resources").child(item).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    DataSnapshot dataSnapshot = task.getResult();
//                                    Map<String, String> mp = (HashMap) dataSnapshot.getValue();
//                                    int q1 = Integer.parseInt(mp.get("quantity"));
//                                    int q2 = Integer.parseInt(q);
//                                    System.out.println(q1 + "************" + q2);
//                                    if (q2 <= q1) {
//                                        String key = ref.child("Requested").push().getKey();
//                                        Request_Attributes obj = new Request_Attributes(q, cat, item, loc, mail, key);
//                                        ref.child("Requested").child(key).setValue(obj);
//                                        Toast.makeText(Request_resource.this, "Request Send", Toast.LENGTH_SHORT).show();
//
////                                Map<String, Object> mp1=new HashMap<>();
////                                String s="";
////                                mp1.put("quantity",s+=(q1-q2));
////                                ref2.child("resources").child(item).updateChildren(mp1);
//
//                                    } else {
//                                        Toast.makeText(Request_resource.this, "Insufficient Qunatity", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }
//                        });
                    }
//                ref2.child("resources").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot ds:snapshot.getChildren())
//                        {
//                            if (ds.getValue().toString().equals(item))
//                            {
//                                System.out.println("okay----------------------------");
////                                Toast.makeText(Request_resource.this, "clicked", Toast.LENGTH_SHORT).show();
//                                int q1=Integer.parseInt(ds.child("quantity").getValue().toString());
//                                int q2=Integer.parseInt(q);
//                                System.out.println(q1+"************"+q2);
//                                if(q2 <= q1)
//                                {
//                                    String key=ref.child("Requested").push().getKey();
//                                    Request_Attributes obj=new Request_Attributes(q,cat,item,loc,mail,key);
//                                    ref.child("Requested").child(key).setValue(obj);
//                                    Toast.makeText(Request_resource.this, "Request Send", Toast.LENGTH_SHORT).show();
//                                }
//                                else{
//                                    Toast.makeText(Request_resource.this, "Insufficient Qunatity", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });


                }
                location.setText("");
                quantity.setText("");
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