package com.example.rms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Data_classes.Request_resource_model;

public class RequestStatusAdminAdapter extends RecyclerView.Adapter<Single_request_card_holder> {

    ArrayList<Request_resource_model> data;
    Context context;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference ref=db.getReference();
    List<String > uniqueId = new ArrayList<>();

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref2=database.getReference();

    public RequestStatusAdminAdapter(Context context, ArrayList<Request_resource_model> data) {
        this.context=context;
        this.data = data;
    }

    @NonNull
    @Override
    public Single_request_card_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_request_card,parent,false);
        return new Single_request_card_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.rms.Single_request_card_holder holder, int position) {
        int x = position;
        Request_resource_model rrm=data.get(position);

        String email= rrm.getEmail();
//        List<String > item = new ArrayList<>();
        String item= rrm.getItem();
//        FirebaseDatabase datab = FirebaseDatabase.getInstance();
//        DatabaseReference database1= datab.getReference();
//        database1.child("Requested Resources").child(rrm.getKey()).child("item").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                item.add(snapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        String cat= rrm.getCategory();
        String qu= rrm.getQuantity();
        String loc=rrm.getLocation();
        String status= rrm.getStatus();

        System.out.println(rrm.getItem()+"******************************"+rrm.getEmail());

        holder.t1.setText(rrm.getEmail());
        holder.t2.setText(rrm.getItem());
        holder.t3.setText(rrm.getQuantity());
        holder.t4.setText(rrm.getLocation());

        System.out.println(item+" 99999999999999999999999999");

        ref2.child("resources").child(item).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("alloted").getValue().toString().equalsIgnoreCase("pending"))
                    {
                        uniqueId.add(ds.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        String k=rrm.getKey();
//        ref.child("Alloted Resources");
        holder.acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                **********************************************************************************************
                for(int i = 0; i < Integer.parseInt(qu); i++ ) {

                    ref2.child("Allocated Resources").child(uniqueId.get(i)).child("email").setValue(email);
                    ref2.child("Allocated Resources").child(uniqueId.get(i)).child("location").setValue(loc);
                    ref2.child("Allocated Resources").child(uniqueId.get(i)).child("item").setValue(item);
                    ref2.child("Allocated Resources").child(uniqueId.get(i)).child("Id").setValue(uniqueId.get(i));


                    ref2.child("All Resources").child(rrm.getKey()).child("status").setValue("1");

                    ref2.child("resources").child(item).child(uniqueId.get(i)).removeValue();
                    ref2.child("Requested Resources").child(rrm.getKey()).removeValue();
                    context.startActivity(new Intent(context,Request_status_admin.class));
                    ((Activity)context).finish();


                }
//                ref2.child("Requested Resources").child(item).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                    if (task.isSuccessful()) {
//                        DataSnapshot dataSnapshot = task.getResult();
//                        Map<String, String> mp = (HashMap) dataSnapshot.getValue();
//                        int q1 = Integer.parseInt(mp.get("quantity"));
//                        int q2 = Integer.parseInt(qu);
//
//                            String key=ref.child("Alloted Resources").push().getKey();
//                            Allocate_attributes aa=new Allocate_attributes(email,qu,item,cat,loc);
//                            ref.child("Allocated Resources").child(key).setValue(aa);
//
//                            Map<String, Object> mp1=new HashMap<>();
//                            String s="";
//                            mp1.put("quantity",s+=(q1-q2));
//                            ref2.child("resources").child(item).updateChildren(mp1);
////                Request_Attributes obj=new Request_Attributes(qu,cat,item,loc,email,k);
////                obj.setStatus("Yes");
////                ref.child("Requested").child(k).setValue(obj);
//
//                            ref.child("Requested").child(k).removeValue();
//                            Intent intent = new Intent(context, Request_status_admin.class);
//                            context.startActivity(intent);
//
//
//                    }
//                }
//            });


                Toast.makeText(context, "Resources Allocated", Toast.LENGTH_SHORT).show();



            }
        });
        holder.rej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String key=ref.child("rejected resource requests").push().getKey();
//                Allocate_attributes aa=new Allocate_attributes(email,qu,item,cat,loc);
//                ref.child("rejected resource requests").child(key).setValue(aa);
//
//                ref.child("Requested").child(k).removeValue();

                for(int i = 0; i < Integer.parseInt(qu); i++ ) {

                    ref2.child("All Resources").child(rrm.getKey()).child("status").setValue("-1");

                    ref2.child("resources").child(item).child(uniqueId.get(i)).child("alloted").setValue("No");
                    ref2.child("Requested Resources").child(rrm.getKey()).removeValue();
                    context.startActivity(new Intent(context,Request_status_admin.class));
                    ((Activity)context).finish();


                }

                Intent intent = new Intent(context, Request_status_admin.class);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
