package Adapter_classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rms.R;
import com.example.rms.View_complaint_ServiceTeam;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Data_classes.Complaint_card_model;

public class Complaint_Adapter_ServiceTeam extends RecyclerView.Adapter<Complaint_Adapter_ServiceTeam.Complaint_card_holder>{

    public Complaint_Adapter_ServiceTeam(Context context,ArrayList<Complaint_card_model> data) {
        this.context=context;
        this.list = data;
    }

    ArrayList<Complaint_card_model> list;
    Context context;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference ref=db.getReference();
    @NonNull
    @Override
    public Complaint_card_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        System.out.println("hello**************");
        View view=inflater.inflate(R.layout.complaint_card_service_team,parent,false);
        return new Complaint_card_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Complaint_card_holder holder, int position) {
        int pos = position;
        Complaint_card_model ccm=list.get(position);

            String email=ccm.getEmail();
            String category=ccm.getCategory();
            String item=ccm.getItem();
            String desc=ccm.getDescription();

            holder.t1.setText(email);
            holder.t3.setText(item);
            holder.t2.setText(category);
            holder.t4.setText(desc);



        holder.repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Repaired", Toast.LENGTH_SHORT).show();
                ref.child("Complaint").child(list.get(pos).getKey()).child("x").setValue(1);
                Intent intent = new Intent(context, View_complaint_ServiceTeam.class);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
        holder.replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Replaced", Toast.LENGTH_SHORT).show();
                ref.child("Complaint").child(list.get(pos).getKey()).child("x").setValue(2);
                Intent intent = new Intent(context, View_complaint_ServiceTeam.class);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Complaint_card_holder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4;
        Button replace,repair;
        public Complaint_card_holder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView) itemView.findViewById(R.id.name);
            t2=(TextView) itemView.findViewById(R.id.email_id);
            t3=(TextView) itemView.findViewById(R.id.textView14);
            t4=(TextView) itemView.findViewById(R.id.textView16);
            replace=(Button) itemView.findViewById(R.id.button3);
            repair=(Button) itemView.findViewById(R.id.button2);
        }
    }
}

