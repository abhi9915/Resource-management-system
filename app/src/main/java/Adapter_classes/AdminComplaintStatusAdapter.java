package Adapter_classes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rms.AdminComplaintStatus;
import com.example.rms.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Data_classes.Complaint_card_model;

public class AdminComplaintStatusAdapter extends RecyclerView.Adapter<AdminComplaintStatusAdapter.Complaint_card_holder>{


    ArrayList<Complaint_card_model> list;
    Context context;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference ref=db.getReference();
    String key;

    public AdminComplaintStatusAdapter(ArrayList<Complaint_card_model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminComplaintStatusAdapter.Complaint_card_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        //System.out.println("hello**************");
        View view=inflater.inflate(R.layout.cardhodcomplaintstatus,parent,false);
        return new Complaint_card_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminComplaintStatusAdapter.Complaint_card_holder holder, int position) {
        Complaint_card_model ccm=list.get(position);

//        if(position%2==1)
//        {
//            holder.complaintStatusHodCard.setBackgroundColor(context.getResources().getColor(R.color.a));
//
//        } else{
//            holder.complaintStatusHodCard.setBackgroundColor(context.getResources().getColor(R.color.b));
//        }

        String email=ccm.getEmail();
        String category=ccm.getCategory();
        String item=ccm.getItem();
        String desc=ccm.getDescription();
        String feedback="";
        key = list.get(position).getKey();

        if(ccm.getX()==1)
            holder.t5.setText("This product will be repair shortly.");
        else if(ccm.getX()==2)
            holder.t5.setText("This product will be replace shortly.");


        ref.child("Complaint").child(ccm.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //System.out.println("NDKBDIHDIUH(WYEYE@E (*FY*");
                System.out.println(snapshot!=null);
                if(snapshot!=null && snapshot.child("y").getValue().toString().equals("1"))
                    holder.t6.setText("Completed");
                else if(snapshot!=null && snapshot.child("x").getValue().toString().equals("1") || snapshot.child("x").getValue().toString().equals("2"))
                    holder.t6.setText("service team forward to admin");
                else if(snapshot!=null && snapshot.child("w").getValue().toString().equals("1"))
                    holder.t6.setText("with sercive team");
                else if(snapshot!=null && snapshot.child("w").getValue().toString().equals("0"))
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
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        holder.c1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view)
            {
                int pos=holder.getAdapterPosition();
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
                ref.child("Complaint").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds: snapshot.getChildren())
                        {
//                            if(ds.getKey().equals(ds.child("key").getValue().toString()))
//                            {
                                key=list.get(pos).getKey();
                                deleteDialog();

                                break;
//                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return true;
            }
        });











    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    private void deleteDialog() {

        //alert
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference();
        builder.setTitle("Delete");
        //set builder
        builder.setMessage("Do you want to Delete this Complaint?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPostReference.child("Complaint").child(key).removeValue();
                Intent k=new Intent(context, AdminComplaintStatus.class);
                context.startActivity(k);


            }

        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        //create and show dialog
        builder.create().show();
    }
    public class Complaint_card_holder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5,t6;
        ConstraintLayout complaintStatusHodCard;
        CardView c1;
        public Complaint_card_holder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView) itemView.findViewById(R.id.name);
            t2=(TextView) itemView.findViewById(R.id.email_id);
            t3=(TextView) itemView.findViewById(R.id.textView14);
            t4=(TextView) itemView.findViewById(R.id.textView16);
            t5=(TextView) itemView.findViewById(R.id.textViewResponseAns);
            t6=(TextView) itemView.findViewById(R.id.StatusComplaint);
            c1=(CardView) itemView.findViewById(R.id.clickable_cv);
            complaintStatusHodCard =(ConstraintLayout) itemView.findViewById(R.id.complaintStatusHodCard);
        }
    }
}
