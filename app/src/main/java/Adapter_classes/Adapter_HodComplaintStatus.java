package Adapter_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rms.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Data_classes.Complaint_card_model;

public class Adapter_HodComplaintStatus extends RecyclerView.Adapter<Adapter_HodComplaintStatus.Complaint_card_holder>{

    public Adapter_HodComplaintStatus(Context context,ArrayList<Complaint_card_model> data) {
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
        View view=inflater.inflate(R.layout.cardhodcomplaintstatus,parent,false);
        return new Complaint_card_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Complaint_card_holder holder, int position) {
            Complaint_card_model ccm=list.get(position);

//            if(position%2==1)
//            {
//                holder.complaintStatusHodCard.setBackgroundColor(context.getResources().getColor(R.color.a));
//
//            } else{
//                holder.complaintStatusHodCard.setBackgroundColor(context.getResources().getColor(R.color.b));
//            }

            String email=ccm.getEmail();
            String category=ccm.getCategory();
            String item=ccm.getItem();
            String desc=ccm.getDescription();
            String feedback="";

            if(ccm.getX()==1)
                feedback="This product will be replace shortly.";
            else if(ccm.getX()==2)
                feedback="This product will be repair shortly.";

            ref.child("Complaint").child(ccm.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                        if(snapshot.child("y").getValue().toString().equals("1"))
                            holder.t6.setText("Completed");
                        else if(snapshot.child("x").getValue().toString().equals("1") || snapshot.child("x").getValue().toString().equals("2"))
                            holder.t6.setText("service team forward to admin");
                        else if(snapshot.child("w").getValue().toString().equals("1"))
                            holder.t6.setText("with sercive team");
                        else if(snapshot.child("w").getValue().toString().equals("0"))
                            holder.t6.setText("Pending");




                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        holder.t1.setText(email);
            holder.t3.setText(item);
            holder.t2.setText(category);
            holder.t4.setText(desc);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Complaint_card_holder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5,t6;
        ConstraintLayout complaintStatusHodCard;
        public Complaint_card_holder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView) itemView.findViewById(R.id.name);
            t2=(TextView) itemView.findViewById(R.id.email_id);
            t3=(TextView) itemView.findViewById(R.id.textView14);
            t4=(TextView) itemView.findViewById(R.id.textView16);
            t5=(TextView) itemView.findViewById(R.id.textViewResponseAns);
            t6=(TextView) itemView.findViewById(R.id.StatusComplaint);
            complaintStatusHodCard =(ConstraintLayout) itemView.findViewById(R.id.complaintStatusHodCard);
        }
    }
}

