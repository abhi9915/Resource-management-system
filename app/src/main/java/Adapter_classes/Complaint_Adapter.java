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
import com.example.rms.View_complaint_admin;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Data_classes.Complaint_card_model;

public class Complaint_Adapter extends RecyclerView.Adapter<Complaint_Adapter.Complaint_card_holder>{

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public Complaint_Adapter(Context context,ArrayList<Complaint_card_model> data) {
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
        View view=inflater.inflate(R.layout.complaint_card,parent,false);
        return new Complaint_card_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Complaint_card_holder holder, int position) {
            int x = position;
            Complaint_card_model ccm=list.get(position);


            String email=ccm.getEmail();
            String category=ccm.getCategory();
            String item=ccm.getItem();
            String desc=ccm.getDescription();

            holder.t2.setText(item);
            holder.t3.setText(category);
            holder.t4.setText(email);
            holder.e1.setText(desc);

            holder.b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Forwarded to ST", Toast.LENGTH_SHORT).show();
                    ref.child("Complaint").child(list.get(x).getKey()).child("w").setValue(1);
                    Intent intent = new Intent(context, View_complaint_admin.class);
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
        TextView t1,t2,t3,t4,e1;
        Button b1;
        public Complaint_card_holder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView) itemView.findViewById(R.id.textView21);
            t2=(TextView) itemView.findViewById(R.id.textView23);
            t3=(TextView) itemView.findViewById(R.id.textView25);
            t4=(TextView) itemView.findViewById(R.id.textView27);
            e1=(TextView) itemView.findViewById(R.id.textView17);
            b1=(Button) itemView.findViewById(R.id.button3);
        }
    }
}

